package com.pengsoft.security.biz.aspect;

import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;

import java.io.Serializable;

/**
 * Build a {@link Predicate}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DataAuthorityPredicateBuilder<T extends Beanable<ID>, ID extends Serializable> {

    Predicate build(Class<T> entityClass, Predicate predicate);

}
