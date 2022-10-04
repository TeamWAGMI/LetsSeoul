package com.letsseoul.letsSeoulApp.service;

import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.domain.Store;
import com.letsseoul.letsSeoulApp.domain.ThemeStore;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.store.StoreDto;
import com.letsseoul.letsSeoulApp.dto.store.StoreThemeResponse;
import com.letsseoul.letsSeoulApp.dto.store.StoreThemeReviewResponse;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import com.letsseoul.letsSeoulApp.repository.ReviewRepository;
import com.letsseoul.letsSeoulApp.repository.StoreRepository;
import com.letsseoul.letsSeoulApp.repository.ThemeStoreRepository;
import com.letsseoul.letsSeoulApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final ThemeStoreRepository themeStoreRepository;
    private final StoreRepository storeRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;

    private static ResponseStatusException triggerExceptionForIllegalRequest() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "잘못된 요청입니다.");
    }
    public List<StoreThemeResponse> attemptGetStoreTheme(Long storeId) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> StoreService.triggerExceptionForIllegalRequest());
        List<StoreThemeResponse> storeList = themeStoreRepository.findDistinctByStoreId(storeId);
        return storeList;
    }

    public MultiResponseDto<StoreThemeReviewResponse> attemptGetStoreThemeReview(Long storeId, Pageable pageable) {
        Store store = storeRepository.findById(storeId).orElseThrow(() -> StoreService.triggerExceptionForIllegalRequest());
        Page<StoreThemeReviewResponse> reviewList = themeStoreRepository.findByStoreId(storeId,pageable);
        return new MultiResponseDto<>(reviewList);
    }

    public StoreDto.UpdateOrDeleteReviewResponse attemptReviewUpdate(Long userId,Long reviewId,ThemeDto.ReviewPatch reviewPatch) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> StoreService.triggerExceptionForIllegalRequest());
        //리뷰아이디와 세션아이디가 다르다면 fail
        if(userId != review.getUser().getId()){
            throw StoreService.triggerExceptionForIllegalRequest();
        }
        review.setContent(reviewPatch.getReviewContent());  //""  << null 검증에 대한 처리 컨트롤러에서 처리
        review.setScore(Math.toIntExact(reviewPatch.getReviewScore())); //1~5 null 그이외에 값이들어오면안되니까;

        Review save = reviewRepository.save(review);
        return StoreDto.UpdateOrDeleteReviewResponse.of();
    }

    //TH-0008
    public StoreDto.UpdateOrDeleteReviewResponse attemptReviewDelete(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> StoreService.triggerExceptionForIllegalRequest());
        review.setStatus("D");
        Review save = reviewRepository.save(review);
        return StoreDto.UpdateOrDeleteReviewResponse.of();
    }
}
