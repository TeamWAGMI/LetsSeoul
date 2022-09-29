package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.FollowUser;
import com.letsseoul.letsSeoulApp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Collection;
import java.util.List;

public interface FollowRepository extends JpaRepository<FollowUser,Long> {

    FollowUser findByFromUserIdAndToUserId(User fromUserId, User toUserId);
    Page<FollowUser> findByFromUserId(User fromUserId,Pageable pageable);

    @Query("select count(f) from FollowUser f where f.toUserId.id in(:toUserId) group by f.toUserId order by f.createdDatetime desc")
    List<Long> countByToUserIds(List<Long> toUserId);

    Long countByFromUserIdAndToUserId(User fromUserId, User toUserId);
    Integer countByFromUserId(User fromUserId);
    Integer countByToUserId(User toUserId);


  /*  @Query("select u,h from FollowUser h inner join User u on h.fromUserId.id=u.id")
    Object[] getFollowUser();*/


}
