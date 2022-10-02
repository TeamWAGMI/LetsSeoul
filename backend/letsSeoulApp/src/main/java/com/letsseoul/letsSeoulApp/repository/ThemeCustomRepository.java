package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.Theme;
import com.querydsl.core.Tuple;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ThemeCustomRepository {
    Page<Tuple> findDynamicQuery(String keyword, String[] who, String[] what, String[] where, Pageable pageable);
}
