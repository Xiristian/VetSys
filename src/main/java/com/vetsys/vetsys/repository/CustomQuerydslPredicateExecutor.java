package com.vetsys.vetsys.repository;

import com.querydsl.core.BooleanBuilder;
import com.vetsys.vetsys.enterprise.BooleanBuilderUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import com.querydsl.core.types.Predicate;

public interface CustomQuerydslPredicateExecutor<T> extends QuerydslPredicateExecutor<T> {
    @Override
    List<T> findAll(Predicate predicate);

    default List<T> findAll(String filter, Class<T> entityType) {
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter, entityType);
        return this.findAll(booleanBuilder);
    }

    default Page<T> findAll(String filter, Class<T> entityType, Pageable pageable) {
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter, entityType);
        return this.findAll(booleanBuilder, pageable);
    }
}
