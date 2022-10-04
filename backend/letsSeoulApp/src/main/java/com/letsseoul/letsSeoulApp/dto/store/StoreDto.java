package com.letsseoul.letsSeoulApp.dto.store;

import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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

    @Getter
    @RequiredArgsConstructor
    //ST-0001 가게정보 조회 Response
    public static class StoreThemeReponse{

        private final Long id;

        private final String emoji;

        private final String title;


        public static List<StoreThemeReponse> of(List<ThemeStore> list){
            List<StoreThemeReponse> reponseList = new ArrayList<>();
            for(ThemeStore themeStore : list){
                reponseList.add(new StoreThemeReponse(
                        themeStore.getTheme().getId(),
                        themeStore.getTheme().getEmoji(),
                        themeStore.getTheme().getTitle()
                ));
            }
            return reponseList;
        }
    }
    @Getter
    @RequiredArgsConstructor
    public static class UpdateOrDeleteReviewResponse {
        private final Boolean success;
        public static StoreDto.UpdateOrDeleteReviewResponse of() {
            return new StoreDto.UpdateOrDeleteReviewResponse(true);
        }
    }






}
