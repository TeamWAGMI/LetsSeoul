package com.letsseoul.letsSeoulApp.controller;


import com.letsseoul.letsSeoulApp.domain.Store;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.SingleListResponseDto;
import com.letsseoul.letsSeoulApp.dto.ThemeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;


@RestController
@RequestMapping("/api/v1/themes")
@RequiredArgsConstructor
@Slf4j
public class ThemeController {

    /**
     * BE-TH-0001 추천 테마 목록 조회
     */
    @GetMapping("/recommends")
    public ResponseEntity<List<ThemeDto.RecommendedThemesListResponse>> listOfRecommendedThemes() {

        List<Theme> stubList = new ArrayList<>();
        Theme theme1 = makeStubDataForTheme(1L, "🍔", "고칼로리 테마");
        Theme theme2 = makeStubDataForTheme(2L, "🥗🥗", "저칼로리 테마");
        Theme theme3 = makeStubDataForTheme(3L, "🍕🍟", "고지혈증 테마");
        Theme theme4 = makeStubDataForTheme(4L, "🥮🥯🥑", "고도비만 테마");
        Theme theme5 = makeStubDataForTheme(5L, "🥓🍜🌭🍟", "고혈압 테마");
        for (int i = 0; i < 10; i++) theme1.addThemeStore(new ThemeStore());
        for (int i = 0; i < 15; i++) theme2.addThemeStore(new ThemeStore());
        for (int i = 0; i < 20; i++) theme3.addThemeStore(new ThemeStore());
        for (int i = 0; i < 25; i++) theme4.addThemeStore(new ThemeStore());
        for (int i = 0; i < 30; i++) theme5.addThemeStore(new ThemeStore());
        stubList.add(theme1);
        stubList.add(theme2);
        stubList.add(theme3);
        stubList.add(theme4);
        stubList.add(theme5);
        stubList.sort(Comparator.comparingInt(o -> o.getThemeStoreList().size()));

        return ResponseEntity.ok().body(ThemeDto.RecommendedThemesListResponse.of(stubList));
    }

    /**
     * BE-TH-0002 인기 테마 목록 조회
     */
    @GetMapping("/populars")
    public ResponseEntity<List<ThemeDto.PopularThemesListResponse>> listOfPopularThemes() {

        List<Theme> stubList = new ArrayList<>();
        Theme theme1 = makeStubDataForTheme(1L, "🍔", "크로플 존맛인 카페");
        Theme theme2 = makeStubDataForTheme(2L, "🥗🥗", "혼자 노트북 들고 가기 좋은 카페");
        Theme theme3 = makeStubDataForTheme(3L, "🍕🍟", "빵지순례 필수코스");
        Theme theme4 = makeStubDataForTheme(4L, "🥮🥯🥑", "감자튀김이 바삭한 곳");
        Theme theme5 = makeStubDataForTheme(5L, "🥓🍜🌭🍟", "치즈 좋아하면 모여라 필수코스");
        Theme theme6 = makeStubDataForTheme(6L, "🍘🍣", "초밥 먹고 싶으면 여기로");
        Theme theme7 = makeStubDataForTheme(7L, "🥗🥙🌮", "난 샐러드를 맛있어서 먹어");
        Theme theme8 = makeStubDataForTheme(8L, "🍚", "집밥 맛집");
        for (int i = 0; i < 10; i++) theme1.addThemeStore(new ThemeStore());
        for (int i = 0; i < 15; i++) theme2.addThemeStore(new ThemeStore());
        for (int i = 0; i < 20; i++) theme3.addThemeStore(new ThemeStore());
        for (int i = 0; i < 25; i++) theme4.addThemeStore(new ThemeStore());
        for (int i = 0; i < 30; i++) theme5.addThemeStore(new ThemeStore());
        for (int i = 0; i < 35; i++) theme6.addThemeStore(new ThemeStore());
        for (int i = 0; i < 40; i++) theme7.addThemeStore(new ThemeStore());
        for (int i = 0; i < 45; i++) theme8.addThemeStore(new ThemeStore());
        stubList.add(theme1);
        stubList.add(theme2);
        stubList.add(theme3);
        stubList.add(theme4);
        stubList.add(theme5);
        stubList.add(theme6);
        stubList.add(theme7);
        stubList.add(theme8);

        return ResponseEntity.ok().body(ThemeDto.PopularThemesListResponse.of(stubList));
    }

    /**
     * BE-TH-0003 테마지도 조회
     * @param themeId
     */
    @GetMapping("/{themeId}")
    public ResponseEntity<List<ThemeDto.ThemeMapListResponse>> themeMapList(@PathVariable("themeId") Long themeId) {

        return ResponseEntity.ok().body(ThemeDto.ThemeMapListResponse.of());
    }

