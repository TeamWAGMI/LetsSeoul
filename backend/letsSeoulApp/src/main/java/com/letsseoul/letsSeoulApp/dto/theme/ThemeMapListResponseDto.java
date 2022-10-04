package com.letsseoul.letsSeoulApp.dto.theme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ThemeMapListResponseDto {
    private final Long storeId;
    private final String itemId;
    private final String storeTitle;
    private final String lat;
    private final String lng;
    private final Long reviewCount;
}
