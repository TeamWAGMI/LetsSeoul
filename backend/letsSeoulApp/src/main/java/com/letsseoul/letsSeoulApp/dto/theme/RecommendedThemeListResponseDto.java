package com.letsseoul.letsSeoulApp.dto.theme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class RecommendedThemeListResponseDto {

    private final Long id;
    private final String emoji;
    private final String title;
    @Setter
    private Long storeCount;
}
