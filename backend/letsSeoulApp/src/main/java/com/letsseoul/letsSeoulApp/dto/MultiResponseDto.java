package com.letsseoul.letsSeoulApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
public class MultiResponseDto<T>{

    private  List<T> content;
    private  PageInfo pageInfo;

    public MultiResponseDto(List<T> content, Page<?> page,Boolean hasNext,Boolean isFirst) {
        this.content = content;
        this.pageInfo = new PageInfo(page.getNumber() + 1,
                page.getSize(), page.getTotalPages(),page.getTotalElements(),hasNext,isFirst);
    }
}
