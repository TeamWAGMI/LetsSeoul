package com.letsseoul.letsSeoulApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


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
    public static class FollowUser {
        private final Boolean success;

        public static FollowUser of() {
            return new FollowUser(true);
        }
    }

    // BE-FO-0004
    @Getter
    @RequiredArgsConstructor
    public static class UnfollowUser {
        private final Boolean success;

        public static UnfollowUser of() {
            return new UnfollowUser(true);
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
