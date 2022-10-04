package com.letsseoul.letsSeoulApp.dto.store;

import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
//ST-0001 가게정보 조회 Response
public class StoreThemeResponse{
    private final Long id;

    private final String emoji;

    private final String title;

    /*public static List<StoreThemeResponse> of(List<ThemeStore> list){
        List<StoreThemeResponse> reponseList = new ArrayList<>();
        for(ThemeStore themeStore : list){
            reponseList.add(new StoreThemeResponse(
                    themeStore.getTheme().getId(),
                    themeStore.getTheme().getEmoji(),
                    themeStore.getTheme().getTitle()
            ));
        }
        return reponseList;
    }*/
}