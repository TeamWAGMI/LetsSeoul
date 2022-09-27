package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.domain.Store;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import com.letsseoul.letsSeoulApp.dto.ThemeDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
    
    
    /**
     * BE-TH-0009
     * @param themeSearchGet 검색 파라미터
     */
    @GetMapping("/search")
    public ResponseEntity<ThemeDto.ThemeSearchResponse> themeSearch(@RequestBody ThemeDto.ThemeSearchGet themeSearchGet) {

        return ResponseEntity.ok().body(ThemeDto.ThemeSearchResponse.of());
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
