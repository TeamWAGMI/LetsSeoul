package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.config.auth.LoginUser;
import com.letsseoul.letsSeoulApp.config.auth.dto.SessionUser;
import com.letsseoul.letsSeoulApp.dto.FollowDto;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;
    /**
     * BE-FO-0001
     * @param userId
     */
    @GetMapping("/{userId}/count")
    public ResponseEntity<FollowDto.CountFollowingsAndFollowersResponse> countFollowingsAndFollowers(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok().body(followService.countFollowingsAndFollowers(userId));
    }

    /**
     * BE-FO-0002
     * @param followUserId
     */
    @GetMapping("/{followUserId}/check")
    public ResponseEntity<FollowDto.CheckFollowing> checkFollowing(@LoginUser SessionUser user,@PathVariable("followUserId") Long followUserId) {
        return ResponseEntity.ok().body(followService.checkFollowing(1L,followUserId));
    }

    /**
     * BE-FO-0003 유저 팔로우
     * @param followUserId
     */
    @PostMapping("/{followUserId}")
    public ResponseEntity<FollowDto.FollowUserReponse> followUser(@LoginUser SessionUser user, @PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(followService.followUser(2L, followUserId));
    }

    /**
     * BE-FO-0004
     * @param followUserId
     */
    @DeleteMapping("/{followUserId}")
    public ResponseEntity<FollowDto.UnfollowUserResponse> unfollowUser(@LoginUser SessionUser user,@PathVariable("followUserId") Long followUserId) {


        return ResponseEntity.ok().body(followService.unfollowUser(1L,followUserId));
    }

    /**
     * BE-FO-0005  팔로우 목록조회
     *
     * @param followUserId
     */
    @GetMapping("/{followUserId}/followings")
    public ResponseEntity<MultiResponseDto<FollowDto.FollowingListResponse>> getFollowingList(
            @LoginUser SessionUser user,
            @PathVariable("followUserId") Long followUserId,
            @RequestParam(defaultValue = "1",name ="page") Integer page,
            @RequestParam(defaultValue = "10",name ="page") Integer size) {
        return ResponseEntity.ok().body( followService.getFollowerList(followUserId, PageRequest.of(page-1,size, Sort.by("createdDatetime").descending())));
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
