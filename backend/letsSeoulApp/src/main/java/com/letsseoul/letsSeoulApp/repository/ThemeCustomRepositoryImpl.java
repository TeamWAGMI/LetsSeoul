package com.letsseoul.letsSeoulApp.repository;


import com.letsseoul.letsSeoulApp.domain.Theme;
import com.letsseoul.letsSeoulApp.dto.theme.ThemeDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import static com.letsseoul.letsSeoulApp.domain.QTheme.theme;
import static com.letsseoul.letsSeoulApp.domain.QTag.tag;
import static com.letsseoul.letsSeoulApp.domain.QThemeTag.themeTag;
import static com.letsseoul.letsSeoulApp.domain.QReview.review;
import static com.letsseoul.letsSeoulApp.domain.QThemeStore.themeStore;
import java.util.List;

@Repository
public class ThemeCustomRepositoryImpl implements ThemeCustomRepository {
    private final JPAQueryFactory queryFactory;

    public ThemeCustomRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public Page<Tuple> findDynamicQuery(ThemeDto.ThemeSearchPost themeSearchGet, Pageable pageable) {
        BooleanBuilder builder = new BooleanBuilder();
        if (!themeSearchGet.getKeyword().equals("")) {
            builder.or(theme.title.contains(themeSearchGet.getKeyword()));
        }

        if (themeSearchGet.getWho() != null) {
            for (String w : themeSearchGet.getWho()) {
                builder.or(tag.title.eq(w));
            }
        }
        if (themeSearchGet.getWhat() != null) {
            for (String w : themeSearchGet.getWhat()) {
                builder.or(tag.title.eq(w));
            }
        }
        if (themeSearchGet.getWhere() != null) {
            for (String w : themeSearchGet.getWhere()) {
                builder.or(tag.title.eq(w));
            }
        }
        JPQLQuery<Tuple> fetch = queryFactory.select(
                    theme.id,
                    theme.emoji,
                    theme.title
                    )
                    .from(theme)
                    .innerJoin(themeTag).on(theme.id.eq(themeTag.theme.id))
                    .innerJoin(tag).on(tag.id.eq(themeTag.tag.id))
                    .leftJoin(themeStore).on(themeStore.theme.id.eq(theme.id))
                    .leftJoin(review).on(themeStore.id.eq(review.themeStore.id).and(review.status.eq("E")))
                    .where(builder)
                    .groupBy(theme.id)
                    .orderBy(themeStore.id.count().desc())
                    .offset(pageable.getOffset())
                    .limit(pageable.getPageSize());
        List<Tuple> fetch1 = fetch.fetch();
        return new PageImpl<>(fetch1, pageable, fetch.fetchCount());
        }

    @Override
    public Long findReviewCount(Long themeId) {
        List<Long> e = queryFactory.select(review.count())
                .from(review)
                .innerJoin(themeStore)
                .on(themeStore.id.eq(review.themeStore.id).and(review.status.eq("E")))
                .where(themeStore.theme.id.eq(themeId)).fetch();
        return e.get(0);
    }
}


