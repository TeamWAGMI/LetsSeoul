package com.letsseoul.letsSeoulApp.domain;

import com.letsseoul.letsSeoulApp.config.audit.Auditable;
import lombok.*;

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

    @Setter
    @Column(nullable = false)
    private Integer score;

    @Setter
    @Column(nullable = false)
    private String content;

    @Setter
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
