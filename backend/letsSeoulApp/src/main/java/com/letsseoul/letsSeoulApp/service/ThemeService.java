package com.letsseoul.letsSeoulApp.service;

import com.letsseoul.letsSeoulApp.domain.FollowTheme;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.domain.User;
import com.letsseoul.letsSeoulApp.dto.ThemeDto;
import com.letsseoul.letsSeoulApp.repository.FollowThemeRepository;
import com.letsseoul.letsSeoulApp.repository.ThemeRepository;
import com.letsseoul.letsSeoulApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ThemeService {

    private final ThemeRepository themeRepository;
    private final UserRepository userRepository;

    private final FollowThemeRepository followThemeRepository;

    //TH-0011
    public ThemeDto.checkDibsThemeResponse checkDibsTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        return ThemeDto.checkDibsThemeResponse.of(followThemeRepository.existsByUserAndTheme(user,theme));

    }
    //TH-0012 테마찜 등록
    public ThemeDto.RegistDibsThemeResponse registDibsTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        followThemeRepository.save(new FollowTheme(user,theme));
        return ThemeDto.RegistDibsThemeResponse.of();
    }

    //TH-0012 테마찜 취소

    public ThemeDto.cancelDibsThemeResponse cancelDibsTheme(Long userId, Long themeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저가 없습니다"));
        Theme theme = themeRepository.findById(themeId).orElseThrow(() -> new RuntimeException("테마가 없습니다"));
        FollowTheme followTheme = followThemeRepository.findByUserAndTheme(user, theme).orElseThrow(() -> new RuntimeException("찜한 테마가 없습니다"));
        followThemeRepository.delete(followTheme);
        return ThemeDto.cancelDibsThemeResponse.of();
    }


}
