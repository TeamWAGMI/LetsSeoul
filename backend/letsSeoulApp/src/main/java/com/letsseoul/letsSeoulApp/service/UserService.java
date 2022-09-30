package com.letsseoul.letsSeoulApp.service;

import com.letsseoul.letsSeoulApp.domain.Hotuser;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.user.CuratorListResponseDto;
import com.letsseoul.letsSeoulApp.dto.user.UserDto;
import com.letsseoul.letsSeoulApp.repository.HotuserRepository;
import com.letsseoul.letsSeoulApp.repository.ReviewRepository;
import com.letsseoul.letsSeoulApp.repository.UserRepository;
import com.letsseoul.letsSeoulApp.util.EmojiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final HotuserRepository hotuserRepository;
    private final ReviewRepository reviewRepository;

    private static ResponseStatusException triggerExceptionForNotFoundMember() {
        return new ResponseStatusException(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다.");
    }

    // US-0001
    public UserDto.UpdateUserEmojiResponse changeUserEmoji(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(UserService::triggerExceptionForNotFoundMember);

        user.updateUserEmoji(EmojiUtil.randomEmoji());

        return UserDto.UpdateUserEmojiResponse.of(userRepository.save(user));
    }

    // US-0002
    public UserDto.UpdateUserInfomationResponse changeUserInfo(Long userId, UserDto.UpdateUserInfomationPatch userInformationPatch) {

        User user = userRepository.findById(userId)
                .orElseThrow(UserService::triggerExceptionForNotFoundMember);

        user.updateUserInfo(userInformationPatch.getNickname(), userInformationPatch.getIntroduce());

        return UserDto.UpdateUserInfomationResponse.of(userRepository.save(user));
    }

    // US-0003
    public UserDto.SearchUserInformationResponse searchUserInfo(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(UserService::triggerExceptionForNotFoundMember);

        return UserDto.SearchUserInformationResponse.of(user);
    }

    // US-0001
    public List<CuratorListResponseDto> listupCurators() {

        List<Hotuser> hotuserList = hotuserRepository.findAll();
        List<CuratorListResponseDto> curatorDtoList = new ArrayList<>();

        hotuserList.stream()
                .forEach(hotuser -> {
                    User user = userRepository.findById(hotuser.getUserId()).get();
                    curatorDtoList.add(
                            new CuratorListResponseDto(user.getId(), user.getEmoji(), user.getNickname()));
                });

        curatorDtoList.stream()
                .forEach(curator -> curator.setReviewCount(
                        reviewRepository.countByUserId(curator.getUserId())));

        return curatorDtoList;
    }


}
