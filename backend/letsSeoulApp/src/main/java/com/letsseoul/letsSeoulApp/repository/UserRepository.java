package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndOrigin(String username, String origin);


    @Query("select u from User u inner join FollowUser f on u.id =f.toUserId.id " +
            "where f.toUserId.id in (:userId) and f.fromUserId.id = :fromUserId order by f.createdDatetime desc")
    List<User> findByIdIn(List<Long> userId,@Param("fromUserId") Long fromUserId);
}
