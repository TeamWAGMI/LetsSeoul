package com.letsseoul.letsSeoulApp.service;


import com.letsseoul.letsSeoulApp.domain.FollowTheme;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeMapListResponseDto;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
//import com.letsseoul.letsSeoulApp.dto.theme.ThemeSearchResponseDto;
import com.letsseoul.letsSeoulApp.repository.*;
import com.querydsl.core.Tuple;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.letsseoul.letsSeoulApp.domain.Hottheme;
import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.domain.Store;
import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import com.letsseoul.letsSeoulApp.dto.theme.PopularThemeListResponseDto;
import com.letsseoul.letsSeoulApp.dto.theme.RecommendedThemeListResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
//import javax.persistence.EntityManager;
//import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private final ThemeCustomRepositoryImpl themeCustomRepository;
    private final SuggestThemeRepository suggestThemeRepository;

    private static ResponseStatusException triggerExceptionForIllegalRequest() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "잘못된 요청입니다.");
    }

    /**
     * TH-0001
     */
    public List<RecommendedThemeListResponseDto> listupRecommendedThemes() {

        List<Hottheme> hotThemes = hotthemeRepository.findAll();
        List<RecommendedThemeListResponseDto> hotThemeDtoList = new ArrayList<>();

        hotThemes.forEach(hotTheme ->
            themeRepository.findById(hotTheme.getThemeId())
                    .ifPresent(t -> hotThemeDtoList.add(
                            new RecommendedThemeListResponseDto(t.getId(), t.getEmoji(), t.getTitle()))));

        hotThemeDtoList.forEach(rTheme -> rTheme.setStoreCount(
                themeStoreRepository.countByThemeId(rTheme.getId())));

        return hotThemeDtoList;
    }

    /**
     * TH-0002
     */
    public List<PopularThemeListResponseDto> listupPopularThemes() {

        return themeStoreRepository.countAllByGroupByThemeId().stream()
                .sorted((dto1, dto2) -> (int) (dto2.getCount() - dto1.getCount()))
                .limit(8)
                .collect(Collectors.toList());
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
                                                                ThemeDto.RegistThemeReviewPost registThemeReviewPost) {

        // 하루(24시간 내)에 등록할 수 있는 리뷰의 개수는 최대 5개이다.
        if (5 < reviewRepository.countByUserIdAndCreatedDatetimeIsAfter(
                userId, LocalDateTime.now().minusDays(1L))) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "리뷰는 하루에 다섯 개 까지만 등록 가능합니다.");
        }

        //별점 검증
        //theme 조회
        //store 조회
        // 둘다 있으면 review 등록 진행 : 한 트랜잭션 안에서
        //  theme-store 널고
        //  review 넣는다, 그런데 이제 user도 조회해서 넣어야 함.
        if (registThemeReviewPost.getReview().getScore() < 1 || 5 < registThemeReviewPost.getReview().getScore()) {
            throw triggerExceptionForIllegalRequest();
        }
        Theme theme = themeRepository.findById(themeId)
                .orElseThrow(ThemeService::triggerExceptionForIllegalRequest);
        Store store = storeRepository.findByItemid(registThemeReviewPost.getStore().getItemid())
                .orElse(Store.builder()
                        .itemid(registThemeReviewPost.getStore().getItemid())
                        .title(registThemeReviewPost.getStore().getTitle())
                        .address(registThemeReviewPost.getStore().getAddress())
                        .lat(registThemeReviewPost.getStore().getLat())
                        .lng(registThemeReviewPost.getStore().getLng())
                        .build());
        storeRepository.save(store);


        ThemeStore themeStore = themeStoreRepository.save(ThemeStore.builder()
                .theme(theme)
                .store(store)
                .build());

        reviewRepository.save(Review.builder()
                .themeStore(themeStore)
                .user(userRepository.findById(userId)
                        .orElseThrow(ThemeService::triggerExceptionForIllegalRequest))
                .score(registThemeReviewPost.getReview().getScore())
                .content(registThemeReviewPost.getReview().getContent())
                .build());

        return ThemeDto.RegistThemeReviewResponse.of();
    }

    /**
     * TH-0009
     */
    public MultiResponseDto<ThemeDto.ThemeSearchResponse> themeSearch(ThemeDto.ThemeSearchPost themeSearchGet, Pageable pageable) {
        Page<Tuple> dynamicQuery = themeCustomRepository.findDynamicQuery(themeSearchGet,pageable);
        List<ThemeDto.ThemeSearchResponse> themeSearchResponses =new ArrayList<>();
        for(Tuple content:dynamicQuery.getContent()){
            themeSearchResponses.add(new ThemeDto.ThemeSearchResponse(
                    content.get(0,Long.class),
                    content.get(1,String.class),
                    content.get(2,String.class),
                    themeCustomRepository.findReviewCount(content.get(0,Long.class))
            ));
        }
        //List<ThemeDto.ThemeSearchResponse> response = themeSearchResponses.stream().sorted((o1, o2) -> (int) (o1.getReviewCount() - o2.getThemeId())).collect(Collectors.toList());
        return  ThemeDto.ThemeSearchResponse.of(dynamicQuery,themeSearchResponses);
        /*List<Tuple> tupleList = dynamicQuery.getContent();
        System.out.println("tupleList.get(0).get(3,Long.class) = " + tupleList.get(0).get(3,Long.class));*/
        //inner class this가되나?

        /*
         * 1. 페이지네이션
         * 2. 스토어카운트
         * 3. responseDto로 받아보기 ,BuilderExpression
         * */
    }

    /**
     * TH-0010
     * @param registThemePost 테마 등록 입력폼
     */
    public ThemeDto.RegistThemeResponse registTheme(ThemeDto.RegistThemePost registThemePost) {

        if (null == suggestThemeRepository.save(registThemePost.toEntity())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "잘못된 요청입니다.");
        }

        return ThemeDto.RegistThemeResponse.of();
    }

    //TH-0011
    public ThemeDto.checkDibsThemeResponse checkDibsTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        return ThemeDto.checkDibsThemeResponse.of(followThemeRepository.existsByUserAndTheme(user, theme));

    }

    //TH-0012 테마찜 등록
    public ThemeDto.RegistDibsThemeResponse registDibsTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        followThemeRepository.findByUserAndTheme(user,theme)
                .orElse(followThemeRepository.save(
                        FollowTheme.builder()
                                .theme(theme)
                                .user(user)
                        .build()));
        return ThemeDto.RegistDibsThemeResponse.of();
    }

    //TH-0013 테마찜 취소
    public ThemeDto.cancelDibsThemeResponse cancelDibsTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        followThemeRepository.findByUserAndTheme(user, theme).ifPresent(followThemeRepository::delete);
        return ThemeDto.cancelDibsThemeResponse.of();
    }


    // TH-0015 테마 정보 조회
    public ThemeDto.ThemeInfoResponse viewThemeInformation(Long themeId) {
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        return ThemeDto.ThemeInfoResponse.of(theme);
    }

    /*// TH-0014
    public List<ThemeSearchResponseDto> themeSearch(Integer page,
                                                    Integer size,
                                                    ThemeDto.ThemeSearchPost themeSearchPost) {

        StringBuffer jpql = new StringBuffer();
        String selectFromJoin = "" +
                " SELECT new com.letsseoul.letsSeoulApp.dto.theme.ThemeSearchResponseDto(t.id, t.emoji, t.title) " +
                " FROM Theme t " +
                " LEFT JOIN ThemeTag tt ON t.id = tt.theme.id " +
                " INNER JOIN Tag tg ON tt.tag.id = tg.id AND tg.status = 'E' " +
                " WHERE 1 = 1 ";
        String groupByorderBy = " GROUP BY t.id " +
                " ORDER BY t.id, tg.divnum ";
        String whereKeyword = "";
        String andForTag = "";
        String whereWhoTag = "";
        String whereWhatTag = "";
        String whereWhereTag = "";
        boolean keywordExist = StringUtils.hasText(themeSearchPost.getKeyword());
        boolean whoTagExist = StringUtils.hasText(themeSearchPost.getWho()[0]);
        boolean whatTagExist = StringUtils.hasText(themeSearchPost.getWhat()[0]);
        boolean whereTagExist = StringUtils.hasText(themeSearchPost.getWhere()[0]);

        if (keywordExist) {
            whereKeyword = " AND t.title like :keyword ";
        }

        if (whoTagExist || whatTagExist || whereTagExist) {
            andForTag = " AND ";
        }

        if (whoTagExist) {
            whereWhoTag = " tg.divnum = 1 AND tg.title = :whoTag ";

            if (whatTagExist || whereTagExist) {
                whereWhoTag += " OR ";
            }
        }
        if (whatTagExist) {
            whereWhatTag = " tg.divnum = 2 AND tg.title = :whatTag ";

            if (whereTagExist) {
                whereWhatTag += " OR ";
            }
        }
        if (whereTagExist) {
            whereWhereTag = " tg.divnum = 3 AND tg.title = :whereTag ";
        }

        TypedQuery<ThemeSearchResponseDto> query = em.createQuery(
                jpql.append(selectFromJoin)
                        .append(whereKeyword)
                        .append(andForTag)
                        .append(whereWhoTag)
                        .append(whereWhatTag)
                        .append(whereWhereTag)
                        .append(groupByorderBy)
                        .toString(), ThemeSearchResponseDto.class)
                .setMaxResults(1000);

        if (keywordExist) {
            query = query.setParameter("keyword", themeSearchPost.getKeyword());
        }
        if (whoTagExist) {
            query = query.setParameter("whoTag", themeSearchPost.getWho()[0]);
        }
        if (whatTagExist) {
            query = query.setParameter("whatTag", themeSearchPost.getWhat()[0]);
        }
        if (whereTagExist) {
            query = query.setParameter("whereTag", themeSearchPost.getWhere()[0]);
        }

        List<ThemeSearchResponseDto> resultDto = query.setMaxResults(size)
                .setFirstResult(page * size)
                .getResultList();

        resultDto.forEach(row -> row.setStoreCount(
                themeStoreRepository.countStoreByThemeId(row.getThemeId())
                        .orElse(0L)));

        return resultDto;
    }*/
}
