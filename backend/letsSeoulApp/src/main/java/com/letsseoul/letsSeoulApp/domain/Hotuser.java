package com.letsseoul.letsSeoulApp.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Hotuser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private String content;
}
