package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThemeCustomRepository {
    Page<Tuple> findDynamicQuery(ThemeDto.ThemeSearchPost themeSearchGet, Pageable pageable);
    Long findReviewCount(Long themeId);
}
