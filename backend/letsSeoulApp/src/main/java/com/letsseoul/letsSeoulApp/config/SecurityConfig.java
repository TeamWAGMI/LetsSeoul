package com.letsseoul.letsSeoulApp.config;

import com.letsseoul.letsSeoulApp.config.auth.CustomOAuth2AuthenticationFailureHandler;
import com.letsseoul.letsSeoulApp.config.auth.CustomOAuth2AuthenticationSuccessHandler;
import com.letsseoul.letsSeoulApp.config.auth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CorsConfig corsConfig;
    private final CustomOAuth2UserService customOAuth2UserService;
    private final CustomOAuth2AuthenticationSuccessHandler customOAuth2AuthenticationSuccessHandler;
    private final CustomOAuth2AuthenticationFailureHandler customOAuth2AuthenticationFailureHandler;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .addFilter(corsConfig.corsFilter())
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/oauth2/authorization/**").permitAll()
//                .antMatchers("/api/v1/**").hasRole("USER") // 테스트 단계에서만 필요한 주석. 개발 끝나면 인증 필요한 api는 필터에 추가 || @PreAuthorized 추가
//                .anyRequest().authenticated() // 또는 여기와 같이 authenticated()로 전부 인증을 요청할 수도 있음.
                .and()
                .logout()
                .logoutUrl("/oauth2/logout").deleteCookies("JSESSIONID", "remember-me", "SESSION")
                .logoutSuccessUrl("/")
                .and()
                .oauth2Login()
                .successHandler(customOAuth2AuthenticationSuccessHandler)
                .failureHandler(customOAuth2AuthenticationFailureHandler)
                .userInfoEndpoint().userService(customOAuth2UserService);

        return http.build();
    }
}
