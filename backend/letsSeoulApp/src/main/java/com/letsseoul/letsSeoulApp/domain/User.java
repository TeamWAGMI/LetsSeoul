package com.letsseoul.letsSeoulApp.domain;

import com.letsseoul.letsSeoulApp.config.audit.Auditable;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String emoji;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String introduce;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private String status;

    @Builder
    public User(String username, String origin, String emoji, String name, String role) {
        this.username = username;
        this.origin = origin;
        this.emoji = emoji;
        this.nickname = name;
        this.role = role;
        this.status = "E"; // 유저를 생성할 때에는 기본 status값을 E(Enable)로 한다.
    }

    public User updateUserEmoji(String emoji) {
        this.nickname = name;
        this.emoji = emoji;
        return this;
    }

    public User updateUserInfo(String nickname, String introduce) {
        this.nickname = nickname;
        this.introduce = introduce;
        return this;
    }

}
