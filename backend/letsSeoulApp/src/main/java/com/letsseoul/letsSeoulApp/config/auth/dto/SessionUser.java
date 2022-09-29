package com.letsseoul.letsSeoulApp.config.auth.dto;

import com.letsseoul.letsSeoulApp.domain.User;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Getter
public class SessionUser implements Serializable {

    private Long id;
    private String username;
    private String origin;
    private String emoji;
    private String nickname;
    private String role;
    private String status;


    public SessionUser(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.origin = user.getOrigin();
        this.emoji = user.getEmoji();
        this.nickname = user.getNickname();
        this.role = user.getRole();
        this.status = user.getStatus();
    }
}
