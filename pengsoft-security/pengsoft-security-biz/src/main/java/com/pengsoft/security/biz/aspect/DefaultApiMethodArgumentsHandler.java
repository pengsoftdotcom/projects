package com.pengsoft.security.biz.aspect;

import com.pengsoft.security.domain.entity.Owned;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.apache.commons.lang3.StringUtils;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Default implementer of {@link ApiMethodArgumentsHandler}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class DefaultApiMethodArgumentsHandler<T extends Beanable<ID>, ID extends Serializable> implements ApiMethodArgumentsHandler<T, ID> {

    @Override
    public Predicate replace(final Class entityClass, final Predicate predicate) {
        if (Owned.class.isAssignableFrom(entityClass)) {
            final var createdByPath = (StringPath) QueryDslUtils.getPath(entityClass, "createdBy");
            return createdByPath.eq(SecurityUtils.getUserId()).and(predicate);
        } else {
            return predicate;
        }
    }

    @Override
    public boolean check(final T bean) {
        final var userId = SecurityUtils.getUserId();
        final var createdBy = ((Owned) bean).getCreatedBy();
        return StringUtils.equals(userId, createdBy);
    }

}
