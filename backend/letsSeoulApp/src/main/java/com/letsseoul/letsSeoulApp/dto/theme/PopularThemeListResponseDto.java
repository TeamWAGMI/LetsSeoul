package com.letsseoul.letsSeoulApp.dto.theme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PopularThemeListResponseDto {

    private final Long id;
    private final String emoji;
    private final String title;
    private final Long count;
}
