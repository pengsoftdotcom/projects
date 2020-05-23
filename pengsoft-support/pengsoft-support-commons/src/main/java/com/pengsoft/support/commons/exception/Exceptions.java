package com.pengsoft.support.commons.exception;

import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ConstraintViolationException;
import java.util.Set;

/**
 * Provide some useful methods to create exception new instances.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class Exceptions {

    @Inject
    private MessageSource messageSource;

    /**
     * Returns {@link ConstraintViolationException} instance by given message and property path.
     *
     * @param path The entity property path.
     * @param code The message template code.
     * @param args The message template arguments.
     * @return The new instance.
     */
    public ConstraintViolationException constraintViolated(final String path, final String code, final Object... args) {
        final var message = messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
        final var propertyPath = PathImpl.createPathFromString(path);
        return new ConstraintViolationException(
                Set.of(ConstraintViolationImpl.forBeanValidation(null, null, null, message, null, null, null, null, propertyPath, null, null)));
    }

    /**
     * Returns {@link IllegalArgumentException} instance
     *
     * @param key The entity key.
     */
    public IllegalArgumentException entityNotFound(final String key) {
        return new IllegalArgumentException("the entity with given key('" + key + "') has been deleted or the given key is invalid.");
    }

}
