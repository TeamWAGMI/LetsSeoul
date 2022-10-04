package com.letsseoul.letsSeoulApp.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id",nullable = false)
    private String itemId;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lng;

    @Builder
    public Store(String itemId, String title, String address, String lat, String lng) {
        this.itemId = itemId;
        this.title = title;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
}