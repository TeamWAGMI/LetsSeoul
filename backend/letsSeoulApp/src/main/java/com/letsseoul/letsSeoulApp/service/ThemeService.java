package com.letsseoul.letsSeoulApp.service;


import com.letsseoul.letsSeoulApp.domain.FollowTheme;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeMapListResponseDto;
import com.letsseoul.letsSeoulApp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.letsseoul.letsSeoulApp.domain.Hottheme;
import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.domain.Store;
import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import com.letsseoul.letsSeoulApp.dto.theme.PopularThemeListResponseDto;
import com.letsseoul.letsSeoulApp.dto.theme.RecommendedThemeListResponseDto;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional

@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;

    private final FollowThemeRepository followThemeRepository;
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
     /*
     *  TH-0003
     * */

      /*
         * 1. 여기서 리스폰스로 바로바꿔준다.  dto에 대한 검증이 불필요할때 get?
         * 2. 그냥 보내서 dto에서 바꾼다 2차적인 검증을 하면 당연히 좋지만 없어도 순서대로 나오니 상관없다 생각 했음 ex)중간에 삭제된 데이터가 있을 수 있어 위험 검증해야함 n2 시간복잡도
         * for (ThemeStore ts : tsList) {
  Long tsID = ts.getId();
  for (CountDto cdto : counts) {
    if (tdId == cdto.getId()) {
      지금만들고있는dto.setCount(cdto.getCount());
    }
  }
         return ThemeDto.ThemeMapListResponse.of(themeList);
    }
          */

    public ThemeDto.ThemeMapListResponse themeMapList(Long themeId) {
        Theme theme = themeRepository.findById(themeId).orElseThrow(ThemeService::triggerExceptionForIllegalRequest);
        List<ThemeMapListResponseDto> themeList = themeStoreRepository.findByTheme(themeId);

        return ThemeDto.ThemeMapListResponse.of(themeList);
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

    //TH-0011
    public ThemeDto.checkDibsThemeResponse checkDibsTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        return ThemeDto.checkDibsThemeResponse.of(followThemeRepository.existsByUserAndTheme(user,theme));

    }
    //TH-0012 테마찜 등록
    public ThemeDto.RegistDibsThemeResponse registDibsTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        followThemeRepository.save(new FollowTheme(user,theme));
        return ThemeDto.RegistDibsThemeResponse.of();
    }

    //TH-0013 테마찜 취소

    public ThemeDto.cancelDibsThemeResponse cancelDibsTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        FollowTheme followTheme = followThemeRepository.findByUserAndTheme(user, theme).orElseThrow(() -> new RuntimeException("찜한 테마가 없습니다"));
        followThemeRepository.delete(followTheme);
        return ThemeDto.cancelDibsThemeResponse.of();
    }

    // TH-0015 테마 정보 조회
    public ThemeDto.ThemeInfoResponse viewThemeInformation(Long themeId) {
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        return ThemeDto.ThemeInfoResponse.of(theme);
    }
}
