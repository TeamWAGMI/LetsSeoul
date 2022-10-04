package com.letsseoul.letsSeoulApp.service;

import com.letsseoul.letsSeoulApp.domain.FollowStore;
import com.letsseoul.letsSeoulApp.domain.Store;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.StoreDto;
import com.letsseoul.letsSeoulApp.repository.FollowStoreRepository;
import com.letsseoul.letsSeoulApp.repository.StoreRepository;
import com.letsseoul.letsSeoulApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;
    private final FollowStoreRepository followStoreRepository;
    private final UserRepository userRepository;

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
}
