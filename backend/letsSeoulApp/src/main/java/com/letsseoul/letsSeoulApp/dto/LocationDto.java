package com.letsseoul.letsSeoulApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class LocationDto {

    //LO-0001 특정 유저 리뷰 장소 조회 //LO-0002 특정 유저 찜 장소 조회
    @Getter
    @RequiredArgsConstructor
    public static class  userPlaceSearchResponse{
        private final Long storeId;
        private final String storeName;
        private final Long reviewCount;
        private final String latitude;
        private final String longitude;

        public static List<userPlaceSearchResponse> of(){
          List<userPlaceSearchResponse> list = new ArrayList<>();
          list.add(new userPlaceSearchResponse(
                  1L,
                  "가게이름",
                  1L,
                  "37.38500822345394",
                  "127.12342695116273"
          ));
            list.add(new userPlaceSearchResponse(
                    2L,
                    "가게이름2",
                    10L,
                    "37.5702",
                    "126.9916"
            ));
          return list;
        }

    }



}
