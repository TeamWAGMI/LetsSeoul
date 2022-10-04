package com.letsseoul.letsSeoulApp.dto;

import com.letsseoul.letsSeoulApp.domain.FollowStore;
import com.letsseoul.letsSeoulApp.domain.Store;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

public class StoreDto {

    private StoreDto() {
    }

    //ST-0001 가게정보 조회 Response
    @Getter
    @RequiredArgsConstructor
    public static class StoreInfoResponse{
        private final String name;
        private final String address;
        private final String latitude;
        private final String longitude;

        public static StoreInfoResponse of(Store store) {
            return new StoreInfoResponse(
                    store.getTitle(),
                    store.getAddress(),
                    store.getLat(),
                    store.getLng());
        }
    }

    // ST-0002 가게찜 여부 조회
    @Getter
    @RequiredArgsConstructor
    public static class CheckDibsResponse {
        private final Boolean isWishing;

        public static CheckDibsResponse of(Optional<FollowStore> dibs) {
            return new CheckDibsResponse(dibs.isPresent());
        }
    }

    // ST-0003 가게찜 등록
    @Getter
    @RequiredArgsConstructor
    public static class RegistDibsResponse {
        private final Boolean success;

        public static RegistDibsResponse of() {
            return new RegistDibsResponse(true);
        }
    }

    // ST-0004 가게찜 취소
    @Getter
    @RequiredArgsConstructor
    public static class DeleteDibsResponse {
        private final Boolean success;

        public static DeleteDibsResponse of() {
            return new DeleteDibsResponse(true);
        }
    }
}
