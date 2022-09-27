package com.letsseoul.letsSeoulApp.domain;

import lombok.Getter;
import javax.persistence.*;

@Entity
@Getter
public class ThemeStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    private Long store;

    public ThemeStore() {
    }

}