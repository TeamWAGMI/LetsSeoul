package com.letsseoul.letsSeoulApp.domain;

import com.letsseoul.letsSeoulApp.config.audit.Auditable;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
public class Tag extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer divnum;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String status;

}