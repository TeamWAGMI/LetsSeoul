package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.FollowStore;
import com.letsseoul.letsSeoulApp.dto.location.DibsPlaceResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowStoreRepository extends JpaRepository<FollowStore, Long> {

    // ST-0002
    Optional<FollowStore> findByUserIdAndStoreId(Long userId, Long storeId);

    // LO-0002
    @Query("SELECT fs " +
            "FROM FollowStore fs " +
            "LEFT JOIN Store s ON fs.store.id = s.id " +
            "LEFT JOIN ThemeStore ts ON s.id = ts.store.id " +
            "INNER JOIN Review r ON ts.id = r.themeStore.id AND r.status = 'E' " +
            "WHERE fs.user.id = :userId " +
            "GROUP BY s.id ")
    List<DibsPlaceResponseDto> findDibsPlaceByUserId(Long userId);
}
