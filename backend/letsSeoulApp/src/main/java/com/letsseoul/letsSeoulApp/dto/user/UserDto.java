package com.letsseoul.letsSeoulApp.dto.user;

import com.letsseoul.letsSeoulApp.domain.FollowTheme;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

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

    //사용자의 찜한 테마목록 response
    @Getter
    @RequiredArgsConstructor
    public static class DibsTehemeResponse{
        private final Long themeId;
        private final String themeEmoji;
        private final String themeName;
        private final Long dibsCount;

        public static MultiResponseDto<DibsTehemeResponse> of(Page<FollowTheme> page, List<Long> countList){
            List<UserDto.DibsTehemeResponse> list = new ArrayList<>();
            List<FollowTheme> content = page.getContent();
            for(int i=0;i<content.size();i++){
                list.add(new DibsTehemeResponse(
                        content.get(i).getTheme().getId(),
                        content.get(i).getTheme().getEmoji(),
                        content.get(i).getTheme().getTitle(),
                        countList.get(i)
                ));
            }
            MultiResponseDto<DibsTehemeResponse> multiResponseDto = new MultiResponseDto<>(list,page);
            return multiResponseDto;
        }
    }

}
