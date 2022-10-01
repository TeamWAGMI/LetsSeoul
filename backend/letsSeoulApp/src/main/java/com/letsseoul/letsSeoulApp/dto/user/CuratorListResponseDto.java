package com.letsseoul.letsSeoulApp.dto.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// CU-0001
@Getter
@RequiredArgsConstructor
public class CuratorListResponseDto {

    private final Long userId;
    private final String emoji;
    private final String nickname;
    @Setter private Long reviewCount;
}
