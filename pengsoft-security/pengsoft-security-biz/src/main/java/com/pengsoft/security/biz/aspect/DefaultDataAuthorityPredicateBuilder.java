package com.pengsoft.security.biz.aspect;

import com.pengsoft.security.domain.entity.Owned;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Default implementer of {@link DataAuthorityPredicateBuilder}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class DefaultDataAuthorityPredicateBuilder<T extends Beanable<ID>, ID extends Serializable> implements DataAuthorityPredicateBuilder<T, ID> {

    @Override
    public Predicate build(final Class<T> entityClass, final Predicate predicate) {
        if (Owned.class.isAssignableFrom(entityClass)) {
            final var createdByPath = (StringPath) QueryDslUtils.getPath(entityClass, "createdBy");
            return createdByPath.eq(SecurityUtils.getUserId()).and(predicate);
        } else {
            return predicate;
        }
    }

}
