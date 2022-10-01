package com.letsseoul.letsSeoulApp.service;

import com.letsseoul.letsSeoulApp.domain.Hottheme;
import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.domain.Store;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import com.letsseoul.letsSeoulApp.dto.theme.PopularThemeListResponseDto;
import com.letsseoul.letsSeoulApp.dto.theme.RecommendedThemeListResponseDto;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import com.letsseoul.letsSeoulApp.repository.HotthemeRepository;
import com.letsseoul.letsSeoulApp.repository.ReviewRepository;
import com.letsseoul.letsSeoulApp.repository.StoreRepository;
import com.letsseoul.letsSeoulApp.repository.ThemeRepository;
import com.letsseoul.letsSeoulApp.repository.ThemeStoreRepository;
import com.letsseoul.letsSeoulApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final HotthemeRepository hotthemeRepository;
    private final ThemeStoreRepository themeStoreRepository;
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;

    private static ResponseStatusException triggerExceptionForIllegalRequest() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "잘못된 요청입니다.");
    }

    /**
     * TH-0001
     */
    public List<RecommendedThemeListResponseDto> listupRecommendedThemes() {

        List<Hottheme> hotthemes = hotthemeRepository.findAll();
        List<RecommendedThemeListResponseDto> hotthemeDtoList = new ArrayList<>();

        hotthemes.forEach(hottheme -> {
            Theme theme = themeRepository.findById(hottheme.getThemeId())
                    .orElseThrow(ThemeService::triggerExceptionForIllegalRequest);

            hotthemeDtoList.add(new RecommendedThemeListResponseDto(theme.getId(), theme.getEmoji(), theme.getTitle()));
        });

        hotthemeDtoList.forEach(rTheme -> rTheme.setStoreCount(
                themeStoreRepository.countByThemeId(rTheme.getId())));

        return hotthemeDtoList;
    }

    /**
     * TH-0002
     */
    public List<PopularThemeListResponseDto> listupPopularThemes() {

        return themeStoreRepository.countAllByGroupByThemeId();
    }

    /**
     * TH-0004 테마 리뷰 등록.
     * theme과 store가 존재해야 해당 theme-store의 review를 달 수 있다
     */
    public ThemeDto.RegistThemeReviewResponse registThemeReview(Long userId,
                                    Long themeId,
                                    Long storeId,
                                    ThemeDto.RegistThemeReviewPost registThemeReviewPost) {

        //별점 검증
        //theme 조회
        //store 조회
        // 둘다 있으면 review 등록 진행 : 한 트랜잭션 안에서
        //  theme-store 널고
        //  review 넣는다, 그런데 이제 user도 조회해서 넣어야 함.

        if (registThemeReviewPost.getScore() < 1 || 5 < registThemeReviewPost.getScore()) {
            throw triggerExceptionForIllegalRequest();
        }
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(ThemeService::triggerExceptionForIllegalRequest);
        Store store = storeRepository.findById(storeId)
                .orElseThrow(ThemeService::triggerExceptionForIllegalRequest);

        ThemeStore themeStore = themeStoreRepository.save(ThemeStore.builder()
                .theme(theme)
                .store(store)
                .build());

        Review savedReview = reviewRepository.save(Review.builder()
                .themeStore(themeStore)
                .user(userRepository.findById(userId)
                        .orElseThrow(ThemeService::triggerExceptionForIllegalRequest))
                .score(registThemeReviewPost.getScore())
                .content(registThemeReviewPost.getContent())
                .build());

        return ThemeDto.RegistThemeReviewResponse.of(savedReview);
    }
}
