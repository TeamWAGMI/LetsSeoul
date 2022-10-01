package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.Hottheme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotthemeRepository extends JpaRepository<Hottheme, Long> {
}
