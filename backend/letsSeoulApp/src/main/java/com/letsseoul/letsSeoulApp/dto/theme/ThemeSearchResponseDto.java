package com.letsseoul.letsSeoulApp.dto.theme;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class ThemeSearchResponseDto {
    private final Long themeId;
    private final String themeEmoji;
    private final String themeTitle;
    @Setter private Long storeCount;
}
