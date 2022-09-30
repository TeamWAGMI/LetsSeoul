package com.letsseoul.letsSeoulApp.service;

import com.letsseoul.letsSeoulApp.domain.FollowTheme;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.MultiResponseDto;
import com.letsseoul.letsSeoulApp.dto.UserDto;
import com.letsseoul.letsSeoulApp.repository.FollowThemeRepository;
import com.letsseoul.letsSeoulApp.repository.UserRepository;
import com.letsseoul.letsSeoulApp.util.EmojiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final FollowThemeRepository followThemeRepository;

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

    // TH-0014
    public MultiResponseDto<UserDto.DibsTehemeResponse> viewListDibsThemes(Long userId, Pageable pageable) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Page<FollowTheme> followThemeList = followThemeRepository.findByUser(user,pageable);
        List<Long> themeList = followThemeList.stream().map(f -> f.getTheme().getId()).collect(Collectors.toList());
        List<Long> countList = followThemeRepository.countByThemeIds(themeList);
        return UserDto.DibsTehemeResponse.of(followThemeList,countList);

    }


}
