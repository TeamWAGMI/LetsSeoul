package com.letsseoul.letsSeoulApp.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MultiResponseDto<T>{

    private List<T> content;
    private PageInfo pageInfo;

    public MultiResponseDto(List<T> content, Page page) {
        this.content = content;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalPages(),page.getTotalElements(), page.isFirst(), page.hasNext());
    }

    public MultiResponseDto(Page<T> page) {
        this.content = page.getContent();
        this.pageInfo = new PageInfo(
                page.getNumber() + 1,
                page.getSize(),
                page.getTotalPages(),
                page.getTotalElements(),
                page.isFirst(),
                page.hasNext());
    }

}
