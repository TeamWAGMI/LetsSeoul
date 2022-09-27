package com.letsseoul.letsSeoulApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PageInfo {
    private final int nowPage;
    private final int nowCount;
    private final int totalPage;
    private final long totalCount;
}
