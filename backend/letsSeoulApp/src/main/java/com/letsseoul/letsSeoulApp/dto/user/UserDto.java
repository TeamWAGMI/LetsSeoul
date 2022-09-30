package com.letsseoul.letsSeoulApp.dto.user;

import com.letsseoul.letsSeoulApp.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private UserDto() {
    }

    //US-0001 유저 정보 변경 response
    @Getter
    @RequiredArgsConstructor
    public static class UpdateUserEmojiResponse{

        private final boolean success;

        private final String emoji;

        public static UpdateUserEmojiResponse of(User user){
            return new UpdateUserEmojiResponse(
                    true,
                    user.getEmoji());
        }

    }
    //US-0002 유저정보변경 request
    @Getter
    @RequiredArgsConstructor
    public static class UpdateUserInfomationPatch{
        private final String nickname;
        private final String introduce;
    }
    //US-0002 유저정보변경 response
    @Getter
    @RequiredArgsConstructor
    public static class UpdateUserInfomationResponse {
        private final String nickname;
        private final String introduce;

        public static UpdateUserInfomationResponse of(User user) {
            return new UpdateUserInfomationResponse(
                    user.getNickname(),
                    user.getIntroduce());
        }
    }

    //US-0003 유저 정보 조회 response
    @Getter
    @RequiredArgsConstructor
    public static class SearchUserInformationResponse{
        private final String emoji;
        private final String nickname;
        private final String introduce;

        public static SearchUserInformationResponse of(User user){
            return new SearchUserInformationResponse(
                    user.getEmoji(),
                    user.getNickname(),
                    user.getIntroduce()
            );
        }
    }

    @Getter
    @RequiredArgsConstructor
    public static class Response {
        private final Long userId;
        private final String emoji;
        private final String nickname;
        private final Long reviewCount;

        public static List<Response> of() {
            List<Response> collect = new ArrayList<>();

            collect.add(new Response(1L, "😀", "서은정", 999L));
            collect.add(new Response(2L, "😁", "문주성", 200L));
            collect.add(new Response(3L, "😂", "이윤진", 110L));
            collect.add(new Response(4L, "🤣", "박정윤", 130L));
            collect.add(new Response(5L, "😎", "박정수", 106L));

            return collect;
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
