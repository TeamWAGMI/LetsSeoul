package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.dto.LocationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    //LO-0001 특정 유저 리뷰 장소 조회
    @GetMapping("/{userId}/review")
    public ResponseEntity<?> userReviewPlaceSearch(@PathVariable("userId") Long userId){

        return ResponseEntity.ok().body(LocationDto.userPlaceSearchResponse.of());
    }
    //LO-0002 특정유저 찜 장소 조회
    @GetMapping("/{userId}/wish")
    public ResponseEntity<?> userReviewDibsSearch(@PathVariable("userId") Long userId){

        return ResponseEntity.ok().body(LocationDto.userPlaceSearchResponse.of());
    }
}
