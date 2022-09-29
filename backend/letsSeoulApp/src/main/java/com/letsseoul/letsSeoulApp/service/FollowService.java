package com.letsseoul.letsSeoulApp.service;

import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.follow.FollowerResponseDto;
import com.letsseoul.letsSeoulApp.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

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
