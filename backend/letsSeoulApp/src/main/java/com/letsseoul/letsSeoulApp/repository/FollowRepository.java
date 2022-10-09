package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.FollowUser;
import com.letsseoul.letsSeoulApp.dto.follow.FollowerResponseDto;
import com.letsseoul.letsSeoulApp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<FollowUser, Long> {

    /**
     * BE-FO-0005
     */
    //FollowUser findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);
    Optional<FollowUser> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);
    Page<FollowUser> findByFromUserId(Long fromUserId,Pageable pageable);

    @Query("select count(f) from FollowUser f where f.toUser.id in(:toUserId) group by f.toUser,f.createdDatetime order by f.createdDatetime desc")
    List<Long> countByToUserIds(List<Long> toUserId);

    Long countByFromUserIdAndToUserId(Long fromUserId, Long toUserId);
    Long countByFromUserId(Long fromUserId);
    Long countByToUserId(Long toUserId);

    /**
     * BE-FO-0006
     */
    @Query(value =
            "SELECT NEW com.letsseoul.letsSeoulApp.dto.follow.FollowerResponseDto(u.id, u.emoji, u.nickname) " +
            "FROM User u WHERE u.id IN (SELECT f.fromUser.id FROM FollowUser f WHERE f.toUser.id = :followUserId)")
    Page<FollowerResponseDto> findFollowerList(Long followUserId, Pageable pageable);

}
