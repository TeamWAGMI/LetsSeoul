package com.letsseoul.letsSeoulApp.dto.follow;

import com.letsseoul.letsSeoulApp.domain.FollowUser;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import lombok.*;
import com.letsseoul.letsSeoulApp.domain.User;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FollowDto {

    // BE-FO-0001
    @Getter
    @RequiredArgsConstructor
    public static class CountFollowingsAndFollowersResponse {
        private final Long numberOfFollowing;
        private final Long numberOfFollower;

        public static CountFollowingsAndFollowersResponse of(Long followingCount,Long followerCount) {
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
            if(count == 1L){
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

    // BE-FO-0005
    @Getter
    @RequiredArgsConstructor
    public static class FollowingListResponse {
        private final Long userId;
        private final String emoji;
        private final String nickname;
        private final Long followerCount;

        public static MultiResponseDto<FollowingListResponse> of(Page<FollowUser> followUsers, List<User> userList, List<Long> countList) {

            List<FollowingListResponse> list = new ArrayList<>();
            for(int i=0; i<userList.size(); i++){
                list.add(new FollowingListResponse(
                        userList.get(i).getId(),
                        userList.get(i).getEmoji(),
                        userList.get(i).getNickname(),
                        countList.get(i)
                ));
            }
            return new MultiResponseDto<>(list, followUsers);
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
