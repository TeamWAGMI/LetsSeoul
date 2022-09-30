package com.letsseoul.letsSeoulApp.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
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

    protected Store() {
    }

    @Builder
    public Store(String itemid, String title, String address, String lat, String lng) {
        this.itemid = itemid;
        this.title = title;
        this.address = address;
        this.lat = lat;
        this.lng = lng;
    }
}