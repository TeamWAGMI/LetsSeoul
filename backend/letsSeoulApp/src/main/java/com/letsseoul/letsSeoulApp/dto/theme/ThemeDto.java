package com.letsseoul.letsSeoulApp.dto.theme;




import com.letsseoul.letsSeoulApp.domain.SuggestTheme;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.querydsl.core.Tuple;
import lombok.*;
import org.springframework.data.domain.Page;


import java.util.ArrayList;
import java.util.List;


public class ThemeDto {

    private ThemeDto() {
    }

    @Getter
    @RequiredArgsConstructor
    public static class RegistThemePost {
        private final String themeTitle;
        private final String themeContent;
        public SuggestTheme toEntity() {
            return SuggestTheme.builder()
                    .title(this.themeTitle)
                    .content(this.themeContent)
                    .build();
        }
    }
    @Getter
    @RequiredArgsConstructor
    public static class ThemeInfoResponse{
        private final Long themeId;
        private final String themeEmoji;
        private final String themeTitle;

        public static ThemeInfoResponse of(Theme theme){
            return new ThemeInfoResponse(theme.getId(),
                    theme.getEmoji(),
                    theme.getTitle());
        }
    }
    @Getter
    @RequiredArgsConstructor
    public static class RegistThemeResponse {
        private final Boolean success;

        public static RegistThemeResponse of() {
            return new RegistThemeResponse(true);
        }

    }

    /*@Getter
    @RequiredArgsConstructor  //가게 테마 조희 Response
    public static class ThemeResponse{
        private final Long id;

        private final String emoji;

        private final String title;

        private final Long count; //가게의 테마에서 리뷰 개수

        public static ThemeResponse of() {
            return new ThemeResponse(
                    1
                    "이모지",
                    "테마이름",
                    0L);
        }
    }*/

    @Getter
    @RequiredArgsConstructor
    public static class ReviewPatch{
        private final Long userId;
        private final Long reviewScore;
        private final String reviewContent;
        private final List<String> reviewImages;
    }


    // BE-TH-0001
    @Getter
    @RequiredArgsConstructor
    @Deprecated
    public static class RecommendedThemesListResponse {
        private final Long id;
        private final String emoji;
        private final String title;
        private final Integer count;

        public static List<RecommendedThemesListResponse> of(List<Theme> themes) {

            List<RecommendedThemesListResponse> collect = new ArrayList<>();
            for (Theme t : themes) {
                RecommendedThemesListResponse listOfRecommendedThemesResponse = new RecommendedThemesListResponse(
                        t.getId(),
                        t.getEmoji(),
                        t.getTitle(),
                        t.getThemeStoreList().size()
                );
                collect.add(listOfRecommendedThemesResponse);
            }
            return collect;
        }
    }

    //BE-TH-0002
    @Getter
    @RequiredArgsConstructor
    public static class PopularThemesListResponse {
        private final Long id;
        private final String emoji;
        private final String title;
        private final Integer count;

        public static List<PopularThemesListResponse> of(List<Theme> themes) {

            List<PopularThemesListResponse> collect = new ArrayList<>();
            for (Theme t : themes) {
                PopularThemesListResponse listOfPopularThemesResponse = new PopularThemesListResponse(
                        t.getId(),
                        t.getEmoji(),
                        t.getTitle(),
                        t.getThemeStoreList().size()
                );
                collect.add(listOfPopularThemesResponse);
            }

            return collect;
        }
    }

