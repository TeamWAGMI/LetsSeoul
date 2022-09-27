package com.letsseoul.letsSeoulApp.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

public class StoreDto {

    private StoreDto() {
    }
    @Getter
    @RequiredArgsConstructor
    //ST-0001 가게정보 조회 Response
    public static class StoreInformationReponse{

        private final String name;

        private final String address;

        private final String latitude;

        private final String longitude;

        public static StoreInformationReponse of(){
            return new StoreInformationReponse(
                    "가게 이름",
                    "가게 주소",
                    "37.38500822345394",
                    "127.12342695116273"
            );
        }
    }
}
