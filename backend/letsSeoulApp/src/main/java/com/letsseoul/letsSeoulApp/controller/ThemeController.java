package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.SingleListResponseDto;
import com.letsseoul.letsSeoulApp.dto.ThemeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.function.Function;

@RestController
@RequestMapping("/api/v1/themes")
public class ThemeController {


    //th-0005  가게 테마 조회
    @GetMapping("/{themeId}/stores/{storeId}/themeList")
    public ResponseEntity<?> attemptGetStoreTheme(@PathVariable("themeId") Long themeId, @PathVariable("storeId") Long storeId){

        List<ThemeDto.ThemeResponse> list = new ArrayList<>();
        list.add(ThemeDto.ThemeResponse.of());
        list.add(ThemeDto.ThemeResponse.of());
        return ResponseEntity.ok().body(new SingleListResponseDto<>(list));
    }

    //TH-0006 가게 테마 리뷰 조회
    @GetMapping("/{themeId}/stores/{storeId}/review")
    public ResponseEntity<?> attemptGetStoreThemeReview(@PathVariable("themeId") Long themeId, @PathVariable("storeId") Long storeId){

        List<ThemeDto.StoreThemeReviewResponse> list = new ArrayList<>();
        list.add(ThemeDto.StoreThemeReviewResponse.of());
        list.add(ThemeDto.StoreThemeReviewResponse.of());

        return ResponseEntity.ok().body(new MultiResponseDto<>(list, new Page() {
            @Override
            public int getTotalPages() {
                return 0;
            }

            @Override
            public long getTotalElements() {
                return 0;
            }

            @Override
            public Page map(Function converter) {
                return null;
            }

            @Override
            public int getNumber() {
                return 0;
            }

            @Override
            public int getSize() {
                return 0;
            }

            @Override
            public int getNumberOfElements() {
                return 0;
            }

            @Override
            public List getContent() {
                return null;
            }

            @Override
            public boolean hasContent() {
                return false;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public boolean isFirst() {
                return false;
            }

            @Override
            public boolean isLast() {
                return false;
            }

            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Pageable nextPageable() {
                return null;
            }

            @Override
            public Pageable previousPageable() {
                return null;
            }

            @Override
            public Iterator iterator() {
                return null;
            }
        }));
    }
    //TH-0007 가게 테마 리뷰 수정
    @PatchMapping("/{themeId}/stores/{storeId}/reviews/{reviewId}")
    public ResponseEntity<?> attemptReviewUpdate(@PathVariable("themeId") Long themeId,
                                                 @PathVariable("storeId") Long storeId,
                                                 @PathVariable("reviewId") Long reviewId,
                                                 @RequestBody ThemeDto.ReviewPatch reviewPatch){

        return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
    }
    //TH-0008 가게 테마 리뷰 삭제
    @DeleteMapping("/{themeId}/stores/{storeId}/reviews/{reviewId}")
    public ResponseEntity<?> attemptReviewDelete(@PathVariable("themeId") Long themeId,
                                                 @PathVariable("storeId") Long storeId,
                                                 @PathVariable("reviewId") Long reviewId){
        return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
    }



   //TH- 0010 테마 등록
    @PostMapping("/registration")
    public ResponseEntity<?> attemptThemeRegister(@RequestBody ThemeDto.ThemePost ThemePostDto){
        return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
    }
    //TH -0011 테마 찜 여부 조회
    @GetMapping("/{themeId}/users/{userId}")
    public ResponseEntity<?> checkDibsTheme(@PathVariable("themeId") Long themeId,
                                            @PathVariable("userId") Long userId){

        return ResponseEntity.ok().body(new HashMap<>(){{put("isWishing",true);}});
    }

    //TH -0013 테마 찜 취소
    @PatchMapping("/{themeId}/users/{userId}/unwish")
    public ResponseEntity cancelDibsTheme (@PathVariable("themeId") Long themeId,
                                           @PathVariable("userId") Long userId){

        return ResponseEntity.ok().body(new HashMap<>(){{put("success",true);}});
    }

}
