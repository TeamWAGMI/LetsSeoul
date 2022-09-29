package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.follow.FollowDto;
import com.letsseoul.letsSeoulApp.dto.follow.FollowerResponseDto;
import com.letsseoul.letsSeoulApp.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.constraints.Positive;
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
    public ResponseEntity<FollowDto.FollowUserResponse> followUser(@PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(FollowDto.FollowUserResponse.of());
    }

    /**
     * BE-FO-0004
     * @param followUserId
     */
    @DeleteMapping("/{followUserId}")
    public ResponseEntity<FollowDto.UnfollowUserResponse> unfollowUser(@PathVariable("followUserId") Long followUserId) {

        return ResponseEntity.ok().body(FollowDto.UnfollowUserResponse.of());
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
    public ResponseEntity<MultiResponseDto<FollowerResponseDto>> getFollowerList(
            @PathVariable("followUserId") @Positive(message = "잘못된 회원 정보입니다.") Long followUserId,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value = "size", required = false) Integer size) {

        System.out.println("### FollowController.getFollowerList 시작");

        if (page != null && page <= 0) {
            System.out.println("### 에러1");
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

        System.out.println("page = " + page);
        System.out.println("size = " + size);
        System.out.println("followUserId = " + followUserId);


        MultiResponseDto<FollowerResponseDto> followerList = followService.getFollowerList(followUserId, page, size);

        System.out.println("### FollowController.getFollowerList 끝");
        return ResponseEntity.ok().body(followerList);
    }

}
