package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.config.auth.LoginUser;
import com.letsseoul.letsSeoulApp.config.auth.dto.SessionUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@Slf4j
public class OAuth2Controller {

    @GetMapping("/oauth2/users")
    public ResponseEntity<SessionUser> oauthLoginSuccess(@LoginUser SessionUser user, Authentication authentication) {

//        log.info("###### 여기입니다 : " + authentication.toString());

        return ResponseEntity.ok().body(user);
    }
}
