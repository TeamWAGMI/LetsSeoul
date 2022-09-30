package com.letsseoul.letsSeoulApp.dto.follow;

import com.letsseoul.letsSeoulApp.domain.FollowUser;
import lombok.*;
import com.letsseoul.letsSeoulApp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowDto {

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
    public static class FollowUserResponse {
        private final Boolean success;

        public static FollowUserResponse of() {
            return new FollowUserResponse(true);
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
        private final Long id;
        private final String emoji;
        private final String nickname;
        @Setter private Long followerCount;

    }
}
