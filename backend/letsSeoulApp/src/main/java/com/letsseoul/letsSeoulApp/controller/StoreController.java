package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.config.auth.LoginUser;
import com.letsseoul.letsSeoulApp.config.auth.dto.SessionUser;
import com.letsseoul.letsSeoulApp.dto.StoreDto;
import com.letsseoul.letsSeoulApp.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    /**
     * ST-0001 가게 정보 조회
     * 단일한 특정 가게 정보를 조회하는 기능
     */
    @GetMapping("/{storeId}")
    public ResponseEntity<StoreDto.StoreInfoResponse> getStoreInfo(@PathVariable("storeId") @Positive Long storeId) {

        return ResponseEntity.ok().body(storeService.getStoreInfo(storeId));
    }

    /**
     * ST-0002 가게찜 여부 조회
     * 특정 가게를 특정 회원이 찜하고 있는지 여부를 조회하는 기능
     */
    @GetMapping("/{storeId}/follows")
    public ResponseEntity<StoreDto.CheckDibsResponse> checkStoreDibs(@LoginUser SessionUser user,
                                                                     @PathVariable("storeId") @Positive Long storeId) {

        return ResponseEntity.ok()
                .body(storeService.checkStoreDibs(user.getId(), storeId));
    }

    /**
     * ST-0003 가게찜 등록
     * 특정 가게를 찜 목록에 등록하는 기능
     */
    @PostMapping("/{storeId}/wishes")
    public ResponseEntity<StoreDto.RegistDibsResponse> registStoreDibs(@LoginUser SessionUser user,
                                                                       @PathVariable("storeId") @Positive Long storeId) {

        return ResponseEntity.ok()
                .body(storeService.registStoreDibs(user.getId(), storeId));
    }


    /**
     * ST-0004 가게찜 취소
     * 특정 가게를 찜 목록에서 삭제하는 기능
     */
    @DeleteMapping("/{storeId}/wishes")
    public ResponseEntity<StoreDto.DeleteDibsResponse> deleteStoreDibs(@LoginUser SessionUser user,
                                                                       @PathVariable("storeId") @Positive Long storeId) {

        return ResponseEntity.ok()
                .body(storeService.deleteStoreDibs(user.getId(), storeId));
    }
}
