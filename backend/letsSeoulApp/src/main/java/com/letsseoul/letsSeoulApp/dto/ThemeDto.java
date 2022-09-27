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
    public static class ListOfRecommendedThemesResponse {
        private final Long id;
        private final String emoji;
        private final String title;
        private final Integer count;

        public static List<ListOfRecommendedThemesResponse> of(List<Theme> themes) {

            List<ListOfRecommendedThemesResponse> collect = new ArrayList<>();
            for (Theme t : themes) {
                ListOfRecommendedThemesResponse listOfRecommendedThemesResponse = new ListOfRecommendedThemesResponse(
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
    public static class ListOfPopularThemesResponse {
        private final Long id;
        private final String emoji;
        private final String title;
        private final Integer count;

        public static List<ListOfPopularThemesResponse> of(List<Theme> themes) {

            List<ListOfPopularThemesResponse> collect = new ArrayList<>();
            for (Theme t : themes) {
                ListOfPopularThemesResponse listOfPopularThemesResponse = new ListOfPopularThemesResponse(
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

}
