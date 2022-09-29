package com.letsseoul.letsSeoulApp.dto.follow;

import com.letsseoul.letsSeoulApp.domain.FollowUser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class FollowDto {

    private FollowDto() {
    }

    // BE-FO-0001
    @Getter
    @RequiredArgsConstructor
    public static class CountFollowingsAndFollowersResponse {
        private final Integer numberOfFollowing;
        private final Integer numberOfFollower;

        public static CountFollowingsAndFollowersResponse of() {
            return new CountFollowingsAndFollowersResponse(5500, 10245);
        }
    }

    // BE-FO-0002
    @Getter
    @RequiredArgsConstructor
    public static class CheckFollowing {
        private final Boolean isFollowing;

        public static CheckFollowing of() {
            return new CheckFollowing(true);
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

    // BE-FO-0005
    @Getter
    @RequiredArgsConstructor
    public static class FollowingListResponse {
        private final Long userId;
        private final String emoji;
        private final String nickname;
        private final Integer followerCount;

        public static List<FollowingListResponse> of() {

            List<FollowingListResponse> collect = new ArrayList<>();
            collect.add(new FollowingListResponse(1L, "🙄", "페퍼톤스", 10000));
            collect.add(new FollowingListResponse(2L, "😀", "뎁", 400));
            collect.add(new FollowingListResponse(3L, "😁", "라이너스", 2343));
            collect.add(new FollowingListResponse(4L, "🤣", "담요", 75));
            collect.add(new FollowingListResponse(5L, "😉", "루싸이트", 0));
            collect.add(new FollowingListResponse(6L, "😐", "토끼", 1345));
            collect.add(new FollowingListResponse(7L, "🤐", "넬", 457));
            collect.add(new FollowingListResponse(8L, "😍", "전자양", 93));
            collect.add(new FollowingListResponse(9L, "🤗", "아스피린", 13));
            collect.add(new FollowingListResponse(10L, "😫", "브로콜리", 78));
            collect.add(new FollowingListResponse(11L, "😴", "너마저", 342));
            collect.add(new FollowingListResponse(12L, "😛", "요조", 1662));
            collect.add(new FollowingListResponse(13L, "😗", "몽키즈", 8345));

            return collect;
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