    /**
     * BE-TH-0004 테마 리뷰 등록
     * @param themeId
     * @param storeId
     */
    @PostMapping("/{themeId}/stores/{storeId}")
    public ResponseEntity registThemeReview(@PathVariable("themeId") Long themeId,
                                            @PathVariable("storeId") Long storeId,
                                            @RequestBody ThemeDto.RegistThemeReviewPost registThemeReviewPost) {

        return ResponseEntity.ok().body(ThemeDto.RegistThemeReviewResponse.of());
    }

    //th-0005  가게 테마 조회
    @GetMapping("/{themeId}/stores/{storeId}/themeList")
    public ResponseEntity<?> attemptGetStoreTheme(@PathVariable("themeId") Long themeId, @PathVariable("storeId") Long storeId){

        List<ThemeDto.ThemeResponse> list = new ArrayList<>();
        list.add(ThemeDto.ThemeResponse.of());
        list.add(ThemeDto.ThemeResponse.of());
        return ResponseEntity.ok().body(new SingleListResponseDto<>(list));
    }

    //TH-0006 가게 테마 리뷰 조회
    @GetMapping("/{themeId}/stores/{storeId}/review")
    public ResponseEntity<?> attemptGetStoreThemeReview(@PathVariable("themeId") Long themeId, @PathVariable("storeId") Long storeId){

        List<ThemeDto.StoreThemeReviewResponse> list = new ArrayList<>();
        list.add(ThemeDto.StoreThemeReviewResponse.of());
        list.add(ThemeDto.StoreThemeReviewResponse.of());

        return ResponseEntity.ok().body(new MultiResponseDto<>(list, new Page() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public Page map(Function converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator iterator() {
                return null;
            }
        }));
    }

    //TH-0007 가게 테마 리뷰 수정
    @PatchMapping("/{themeId}/stores/{storeId}/reviews/{reviewId}")
    public ResponseEntity<?> attemptReviewUpdate(@PathVariable("themeId") Long themeId,
                                                 @PathVariable("storeId") Long storeId,
                                                 @PathVariable("reviewId") Long reviewId,
                                                 @RequestBody ThemeDto.ReviewPatch reviewPatch){

        return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
    }

    //TH-0008 가게 테마 리뷰 삭제
    @DeleteMapping("/{themeId}/stores/{storeId}/reviews/{reviewId}")
    public ResponseEntity<?> attemptReviewDelete(@PathVariable("themeId") Long themeId,
                                                 @PathVariable("storeId") Long storeId,
                                                 @PathVariable("reviewId") Long reviewId){
        return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
    }

    /**
     * BE-TH-0009
     * @param themeSearchGet 검색 파라미터
     */
    @GetMapping("/search")
    public ResponseEntity<ThemeDto.ThemeSearchResponse> themeSearch(@RequestBody ThemeDto.ThemeSearchGet themeSearchGet) {

        return ResponseEntity.ok().body(ThemeDto.ThemeSearchResponse.of());
    }

    //TH- 0010 테마 등록
    @PostMapping("/registration")
    public ResponseEntity<?> attemptThemeRegister(@RequestBody ThemeDto.ThemePost ThemePostDto){
        return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
    }

    //TH -0011 테마 찜 여부 조회
    @GetMapping("/{themeId}/users/{userId}/follows")
    public ResponseEntity<?> checkDibsTheme(@PathVariable("themeId") Long themeId,
                                            @PathVariable("userId") Long userId){

        return ResponseEntity.ok().body(new HashMap<>(){{put("isWishing",true);}});
    }

    /**
     * BE-TH-0012
     * @param themeId
     * @param userId
     */
    @PostMapping("/{themeId}/users/{userId}/wishes")
    public ResponseEntity<ThemeDto.RegistDibsThemeResponse> registDibsTheme(@PathVariable("themeId") Long themeId, @PathVariable("userId") Long userId) {

        return ResponseEntity.ok().body(ThemeDto.RegistDibsThemeResponse.of());
    }

    //TH -0013 테마 찜 취소
    @PatchMapping("/{themeId}/users/{userId}/unwish")
    public ResponseEntity cancelDibsTheme (@PathVariable("themeId") Long themeId,
                                           @PathVariable("userId") Long userId){

        return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
    }
    

    public Theme makeStubDataForTheme(Long id, String emoji, String title) {
        Theme stub = Theme.builder()
                .emoji(emoji)
                .title(title)
                .build();
        stub.setId(id);
        return stub;
    }

    public Store makeStubDataForStore(Long id, String title, String lat, String lng) {
        Store stub = Store.builder()
                .title(title)
                .lat(lat)
                .lng(lng)
                .build();
        stub.setId(id);
        return stub;
    }
}

