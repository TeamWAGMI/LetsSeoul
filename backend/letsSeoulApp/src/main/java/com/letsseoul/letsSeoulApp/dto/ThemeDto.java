package com.letsseoul.letsSeoulApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class ThemeDto {

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

        public static ThemeResponse of(){
            return new ThemeResponse(
                    1L,
                    "이모지",
                    "테마이름",
                    0L);
        };
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

}
