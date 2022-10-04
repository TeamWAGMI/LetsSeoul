package com.letsseoul.letsSeoulApp.service;

import com.letsseoul.letsSeoulApp.dto.location.DibsPlaceResponseDto;
import com.letsseoul.letsSeoulApp.dto.location.ReviewPlaceResponseDto;
import com.letsseoul.letsSeoulApp.repository.FollowStoreRepository;
import com.letsseoul.letsSeoulApp.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class LocationService {

    private final ReviewRepository reviewRepository;
    private final FollowStoreRepository followStoreRepository;

    // LO-0001
    public List<ReviewPlaceResponseDto> listupReviewPlace(Long userId) {

        return reviewRepository.findReviewPlaceByUserId(userId);
    }

    // LO-0002
    public List<DibsPlaceResponseDto> listupDibsPlace(Long userId) {

        return followStoreRepository.findDibsPlaceByUserId(userId);
    }
}
