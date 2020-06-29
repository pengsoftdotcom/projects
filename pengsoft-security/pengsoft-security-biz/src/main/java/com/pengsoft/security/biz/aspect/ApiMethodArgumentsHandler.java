package com.pengsoft.security.biz.aspect;

import com.pengsoft.security.domain.entity.Owned;
import com.pengsoft.support.domain.entity.Entity;
import com.querydsl.core.types.Predicate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Handle the arguments of methods that effected by data authority to match data authority
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ApiMethodArgumentsHandler<T extends Entity<ID>, ID extends Serializable> {

    Predicate replace(Class<T> entityClass, Predicate predicate);

    boolean check(Owned entity);

    default boolean check(final List<Owned> entities) {
        for (final Owned entity : entities) {
            if (!check(entity)) {
                return false;
            }
        }
        return true;
    }

    boolean check(Class<T> entityClass, Collection<String> ids);

}
