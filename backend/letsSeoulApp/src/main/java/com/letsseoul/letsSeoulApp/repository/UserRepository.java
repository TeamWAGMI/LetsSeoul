package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsernameAndOrigin(String username, String origin);


    @Query("SELECT u FROM User u INNER JOIN FollowUser f ON u.id =f.toUser.id " +
            "WHERE f.toUser.id IN (:userId) AND f.fromUser.id = :fromUserId ORDER BY f.createdDatetime DESC ")
    List<User> findByIdIn(List<Long> userId,@Param("fromUserId") Long fromUserId);


}
