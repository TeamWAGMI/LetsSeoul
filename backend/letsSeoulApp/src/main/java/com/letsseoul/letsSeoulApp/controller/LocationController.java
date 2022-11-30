package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.dto.location.DibsPlaceResponseDto;
import com.letsseoul.letsSeoulApp.dto.location.ReviewPlaceResponseDto;
import com.letsseoul.letsSeoulApp.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;

    /**
     * LO-0001 특정유저 리뷰 장소 조회
     * 특정 유저가 리뷰를 남긴 장소들을 조회하는 api
     */
    @GetMapping("/{userId}/reviews")
    public List<ReviewPlaceResponseDto> listupReviewPlace(@PathVariable("userId") @Positive Long userId) {

        return locationService.listupReviewPlace(userId);
    }

    /**
     * LO-0002 특정유저 찜 장소 조회
     * 특정 유저가 찜한 장소들을 조회하는 api
     */
    @GetMapping("/{userId}/wishes")
    public List<DibsPlaceResponseDto> listupDibsPlace(@PathVariable("userId") @Positive Long userId) {

        return locationService.listupDibsPlace(userId);
    }
}
