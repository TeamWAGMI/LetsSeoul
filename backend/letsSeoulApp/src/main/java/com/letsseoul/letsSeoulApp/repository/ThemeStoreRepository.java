package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.Store;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import com.letsseoul.letsSeoulApp.dto.store.StoreThemeResponse;
import com.letsseoul.letsSeoulApp.dto.store.StoreThemeReviewResponse;
import com.letsseoul.letsSeoulApp.dto.theme.PopularThemeListResponseDto;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeMapListResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

    @Query("select " +
            "distinct new com.letsseoul.letsSeoulApp.dto.store.StoreThemeResponse(t.id,t.emoji,t.title)" +
            "from ThemeStore ts " +
            "inner join Theme t " +
            "on ts.theme.id = t.id " +
            "where ts.store.id= :storeId")
    List<StoreThemeResponse> findDistinctByStoreId(@Param("storeId") Long storeId);

    @Query("SELECT " +
            "new com.letsseoul.letsSeoulApp.dto.theme.ThemeMapListResponseDto(s.id,s.itemid,s.title,s.lat,s.lng,count(r.id)) " +
            "FROM ThemeStore ts " +
            "INNER JOIN Store s " +
            "ON ts.store.id = s.id " +
            "left join Review r " +
            "on ts.id=r.themeStore.id and r.status ='E' " +
            "WHERE ts.theme.id = :themeId " +
            "GROUP BY s.id")
    List<ThemeMapListResponseDto> findByTheme(@Param("themeId") Long themeId);

    @Query("select " +
            "new com.letsseoul.letsSeoulApp.dto.store.StoreThemeReviewResponse(u.id,u.emoji,u.nickname,r.id,r.score,r.content,r.createdDatetime,r.modifiedDatetime) " +
            "from ThemeStore ts " +
            "inner join " +
            "Review r " +
            "on ts.id=r.themeStore.id " +
            "inner join User u on r.user.id=u.id " +
            "where ts.store.id = :storeId and r.status = 'E'" +
            "ORDER BY r.createdDatetime desc ")
    Page<StoreThemeReviewResponse> findByStoreId(@Param("storeId") Long storeId, Pageable pageable);
}
