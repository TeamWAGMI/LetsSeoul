package com.letsseoul.letsSeoulApp.repository;

import com.letsseoul.letsSeoulApp.domain.FollowTheme;
import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FollowThemeRepository extends JpaRepository<FollowTheme,Long> {
    Boolean existsByUserAndTheme(User user, Theme theme);
    Optional<FollowTheme> findByUserAndTheme(User user, Theme theme);

    List<FollowTheme> findByUser(User user);

    @Query("select count(f) from FollowTheme f where f.theme.id in(:themeId) group by f.theme.id order by f.createdDatetime desc")
    List<Long> countByThemeIds(List<Long> themeId);
    Page<FollowTheme> findByUser(User user, Pageable pageable);
    //Page<FollowTheme> countByTheme(Theme theme);
}
