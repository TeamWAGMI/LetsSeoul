package com.letsseoul.letsSeoulApp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
public class User {

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

    private String introduce;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdDatetime;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDatetime;

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

    public User update(String name, String emoji) {
        this.nickname = name;
        this.emoji = emoji;
        return this;
    }

}
