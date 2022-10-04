package com.letsseoul.letsSeoulApp.dto.theme;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThemeResponseDto {
    private Long themeId;
    private String themeEmoji;
    private String themeTitle;
}
