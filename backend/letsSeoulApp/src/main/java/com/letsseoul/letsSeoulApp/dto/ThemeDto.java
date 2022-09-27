package com.letsseoul.letsSeoulApp.dto;

import com.letsseoul.letsSeoulApp.domain.Theme;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class ThemeDto {

    private ThemeDto() {
    }

    @Getter
    @RequiredArgsConstructor
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

}
