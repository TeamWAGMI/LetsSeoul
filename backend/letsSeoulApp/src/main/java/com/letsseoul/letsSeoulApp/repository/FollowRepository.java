package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.FollowUser;
import com.letsseoul.letsSeoulApp.dto.follow.FollowerResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<FollowUser, Long> {

    /**
     * BE-FO-0006
     */
    @Query(value =
            "SELECT NEW com.letsseoul.letsSeoulApp.dto.follow.FollowerResponseDto(u.id, u.emoji, u.nickname) " +
            "FROM User u WHERE u.id IN (SELECT f.fromUser.id FROM FollowUser f WHERE f.toUser.id = :followUserId)")
    Page<FollowerResponseDto> findFollowerList(Long followUserId, Pageable pageable);

    Long countByToUserId(Long userId);


}
