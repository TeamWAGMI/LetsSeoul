package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.config.auth.LoginUser;
import com.letsseoul.letsSeoulApp.config.auth.dto.SessionUser;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.UserDto;
import com.letsseoul.letsSeoulApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Positive;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //US-0001 유저 이미지 변경
    @PatchMapping("/emoji")
    public ResponseEntity<?> updateUserEmoji(@LoginUser SessionUser user){

        return ResponseEntity.ok()
                .body(userService.changeUserEmoji(user.getId()));
    }
    /**
     * US-0002 유저 정보 변경
     * 기능 요약: 유저의 닉네임, 한줄 소개를 변경해주는 api
     */
    @PatchMapping
    public ResponseEntity<UserDto.UpdateUserInfomationResponse> updateUserInfomation(
            @LoginUser SessionUser user,
            @RequestBody UserDto.UpdateUserInfomationPatch updateUserInfomationPatch){

        return ResponseEntity.ok()
                .body(userService.changeUserInfo(user.getId(), updateUserInfomationPatch));
    }

    /**
     * US-0003 유저 정보 조회 api
     * 해당 유저의 정보(닉네임, 이모지, 한줄소개)를 조회하는 api
     */
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto.SearchUserInformationResponse> searchUserInformation(
            @PathVariable("userId") @Positive(message = "잘못된 유저 정보입니다.") Long userId) {

        return ResponseEntity.ok()
                .body(userService.searchUserInfo(userId));
    }

    //CU- 0001  (추천)큐레이터 목록 조회
    @GetMapping("/curators")
    public ResponseEntity<?> attemptGetCurators(){
        User user = User.builder()
                .emoji("테스트이모지")
                .name("테스트 닉네임")
                .build();
        return ResponseEntity.ok().body(UserDto.Response.of());
    }

    //TH- 0014 찜한 테마 목록 조회
    @GetMapping("/me/themes")
    public ResponseEntity<MultiResponseDto<UserDto.DibsTehemeResponse>> viewListOfDibsThemes(@LoginUser SessionUser user, @RequestParam(name = "page",defaultValue = "1") Integer page, @RequestParam(name = "size",defaultValue = "10") Integer size){
        return ResponseEntity.ok().body(userService.viewListDibsThemes(1L, PageRequest.of(page-1,size)));
    }
}
