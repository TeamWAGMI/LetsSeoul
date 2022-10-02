package com.letsseoul.letsSeoulApp.dto.theme;

import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.PageInfo;
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
    public static class ThemePost{
        private final String themeName;
        private final String themeContent;
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
    @ToString
    public static class RegistThemeReviewPost {
        private final Integer score;
        private final String content;
        private final String[] images; // 이미지는 나중에 붙이기로 했다.
    }
    @Getter
    @RequiredArgsConstructor
    public static class RegistThemeReviewResponse {
        private final Boolean success;

        public static RegistThemeReviewResponse of(Review savedReview) {
            if (null != savedReview) {
                return new RegistThemeReviewResponse(true);
            }
            else {
                return new RegistThemeReviewResponse(false);
            }
        }
    }

    // BE-TH-0009
    @Getter
    @RequiredArgsConstructor
    public static class ThemeSearchGet {
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
