package com.letsseoul.letsSeoulApp.service;

import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.follow.FollowerResponseDto;
import com.letsseoul.letsSeoulApp.repository.FollowRepository;
import com.letsseoul.letsSeoulApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.letsseoul.letsSeoulApp.dto.FollowDto;
import org.springframework.transaction.annotation.Transactional;
import com.letsseoul.letsSeoulApp.domain.FollowUser;
import com.letsseoul.letsSeoulApp.domain.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    @Transactional
    public FollowDto.FollowUserReponse followUser(Long fromUserId, Long toUserId) {

        User fromUser = userRepository.findById(fromUserId).orElseThrow(() -> new RuntimeException("UserId가 없습니다."));
        User toUser = userRepository.findById(toUserId).orElseThrow(() -> new RuntimeException("UserId가 없습니다."));

        FollowUser followUser = FollowUser.builder()
                .fromUserId(fromUser)
                .toUserId(toUser)
                .build();
        followRepository.save(followUser);
        return FollowDto.FollowUserReponse.of();
    }

    public FollowDto.UnfollowUserResponse unfollowUser(Long fromUserId, Long toUserId) {

        User fromUser = userRepository.findById(fromUserId).orElseThrow(() -> new RuntimeException("UserId가 없습니다."));
        User toUser = userRepository.findById(toUserId).orElseThrow(() -> new RuntimeException("UserId가 없습니다."));
        followRepository.delete(followRepository.findByFromUserIdAndToUserId(fromUser, toUser));
        return FollowDto.UnfollowUserResponse.of();
    }

      public MultiResponseDto<FollowDto.FollowingListResponse> getFollowerList(Long userId, Pageable pageable){
        User findUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("UserId가 없습니다."));
        Page<FollowUser> followUsers = followRepository.findByFromUserId(findUser,pageable); //팔로워 정보
        List<Long> userList = followUsers.stream().map(f -> f.getToUserId().getId()).collect(Collectors.toList()); //팔로잉 유저 아이디
        List<User> user = userRepository.findByIdIn(userList,userId);
        List<Long> countList= followRepository.countByToUserIds(userList);
        return FollowDto.FollowingListResponse.of(followUsers,user,countList);
    }


    public FollowDto.CheckFollowing checkFollowing(Long userId, Long followUserId) {
        User fromUser = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("UserId가 없습니다."));
        User toUser = userRepository.findById(followUserId).orElseThrow(() -> new RuntimeException("UserId가 없습니다."));
        return FollowDto.CheckFollowing.of(followRepository.countByFromUserIdAndToUserId(fromUser, toUser));

    }

    public FollowDto.CountFollowingsAndFollowersResponse countFollowingsAndFollowers(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("UserId가 없습니다."));
        return FollowDto.CountFollowingsAndFollowersResponse.of(followRepository.countByFromUserId(user), followRepository.countByToUserId(user));
    }

    /*public void test(Long id) {
        Object[] followUser = followRepository.getFollowUser();
        System.out.println("---------------");
        System.out.println(Arrays.toString(followUser));
    }*/

    /**
     * BE-FO-0006
     */
    public MultiResponseDto<FollowerResponseDto> getFollowerList(Long followUserId, Integer page, Integer size) {

        Page<FollowerResponseDto> followerResponseDtos = followRepository.findFollowerList(
                followUserId, PageRequest.of(page, size, Sort.by("id").ascending()));

        for (FollowerResponseDto followerDto : followerResponseDtos) {
            followerDto.setFollowerCount(followRepository.countByToUserId(followerDto.getUserId()));
        }

        return new MultiResponseDto<>(followerResponseDtos);

    }
}
