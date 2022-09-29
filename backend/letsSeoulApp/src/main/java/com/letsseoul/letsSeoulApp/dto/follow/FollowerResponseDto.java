package com.letsseoul.letsSeoulApp.dto.follow;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
public class FollowerResponseDto {
    private final Long userId;
    private final String emoji;
    private final String nickname;
    @Setter private Long followerCount;
}
