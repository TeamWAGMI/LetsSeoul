package com.letsseoul.letsSeoulApp.dto;

import com.letsseoul.letsSeoulApp.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class UserDto {

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
