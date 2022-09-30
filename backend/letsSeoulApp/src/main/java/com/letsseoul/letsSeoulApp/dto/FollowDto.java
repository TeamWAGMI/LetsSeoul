package com.letsseoul.letsSeoulApp.dto;

import com.letsseoul.letsSeoulApp.domain.FollowUser;
import com.letsseoul.letsSeoulApp.domain.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;


public class FollowDto {

    private FollowDto() {
    }




    // BE-FO-0001
    @Getter
    @RequiredArgsConstructor
    public static class CountFollowingsAndFollowersResponse {
        private final Integer numberOfFollowing;
        private final Integer numberOfFollower;

        public static CountFollowingsAndFollowersResponse of(Integer followingCount,Integer followerCount) {
            return new CountFollowingsAndFollowersResponse(followingCount, followerCount);
        }
    }

    // BE-FO-0002
    @Getter
    @RequiredArgsConstructor
    public static class CheckFollowing {
        private final Boolean isFollowing;

        public static CheckFollowing of(Long count) {
            boolean is = false;
            if(count == 1L ){
                is = true;
            }
            return new CheckFollowing(is);
        }
    }

    // BE-FO-0003
    @Getter
    @RequiredArgsConstructor
    public static class FollowUserReponse {
        private final Boolean success;

        public static FollowUserReponse of() {
            return new FollowUserReponse(true);
        }
    }

    // BE-FO-0004
    @Getter
    @RequiredArgsConstructor
    public static class UnfollowUserResponse {
        private final Boolean success;

        public static UnfollowUserResponse of() {
            return new UnfollowUserResponse(true);
        }
    }
    @Getter
    @RequiredArgsConstructor
    public static class ThemeSearchResponse<T> {
        private final List<T> content;
        private final ThemeDto.ThemeSearchResponse.PageInfo pageInfo;

        @Getter
        @RequiredArgsConstructor
        static class PageInfo {
            private final Integer nowPage;
            private final Integer nowSize;
            private final Integer totalPage;
            private final Long totalSize;
        }

        @Getter
        @RequiredArgsConstructor
        static class ListTheme {
            private final Long themeId;
            private final String themeEmoji;
            private final String themeTitle;
            private final Integer reviewCount;
        }

        public static ThemeDto.ThemeSearchResponse of() {
            List<ThemeDto.ThemeSearchResponse.ListTheme> collect = new ArrayList<>();
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(1L, "😀", "테마이름 짓기 어려워요", 100));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(2L, "😁", "테마이름 어려워요", 200));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(3L, "😂", "테마이름", 300));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(4L, "🤣", "짓기 어려워요", 400));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(5L, "😃", "이름 짓기 어려워요", 110));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(6L, "😅", "짓기 어려워요", 120));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(7L, "😆", "어려워요", 130));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(8L, "😎", "테마이름 짓기", 140));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(9L, "🤗", "이름 어려워요", 105));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(0L, "😍", "테마이름 짓기 어려", 106));
            collect.add(new ThemeDto.ThemeSearchResponse.ListTheme(11L, "🥰", "테마 짓기", 107));

            ThemeDto.ThemeSearchResponse.PageInfo pageInfo = new ThemeDto.ThemeSearchResponse.PageInfo(1, 10, 2, 11L);

            return new ThemeDto.ThemeSearchResponse(collect, pageInfo);
        }
    }
    // BE-FO-0005
    @Getter
    @RequiredArgsConstructor
    public static class FollowingListResponse {
        private final Long userId;
        private final String emoji;
        private final String nickname;
        private final Long followerCount;

        //MultiResponseDto<FollowingListResponse>
        public static MultiResponseDto<FollowingListResponse> of(Page<FollowUser> followUsers,List<User> userList,List<Long> countList) {

            List<FollowingListResponse> list = new ArrayList<>();
            for(int i=0; i<userList.size(); i++){
                list.add(new FollowingListResponse(
                        userList.get(i).getId(),
                        userList.get(i).getEmoji(),
                        userList.get(i).getNickname(),
                        countList.get(i)
                ));
            }
            return new MultiResponseDto<>(list,followUsers, true, false);
        }
    }

    // BE-FO-0006
    @Getter
    @RequiredArgsConstructor
    public static class FollowerListResponse {
        private final Long userId;
        private final String emoji;
        private final String nickname;
        private final Integer followerCount;

        public static List<FollowerListResponse> of() {

            List<FollowerListResponse> collect = new ArrayList<>();
            collect.add(new FollowerListResponse(1L, "😛", "요조", 1662));
            collect.add(new FollowerListResponse(2L, "😁", "라이너스", 2343));
            collect.add(new FollowerListResponse(3L, "🤣", "담요", 75));
            collect.add(new FollowerListResponse(4L, "🙄", "페퍼톤스", 10000));
            collect.add(new FollowerListResponse(5L, "😀", "뎁", 400));
            collect.add(new FollowerListResponse(6L, "😐", "토끼", 1345));
            collect.add(new FollowerListResponse(7L, "🤐", "넬", 457));
            collect.add(new FollowerListResponse(8L, "😉", "루싸이트", 0));
            collect.add(new FollowerListResponse(9L, "😗", "몽키즈", 8345));
            collect.add(new FollowerListResponse(10L, "😍", "전자양", 93));
            collect.add(new FollowerListResponse(11L, "🤗", "아스피린", 13));
            collect.add(new FollowerListResponse(12L, "😫", "브로콜리", 78));
            collect.add(new FollowerListResponse(13L, "😴", "너마저", 342));

            return collect;
        }
    }
}
