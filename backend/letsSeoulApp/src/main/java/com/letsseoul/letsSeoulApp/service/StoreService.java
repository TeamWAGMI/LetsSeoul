package com.letsseoul.letsSeoulApp.service;


import com.letsseoul.letsSeoulApp.domain.FollowStore;
import com.letsseoul.letsSeoulApp.domain.Review;
import com.letsseoul.letsSeoulApp.domain.Store;

import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.store.StoreDto;
import com.letsseoul.letsSeoulApp.dto.store.StoreThemeResponse;
import com.letsseoul.letsSeoulApp.dto.store.StoreThemeReviewResponse;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import com.letsseoul.letsSeoulApp.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final ThemeStoreRepository themeStoreRepository;
    private final StoreRepository storeRepository;

    private final ReviewRepository reviewRepository;

    private final UserRepository userRepository;
    private final FollowStoreRepository followStoreRepository;

    private static ResponseStatusException triggerExceptionForIllegalRequest() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "잘못된 요청입니다.");
    }

    /**
     * ST-0001
     */
    public StoreDto.StoreInfoResponse getStoreInfo(Long storeId) {

        Store store = getStoreOrThrow(storeId);

        return StoreDto.StoreInfoResponse.of(store);
    }

    /**
     * ST-0002
     */
    public StoreDto.CheckDibsResponse checkStoreDibs(Long userId, Long storeId) {

        getUserOrThrow(userId);
        getStoreOrThrow(storeId);

        Optional<FollowStore> dibs = followStoreRepository.findByUserIdAndStoreId(userId, storeId);

        return StoreDto.CheckDibsResponse.of(dibs);
    }

    /**
     * ST-0003
     */
    public StoreDto.RegistDibsResponse registStoreDibs(Long userId, Long storeId) {

        User user = getUserOrThrow(userId);
        Store store = getStoreOrThrow(storeId);

        FollowStore followStore = followStoreRepository.findByUserIdAndStoreId(userId, storeId)
                .orElse(FollowStore.builder()
                        .user(user)
                        .store(store)
                        .build());

        followStoreRepository.save(followStore);

        return StoreDto.RegistDibsResponse.of();
    }

    /**
     * ST-0004
     */
    public StoreDto.DeleteDibsResponse deleteStoreDibs(Long userId, Long storeId) {

        getUserOrThrow(userId);
        getStoreOrThrow(storeId);

        followStoreRepository.findByUserIdAndStoreId(userId, storeId)
                .ifPresent(fs -> followStoreRepository.deleteById(fs.getId()));

        return StoreDto.DeleteDibsResponse.of();
    }

    private Store getStoreOrThrow(Long storeId) {
        return storeRepository.findById(storeId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 정보입니다."));
    }

    private User getUserOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 정보입니다."));
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
        reviewRepository.save(review);
        return StoreDto.UpdateOrDeleteReviewResponse.of();
    }

    //TH-0008
    public StoreDto.UpdateOrDeleteReviewResponse attemptReviewDelete(Long userId,Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> StoreService.triggerExceptionForIllegalRequest());
        if(review.getUser().getId() != userId){
            throw StoreService.triggerExceptionForIllegalRequest();
        }
        review.setStatus("D");
        reviewRepository.save(review);
        return StoreDto.UpdateOrDeleteReviewResponse.of();

    }
}
