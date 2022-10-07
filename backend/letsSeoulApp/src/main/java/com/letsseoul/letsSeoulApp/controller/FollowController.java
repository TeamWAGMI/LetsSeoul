package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.follow.FollowDto;
import com.letsseoul.letsSeoulApp.dto.follow.FollowerResponseDto;
import com.letsseoul.letsSeoulApp.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import com.letsseoul.letsSeoulApp.config.auth.LoginUser;
import com.letsseoul.letsSeoulApp.config.auth.dto.SessionUser;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Positive;

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
        return ResponseEntity.ok().body(followService.checkFollowing(user.getId(),followUserId));
    }

    /**
     * BE-FO-0003 유저 팔로우
     * @param followUserId
     */
    @PostMapping("/{followUserId}")
    public ResponseEntity<FollowDto.FollowUserResponse> followUser(@LoginUser SessionUser user, @PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(followService.followUser(user.getId(), followUserId));
    }

    /**
     * BE-FO-0004
     * @param followUserId
     */
    @DeleteMapping("/{followUserId}")
    public ResponseEntity<FollowDto.UnfollowUserResponse> unfollowUser(@LoginUser SessionUser user,@PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(followService.unfollowUser(user.getId(),followUserId));
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
        return ResponseEntity.ok().body(followService.getFollowerList(followUserId, PageRequest.of(page-1,size, Sort.by("createdDatetime").descending())));
    }

    /**
     * BE-FO-0006
     * @param followUserId
     */
    @GetMapping("/{followUserId}/followers")
    public ResponseEntity<MultiResponseDto<FollowerResponseDto>> getFollowerList(
            @PathVariable("followUserId") @Positive(message = "잘못된 회원 정보입니다.") Long followUserId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {


        if (page != null && page <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
        }
        if (size != null && size <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
        }

        if (page == null) {
            page = 0;
        }
        else {
            page--;
        }
        if (size == null) {
            size = 20;
        }

        MultiResponseDto<FollowerResponseDto> followerList = followService.getFollowerList(followUserId, page, size);

        return ResponseEntity.ok().body(followerList);
    }

}
