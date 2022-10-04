package com.letsseoul.letsSeoulApp.domain;

import com.letsseoul.letsSeoulApp.config.audit.Auditable;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theme_store_id")
    private ThemeStore themeStore;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private Integer score;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String status;

    @Builder
    public Review(ThemeStore themeStore, User user, Integer score, String content) {
        this.themeStore = themeStore;
        this.user = user;
        this.score = score;
        this.content = content;
        this.status = "E"; // 리뷰 생성 시 상태값 기본 "E"(활성)
    }
}
