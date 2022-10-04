package com.letsseoul.letsSeoulApp.dto.theme;

import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.domain.SuggestTheme;
import com.letsseoul.letsSeoulApp.domain.Theme;
import lombok.*;
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
    public static class RegistThemeResponse {
        private final Boolean success;

        public static RegistThemeResponse of() {
            return new RegistThemeResponse(true);
        }
    }


    @Getter
    @RequiredArgsConstructor  //가게 테마 조희 Response
    public static class ThemeResponse{
        private final Long id;

        private final String emoji;

        private final String title;

        private final Long count; //가게의 테마에서 리뷰 개수

        public static ThemeResponse of() {
            return new ThemeResponse(
                    1L,
                    "이모지",
                    "테마이름",
                    0L);
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class ReviewPatch{
        private final Long userId;
        private final Long reviewScore;
        private final String reviewContent;
        private final List<String> reviewImages;
    }

    @Getter
    @RequiredArgsConstructor
    public static class StoreThemeReviewResponse{
        private final Long userId;
        private final String userEmoji;
        private final String userNickname;
        private final Long reviewId;
        private final Long reviewScore;
        private final String reviewContent;
        private final String createDatetime;
        private final String modifiedDatetime;
        private final List<String> reviewImages;

        public static StoreThemeReviewResponse of(){
            return new StoreThemeReviewResponse(
                    1L,
                    "이모지",
                    "닉네임",
                    1L ,
                    1L,
                    "리뷰 내용",
                    "2022-12-06T22:40:01.156412",
                    "2022-12-06T22:40:01.156412",
                     new ArrayList<>(){{add("path");add("path");}}
            );
        }

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
        private final Long storeId;
        private final String itemId;
        private final String storeTitle;
        private final Integer reviewCount;
        private final String lat;
        private final String lng;

        public static List<ThemeMapListResponse> of() { // 어떻게 객체탐색을 해서 응답을 구성할 지 구체적인 구현이 필요
            // 현재는 일단 임의의 값들을 넣는다.

            List<ThemeDto.ThemeMapListResponse> collect = new ArrayList<>();
            collect.add(new ThemeMapListResponse(1L, "18577297", "카카오", 100, "33.450701", "126.570667"));
            collect.add(new ThemeMapListResponse(2L, "1449813286", "인프랩", 1000, "37.40045351022944", "127.10680496162932"));
            collect.add(new ThemeMapListResponse(3L, "720698281", "라인", 400, "37.39425685503581", "127.11202709961768"));
            collect.add(new ThemeMapListResponse(4L, "1449813286", "두나무", 200, "37.49564546933401", "127.02817125032831"));
            return collect;
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
    public static class ThemeSearchResponse<T> {
        private final List<T> content;
        private final PageInfo pageInfo;

        @Getter
        @RequiredArgsConstructor
        static class PageInfo {
            private final Integer nowPage;
            private final Integer nowSize;
            private final Integer totalPage;
            private final Long totalSize;
        }

        @Getter
        @RequiredArgsConstructor
        static class ListTheme {
            private final Long themeId;
            private final String themeEmoji;
            private final String themeTitle;
            private final Integer reviewCount;
        }

        public static ThemeSearchResponse of() {
            List<ListTheme> collect = new ArrayList<>();
            collect.add(new ListTheme(1L, "😀", "테마이름 짓기 어려워요", 100));
            collect.add(new ListTheme(2L, "😁", "테마이름 어려워요", 200));
            collect.add(new ListTheme(3L, "😂", "테마이름", 300));
            collect.add(new ListTheme(4L, "🤣", "짓기 어려워요", 400));
            collect.add(new ListTheme(5L, "😃", "이름 짓기 어려워요", 110));
            collect.add(new ListTheme(6L, "😅", "짓기 어려워요", 120));
            collect.add(new ListTheme(7L, "😆", "어려워요", 130));
            collect.add(new ListTheme(8L, "😎", "테마이름 짓기", 140));
            collect.add(new ListTheme(9L, "🤗", "이름 어려워요", 105));
            collect.add(new ListTheme(0L, "😍", "테마이름 짓기 어려", 106));
            collect.add(new ListTheme(11L, "🥰", "테마 짓기", 107));

            PageInfo pageInfo = new PageInfo(1, 10, 2, 11L);

            return new ThemeSearchResponse(collect, pageInfo);
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
