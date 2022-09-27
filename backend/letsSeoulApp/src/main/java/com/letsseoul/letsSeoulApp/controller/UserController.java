package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

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
