package com.letsseoul.letsSeoulApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
//        config.addAllowedOrigin("https://letsseoul.com");
        config.addAllowedOriginPattern("*"); // 테스트 : CORS 에러로 인해서 일단 전부 허용하도록 해둔다.
//        config.setAllowedOrigins(List.of("https://letsseoul.com", "http://localhost:3000")); // 테스트 : 리액트 로컬 허용
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", config);
        source.registerCorsConfiguration("/oauth2/**", config);

        return new CorsFilter(source);
    }
}
