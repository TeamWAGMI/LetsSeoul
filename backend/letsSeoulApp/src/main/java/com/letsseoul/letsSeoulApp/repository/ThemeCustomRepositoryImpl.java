package com.letsseoul.letsSeoulApp.repository;


import com.letsseoul.letsSeoulApp.domain.Theme;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.letsseoul.letsSeoulApp.domain.QTheme.theme;
import static com.letsseoul.letsSeoulApp.domain.QTag.tag;
import static com.letsseoul.letsSeoulApp.domain.QThemeTag.themeTag;
import static com.letsseoul.letsSeoulApp.domain.QThemeStore.themeStore;
import java.util.List;

@Repository
public class ThemeCustomRepositoryImpl implements ThemeCustomRepository {
    private final JPAQueryFactory queryFactory;

    public ThemeCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Tuple> findDynamicQuery(String keyword, String[] who, String[] what, String[] where, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!keyword.equals("")) {
            builder.or(theme.title.contains(keyword));
        }

        if (who != null) {
            for (String w : who) {
                builder.or(tag.title.eq(w));
            }
        }
        if (what != null) {
            for (String w : what) {
                builder.or(tag.title.eq(w));
            }
        }
        if (where != null) {
            for (String w : where) {
                builder.or(tag.title.eq(w));
            }
        }
        JPQLQuery<Tuple> fetch = queryFactory.select(
                    theme.id,
                    theme.emoji,
                    theme.title,
                    themeStore.theme.id.count().as("cnt")
                    )
                    .from(theme)
                    .innerJoin(themeTag).on(theme.id.eq(themeTag.theme.id))
                    .innerJoin(tag).on(tag.id.eq(themeTag.tag.id))
                    .leftJoin(themeStore).on(themeStore.theme.id.eq(theme.id))
                    .where(builder)
                    .groupBy(theme.id)
                    .orderBy(themeStore.theme.id.count().desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize());

            return new PageImpl<>(fetch.fetch(), pageable, fetch.fetchCount());
        }
    }


