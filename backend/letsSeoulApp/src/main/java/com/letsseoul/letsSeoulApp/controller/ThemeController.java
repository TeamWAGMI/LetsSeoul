package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.config.auth.LoginUser;
import com.letsseoul.letsSeoulApp.config.auth.dto.SessionUser;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.theme.PopularThemeListResponseDto;
import com.letsseoul.letsSeoulApp.dto.theme.RecommendedThemeListResponseDto;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import com.letsseoul.letsSeoulApp.service.ThemeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import javax.validation.constraints.Positive;
import java.util.List;


@RestController
@RequestMapping("/api/v1/themes")
@RequiredArgsConstructor
@Slf4j
public class ThemeController {

    private final ThemeService themeService;

    /**
     * BE-TH-0001 추천 테마 목록 조회
     */
    @GetMapping("/recommends")
    public List<RecommendedThemeListResponseDto> getRecommendedThemeList() {

        return themeService.listupRecommendedThemes();
    }

    /**
     * BE-TH-0002 인기 테마 목록 조회
     */
    @GetMapping("/populars")
    public List<PopularThemeListResponseDto> getPopularThemeList() {

        return themeService.listupPopularThemes();
    }

    /**
     * BE-TH-0003 테마지도 조회
     * @param themeId
     */
    @GetMapping("/{themeId}")
    public ThemeDto.ThemeMapListResponse themeMapList(@PathVariable("themeId") Long themeId) {
        return themeService.themeMapList(themeId);
    }

    /**
     * BE-TH-0004 테마 리뷰 등록
     * @param themeId
     */
    @PostMapping("/{themeId}")//hotfix
    public ThemeDto.RegistThemeReviewResponse registThemeReview(@LoginUser SessionUser user,
                                            @PathVariable("themeId") @Positive Long themeId,
                                            @RequestBody ThemeDto.RegistThemeReviewPost registThemeReviewPost) {

        if (!StringUtils.hasText(registThemeReviewPost.getReview().getContent())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
        }
        else if (null == registThemeReviewPost.getReview().getScore()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
        }

        return themeService.registThemeReview(user.getId(), themeId, registThemeReviewPost);
    }



    /**
     * BE-TH-0009
     * @param themeSearchPost 검색 파라미터
     */
    @PostMapping("/search")
    public MultiResponseDto<ThemeDto.ThemeSearchResponse> themeSearch(@RequestBody ThemeDto.ThemeSearchPost themeSearchPost, @RequestParam(name = "page",defaultValue = "1") Integer page, @RequestParam(name = "size",defaultValue = "10") Integer size) {

        return themeService.themeSearch(themeSearchPost, PageRequest.of(page-1,size));
    }

    /**
     * BE-TH-0010 테마 등록(신규 테마 제안)
     * @param registThemePost
     */
    @PostMapping
    public ThemeDto.RegistThemeResponse registTheme(@RequestBody ThemeDto.RegistThemePost registThemePost){

        if (!StringUtils.hasText(registThemePost.getThemeTitle())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
        }
        else if (!StringUtils.hasText(registThemePost.getThemeContent())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.");
        }

        return themeService.registTheme(registThemePost);
    }

    //TH -0011 테마 찜 여부 조회
    @GetMapping("/{themeId}/users/me/follows")
    public ThemeDto.checkDibsThemeResponse checkDibsTheme(@LoginUser SessionUser user,
                                                                          @PathVariable("themeId") Long themeId){
        return themeService.checkDibsTheme(user.getId(),themeId);
    }


    /**
     * BE-TH-0012 테마찜 등록
     * @param themeId
     */
    @PostMapping("/{themeId}/users/me/wishes")
    public ThemeDto.RegistDibsThemeResponse registDibsTheme(
            @LoginUser SessionUser user,
            @PathVariable("themeId") Long themeId) {
        return themeService.registDibsTheme(user.getId(),themeId);
    }

    //TH -0013 테마 찜 취소
    @DeleteMapping("/{themeId}/users/me/unwish")
    public ThemeDto.cancelDibsThemeResponse cancelDibsTheme (
            @LoginUser SessionUser user,
            @PathVariable("themeId") Long themeId){
        return themeService.cancelDibsTheme(user.getId(),themeId);
    }

    // TH-0015 테마 정보 조회
    @GetMapping("/{themeId}/info")
    public ThemeDto.ThemeInfoResponse viewThemeInformation(@PathVariable("themeId") Long themeId){
        return themeService.viewThemeInformation(themeId);
    }
    
}

