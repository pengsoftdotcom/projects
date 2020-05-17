package com.pengsoft.support.commons.exception;

import org.springframework.http.ResponseEntity;

import java.lang.reflect.ParameterizedType;

/**
 * Convert an {@link Exception} to a {@link ResponseEntity}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ExceptionResponseEntityConverter<E extends Exception> {

    default boolean support(final Exception e) {
        final var genericInterface = getClass().getGenericInterfaces()[0];
        final var typeArguments = ((ParameterizedType) genericInterface).getActualTypeArguments();
        return typeArguments[0].equals(e.getClass());
    }

    ResponseEntity<Object> convert(E e);

}
