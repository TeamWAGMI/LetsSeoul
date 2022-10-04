package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import com.letsseoul.letsSeoulApp.dto.theme.PopularThemeListResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ThemeStoreRepository extends JpaRepository<ThemeStore, Long> {

    // TH-0001
    @Query("SELECT COUNT(ts.id) FROM ThemeStore ts WHERE ts.theme.id = :themeId ")
    Long countByThemeId(Long themeId);

    // TH-0009
    @Query("SELECT COUNT(ts.id) FROM ThemeStore ts WHERE ts.theme.id = :themeId GROUP BY ts.theme.id ")
    Optional<Long> countStoreByThemeId(Long themeId);

    // TH-0002
    @Query("SELECT " +
            "new com.letsseoul.letsSeoulApp.dto.theme.PopularThemeListResponseDto(ts.theme.id, t.emoji, t.title, COUNT(ts.id)) " +
            "FROM ThemeStore ts " +
            "LEFT JOIN Theme t " +
            "ON ts.theme.id = t.id " +
            "GROUP BY ts.theme.id")
    List<PopularThemeListResponseDto> countAllByGroupByThemeId();
}
