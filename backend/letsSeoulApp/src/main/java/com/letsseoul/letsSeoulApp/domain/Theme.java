package com.letsseoul.letsSeoulApp.domain;


import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Theme {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String emoji;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    @CreatedDate
    private LocalDateTime createdDatetime;

    @Column(nullable = false)
    @LastModifiedDate
    private LocalDateTime modifiedDatetime;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "theme")
    private List<ThemeStore> themeStoreList = new ArrayList<>();

    @OneToMany(mappedBy = "theme")
    private List<ThemeTag> themeTagList = new ArrayList<>();

    public void addThemeStore(ThemeStore themeStore) {
        this.themeStoreList.add(themeStore);
    }

    @Builder
    public Theme(String emoji, String title, LocalDateTime createdDatetime, LocalDateTime modifiedDatetime) {
        this.emoji = emoji;
        this.title = title;
        this.createdDatetime = createdDatetime;
        this.modifiedDatetime = modifiedDatetime;
        this.status = "D"; // 새로 생성한 테마의 기본 상태는 비활성화(D, Disable) 상태다.
    }
}