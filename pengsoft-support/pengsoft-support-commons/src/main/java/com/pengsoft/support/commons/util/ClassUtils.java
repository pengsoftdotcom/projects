package com.pengsoft.support.commons.util;

import java.lang.reflect.ParameterizedType;

/**
 * The {@link Class} utility methods.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class ClassUtils extends org.apache.commons.lang3.ClassUtils {

    /**
     * Return the generic type of the superclass.
     *
     * @param superclass the superclass
     * @param index the index of arguments.
     * @return
     */
    public static Class<?> getGenericType(final Class<?> superclass, final int index) {
        final var genericSuperclass = superclass.getGenericSuperclass();
        if (!(genericSuperclass instanceof ParameterizedType)) {
            return Object.class;
        }
        final var typeArguments = ((ParameterizedType) genericSuperclass).getActualTypeArguments();
        if (index >= typeArguments.length || index < 0) {
            return Object.class;
        }
        if (!(typeArguments[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) typeArguments[index];
    }

}
