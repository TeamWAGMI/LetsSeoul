package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.dto.FollowDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follows")
@RequiredArgsConstructor
public class FollowController {

    /**
     * BE-FO-0001
     * @param userId
     */
    @GetMapping("/{userId}/count")
    public ResponseEntity<FollowDto.CountFollowingsAndFollowersResponse> countFollowingsAndFollowers(@PathVariable("userId") Long userId) {

        return ResponseEntity.ok().body(FollowDto.CountFollowingsAndFollowersResponse.of());
    }

    /**
     * BE-FO-0002
     * @param followUserId
     */
    @GetMapping("/{followUserId}/check")
    public ResponseEntity<FollowDto.CheckFollowing> checkFollowing(@PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(FollowDto.CheckFollowing.of());
    }

    /**
     * BE-FO-0003
     * @param followUserId
     */
    @PostMapping("/{followUserId}")
    public ResponseEntity<FollowDto.FollowUser> followUser(@PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(FollowDto.FollowUser.of());
    }

    /**
     * BE-FO-0004
     * @param followUserId
     */
    @DeleteMapping("/{followUserId}")
    public ResponseEntity<FollowDto.UnfollowUser> unfollowUser(@PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(FollowDto.UnfollowUser.of());
    }

    /**
     * BE-FO-0005
     * @param followUserId
     */
    @GetMapping("/{followUserId}/followings")
    public ResponseEntity<List<FollowDto.FollowingListResponse>> getFollowingList(@PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(FollowDto.FollowingListResponse.of());
    }

    /**
     * BE-FO-0006
     * @param followUserId
     */
    @GetMapping("/{followUserId}/followers")
    public ResponseEntity<List<FollowDto.FollowerListResponse>> getFollowerList(@PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(FollowDto.FollowerListResponse.of());
    }

}
