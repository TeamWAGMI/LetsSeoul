package com.letsseoul.letsSeoulApp.dto;

import com.letsseoul.letsSeoulApp.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    //US-0001 유저 정보 변경 response
    @Getter
    @RequiredArgsConstructor
    public static class UserEmojiResponse{

        private final boolean success;

        private final String emoji;

        public static UserEmojiResponse of(){
            return new UserEmojiResponse(
                    true,
                    "🧡"
            );
        }

    }
    //US-0002 유저정보변경 request
    @Getter
    @RequiredArgsConstructor
    public static class UserInformationPatch{

        private final String nickname;
        private final String introduction;
    }
    //US-0002 유저정보변경 response
    @Getter
    @RequiredArgsConstructor
    public static class UserInformationPatchResponse{
        private final String nickname;
        private final String introduction;

        public static UserInformationPatchResponse of(){
            return new UserInformationPatchResponse(
                    "닉네임",
                    "소개"
            );
        }

    }

    //US-0003 유저 정보 조회 response
    @Getter
    @RequiredArgsConstructor
    public static class UserInformationGetResponse{
        private final String emoji;
        private final String nickname;
        private final String introduction;
        public static UserInformationGetResponse of(){
            return new UserInformationGetResponse(
                    "🧡",
                    "닉네임",
                    "소개"
            );
        }
    }



    @Getter
    @RequiredArgsConstructor
    public static class Response{

        private final Long userId;
        private final String emoji;
        private final String nickname;
        private final Long reviewCount;

        public static Response of(User user){
            return new Response(
                    //user.getId(),
                    1L,
                    user.getEmoji(),
                    user.getNickname(),
                    0L
            );
        }

    }
    //사용자의 찜한 테마목록 response
    @Getter
    @RequiredArgsConstructor
    public static class DibsTehemeResponse{
        private final Long themeId;
        private final String themeEmoji;
        private final String themeName;
        private final Long reviewCount;

        public static List<DibsTehemeResponse> of(){
            List<UserDto.DibsTehemeResponse> list = new ArrayList<>();
            list.add(new DibsTehemeResponse(
                    1L,
                    "테마 이모지",
                    "테마네임",
                    0L
            ));
            return list;
        }
    }

}
