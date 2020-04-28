package com.pengsoft.support.domain.util;

import java.io.Serializable;

import com.pengsoft.support.domain.entity.Beanable;

/**
 * The entity utility methods.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class EntityUtils {

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

}
