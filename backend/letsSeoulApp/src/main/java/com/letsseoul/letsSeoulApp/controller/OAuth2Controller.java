package com.letsseoul.letsSeoulApp.controller;

import com.letsseoul.letsSeoulApp.config.auth.LoginUser;
import com.letsseoul.letsSeoulApp.config.auth.dto.SessionUser;
import com.letsseoul.letsSeoulApp.util.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class OAuth2Controller {

    @GetMapping("/oauth2/success")
    public ResponseEntity oauthLoginSuccess(@LoginUser SessionUser user) {

        if (user != null) {
            log.info("### OAuth2Controller.oauthLoginSuccess's SessionUser instance : " + user.toString()); //테스트 로그
        }

        return ResponseEntity.ok().body(user);
    }
}
