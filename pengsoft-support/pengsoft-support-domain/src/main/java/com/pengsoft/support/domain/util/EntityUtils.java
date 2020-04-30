package com.pengsoft.support.domain.util;

import com.pengsoft.support.domain.entity.Beanable;

import java.io.Serializable;
import java.util.Optional;

/**
 * The entity utility methods.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class EntityUtils {

    private EntityUtils() {
        
    }

    public static boolean eq(final Beanable<? extends Serializable> b1, final Beanable<? extends Serializable> b2) {
        if (b1 == b2) {
            return true;
        }
        if (b1 == null || b2 == null) {
            return false;
        }

        final var i1 = b1.getId();
        final var i2 = b2.getId();
        if (i1 == i2) {
            return true;
        }
        if (i1 == null || i2 == null) {
            return false;
        }
        return i1.equals(i2);
    }

    public static boolean ne(final Beanable<? extends Serializable> b1, final Beanable<? extends Serializable> b2) {
        return !eq(b1, b2);
    }

    public static <T extends Beanable<? extends Serializable>> boolean isPersisted(final T bean) {
        return Optional.ofNullable(bean).filter(b -> b.getId() != null && b.getCreatedAt() != null).isPresent();
    }

    public static <T extends Beanable<? extends Serializable>> boolean isNotPersisted(final T bean) {
        return !isPersisted(bean);
    }
}
