package com.letsseoul.letsSeoulApp.controller;


import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.store.StoreDto;
import com.letsseoul.letsSeoulApp.dto.store.StoreThemeResponse;
import com.letsseoul.letsSeoulApp.dto.store.StoreThemeReviewResponse;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import com.letsseoul.letsSeoulApp.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

       private final StoreService storeService;

      //ST-0001 가게 정보 조회
      @GetMapping("/{storeId}")
      public ResponseEntity<?>  storeInformationSearch(@PathVariable("storeId") Long storeId){

          return ResponseEntity.ok().body(StoreDto.StoreInformationReponse.of());
      }

      //ST-0002 가게찜 여부 조회
      @GetMapping("/{storeId}/follows")
      public ResponseEntity<?>  storeDibsSearch(@PathVariable("storeId") Long storeId){

          return ResponseEntity.ok().body(new HashMap<>(){{put("isWishing",true);}});
      }

      //ST-0003 가게찜 등록
      @PostMapping("/{storeId}/wishes")
      public ResponseEntity<?>  storeDibsRegister(@PathVariable("storeId") Long storeId){

          return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
      }
      //ST-0004 가게찜 취소
      @DeleteMapping("/{storeId}/wishes")
      public ResponseEntity<?>  storeDibsCancel(@PathVariable("storeId") Long storeId){

          return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
      }
    //th-0005  가게 테마 조회
    @GetMapping("/{storeId}/themeList")
    public ResponseEntity<List<StoreThemeResponse>> attemptGetStoreTheme(@PathVariable("storeId") Long storeId){
        return ResponseEntity.ok().body(storeService.attemptGetStoreTheme(storeId));
    }

    //TH-0006 가게 테마 리뷰 조회
    @GetMapping("/{storeId}/review")
    public ResponseEntity<MultiResponseDto<StoreThemeReviewResponse>> attemptGetStoreThemeReview(
            @PathVariable("storeId") Long storeId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size){
        return ResponseEntity.ok().body(storeService.attemptGetStoreThemeReview(storeId, PageRequest.of(page-1,size)));
    }

    //TH-0007 가게 테마 리뷰 수정
    @PatchMapping("/reviews/{reviewId}")
    public ResponseEntity<StoreDto.UpdateOrDeleteReviewResponse> attemptReviewUpdate(
                                                 @PathVariable("reviewId") Long reviewId,
                                                 @RequestBody ThemeDto.ReviewPatch reviewPatch){
        return ResponseEntity.ok().body(storeService.attemptReviewUpdate(3L,reviewId,reviewPatch));
    }

    //TH-0008 가게 테마 리뷰 삭제
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<StoreDto.UpdateOrDeleteReviewResponse> attemptReviewDelete(@PathVariable("reviewId") Long reviewId){
        return ResponseEntity.ok().body(storeService.attemptReviewDelete(reviewId));
    }


}
