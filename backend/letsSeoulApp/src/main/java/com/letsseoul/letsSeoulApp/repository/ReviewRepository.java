package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.dto.location.ReviewPlaceResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // CU-0001
    @Query("SELECT count(r.id) FROM Review r WHERE r.user.id = :userId AND r.status = 'E' ")
    Long countByUserId(Long userId);

    //LO-0001
    @Query("SELECT " +
            "new com.letsseoul.letsSeoulApp.dto.location.ReviewPlaceResponseDto(s.id, s.title, count(r.id), s.lat, s.lng) " +
            "FROM Review r " +
            "LEFT JOIN ThemeStore ts ON r.themeStore.id = ts.id " +
            "INNER JOIN Store s ON ts.store.id = s.id " +
            "WHERE r.user.id = :userId AND r.status = 'E' " +
            "GROUP BY s.id ")
    List<ReviewPlaceResponseDto> findReviewPlaceByUserId(Long userId);
}