    // BE-TH-0003
    @Getter
    @RequiredArgsConstructor
    public static class ThemeMapListResponse {
        private final MapInfoResponse mapInfo;
        private final List<ThemeMapListResponseDto> stores;
        @Getter
        @RequiredArgsConstructor
        static class MapInfoResponse {
            private final Double centerLat;
            private final Double centerLng;
            private final Integer level;
        }
        public static ThemeMapListResponse of(List<ThemeMapListResponseDto> themeStoreList) { // 어떻게 객체탐색을 해서 응답을 구성할 지 구체적인 구현이 필요
            // centerLat/Lng & level 구하기
            /* 한국 위도 범위 33~43, 124~132. 넉넉히 +-1씩 잡는다. */
            Double maxLat = 32.0D;
            Double minLat = 44.0D;
            Double maxLng = 123.0D;
            Double minLng = 131.0D;
            for (ThemeMapListResponseDto t : themeStoreList) {
                Double nowLat = Double.valueOf(t.getLat());
                Double nowLng = Double.valueOf(t.getLng());

                maxLat = Math.max(nowLat, maxLat);
                minLat = Math.min(nowLat, minLat);
                maxLng = Math.max(nowLng, maxLng);
                minLng = Math.min(nowLng, minLng);
            }
            Double centerLat = minLat + (maxLat - minLat) / 2;
            Double centerLng = minLng + (maxLng - minLng) / 2;
            Integer level = 6; //임시값

            //위에서 나온값을 토대로 해당 값 세팅
            MapInfoResponse mapInfoResponse = new MapInfoResponse(centerLat, centerLng, level);

            return new ThemeMapListResponse(mapInfoResponse,themeStoreList);
        }
    }

    // BE-TH-0004
    @Getter
    @RequiredArgsConstructor
    public static class RegistThemeReviewPost {
        private final RegistThemeReviewPostStore store;
        private final RegistThemeReviewPostReview review;

        @Getter
        @RequiredArgsConstructor
        public static class RegistThemeReviewPostStore {
            private final String itemid;
            private final String title;
            private final String address;
            private final String lat;
            private final String lng;
        }
        @Getter
        @RequiredArgsConstructor
        public static class RegistThemeReviewPostReview {
            private final Integer score;
            private final String content;
            private final String[] images; // 이미지는 나중에 붙이기로 했다.
        }
    }
    @Getter
    @RequiredArgsConstructor
    public static class RegistThemeReviewResponse {
        private final Boolean success;

        public static RegistThemeReviewResponse of() {
            return new RegistThemeReviewResponse(true);
        }
    }

    // BE-TH-0009
    @Getter
    @RequiredArgsConstructor
    public static class ThemeSearchPost {
        private final String keyword;
        private final String[] who;
        private final String[] what;
        private final String[] where;
    }
    @Getter
    @RequiredArgsConstructor
    public static class ThemeSearchResponse {
        private final Long themeId;
        private final String themeEmoji;
        private final String themeTitle;
        private final Long reviewCount;

        public static MultiResponseDto<ThemeSearchResponse> of(Page<Tuple> page) {
            List<ThemeSearchResponse> themeSearchResponses =new ArrayList<>();
            for(Tuple content:page.getContent()){
                themeSearchResponses.add(new ThemeSearchResponse(
                        content.get(0,Long.class),
                        content.get(1,String.class),
                        content.get(2,String.class),
                        content.get(3,Long.class)
                ));
            }
            return new MultiResponseDto<>(themeSearchResponses,page);
        }
    }
    //TH-0011
    @Getter
    @RequiredArgsConstructor
    public static class checkDibsThemeResponse {
        private final Boolean isWishing;

        public static checkDibsThemeResponse of(Boolean _flag) {
            return new checkDibsThemeResponse(_flag);
        }
    }

    // BE-TH-0012
    @Getter
    @RequiredArgsConstructor
    public static class RegistDibsThemeResponse {
        private final Boolean success;

        public static RegistDibsThemeResponse of() {
            return new RegistDibsThemeResponse(true);
        }
    }
    // TH-0013
    @Getter
    @RequiredArgsConstructor
    public static class cancelDibsThemeResponse {
        private final Boolean success;

        public static cancelDibsThemeResponse of() {
            return new cancelDibsThemeResponse(true);
        }
    }
}
