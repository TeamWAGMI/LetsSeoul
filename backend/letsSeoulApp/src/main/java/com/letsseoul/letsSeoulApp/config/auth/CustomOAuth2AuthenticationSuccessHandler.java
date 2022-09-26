package com.letsseoul.letsSeoulApp.config.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomOAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        // 이 메서드에 있는 로그들은 서비스 단계에서는 삭제되어야 한다. //

        log.info("### 인증 성공 : OAuth2AuthenticationSuccessHandler.onAuthenticationSuccess");
        log.info("### authentication : {}" + authentication);

        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
        log.info("### authentication's principal :");
        token.getPrincipal().getAttributes().forEach((i, k) -> log.info("### {} = {}", i, k));

        log.info("### request's attributes : ");
        request.getAttributeNames().asIterator().forEachRemaining(s -> log.info("{} : {}", s, request.getAttribute(s)));

        response.sendRedirect("/oauth2/success");
        handle(request, response, authentication);


    }
}
