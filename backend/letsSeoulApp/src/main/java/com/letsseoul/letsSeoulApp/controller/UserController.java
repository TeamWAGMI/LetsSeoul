package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    //US-0001 유저 이미지 변경
    @PatchMapping("/{userId}/changeEmoji")
    public ResponseEntity<?> userEmojiChange(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(UserDto.UserEmojiResponse.of());
    }
    //US-0002 유저 정보 변경
    @PatchMapping("/{userId}")
    public ResponseEntity<?> userInformationChange(@PathVariable("userId") Long userId, @RequestBody UserDto.UserInformationPatch requestBody){
        return ResponseEntity.ok().body(UserDto.UserInformationPatchResponse.of());
    }
    //US-0003 유저 정보 조회
    @GetMapping("/{userId}")
    public ResponseEntity<?> userInformationSearch(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(UserDto.UserInformationGetResponse.of());
    }
    //CU- 0001  (추천)큐레이터 목록 조회
    @GetMapping("/curators")
    public ResponseEntity<?> attemptGetCurators(){
        User user = User.builder()
                .emoji("테스트이모지")
                .name("테스트 닉네임")
                .build();
        return ResponseEntity.ok().body(UserDto.Response.of(user));
    }

    //TH- 0014 찜한 테마 목록 조회
    @GetMapping("/{userId}/theme")
    public ResponseEntity<?> viewListOfDibsThemes(@PathVariable("userId") Long userId){
        return ResponseEntity.ok().body(UserDto.DibsTehemeResponse.of());
    }
}
