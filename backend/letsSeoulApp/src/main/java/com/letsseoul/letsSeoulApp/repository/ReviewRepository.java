package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("SELECT count(r.id) FROM Review r WHERE r.user.id = :userId")
    Long countByUserId(Long userId);

}
