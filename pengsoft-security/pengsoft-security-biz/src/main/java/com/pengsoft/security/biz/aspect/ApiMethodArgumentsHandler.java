package com.pengsoft.security.biz.aspect;

import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;

import java.io.Serializable;
import java.util.List;

/**
 * Handle the arguments of methods that effected by data authority to match data authority
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ApiMethodArgumentsHandler<T extends Beanable<ID>, ID extends Serializable> {

    Predicate replace(Class<T> entityClass, Predicate predicate);

    boolean check(T bean);

    default boolean check(final List<T> beans) {
        for (final T bean : beans) {
            if (!check(bean)) {
                return false;
            }
        }
        return true;
    }

}
