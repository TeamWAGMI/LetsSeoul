package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.dto.StoreDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/stores")
public class StoreController {

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
}
