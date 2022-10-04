package com.letsseoul.letsSeoulApp.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String itemid;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lng;

    @OneToMany(mappedBy = "store")
    List<ThemeStore> themeStoreList = new ArrayList<>();

    @Builder
    public Store(String itemid, String title, String address, String lat, String lng) {
        this.itemid = itemid;
        this.title = title;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
}