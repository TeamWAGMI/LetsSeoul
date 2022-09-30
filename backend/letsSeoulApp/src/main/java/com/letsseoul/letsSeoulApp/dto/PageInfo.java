package com.letsseoul.letsSeoulApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PageInfo {
    private final Integer nowPage;
    private final Integer nowCount;
    private final Integer totalPage;
    private final Long totalCount;
    private final Boolean hasNext;
    private final Boolean isFirst;
}
