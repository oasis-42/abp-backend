package dev.joelfrancisco.abp.enterprise;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface CustomQuerydslPredicateExecutor<T> extends QuerydslPredicateExecutor<T> {
    @Override
    Iterable<T> findAll(Predicate predicate);

    default Iterable<T> findAll(String filter, Class<T> entityType) {
        BooleanBuilder booleanBuilder = BooleanBuilderUtil.buildPredicateFromFilter(filter, entityType);
        return this.findAll(booleanBuilder);
    }
}
