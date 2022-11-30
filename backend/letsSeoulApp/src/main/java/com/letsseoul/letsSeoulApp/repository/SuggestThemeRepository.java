package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.SuggestTheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SuggestThemeRepository extends JpaRepository<SuggestTheme, Long> {
}
