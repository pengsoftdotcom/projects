package com.pengsoft.support.commons.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

/**
 * The default exception handler.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private final List<ExceptionResponseEntityConverter> converters;

    public DefaultExceptionHandler(final List<ExceptionResponseEntityConverter> converters) {
        Collections.reverse(converters);
        this.converters = converters;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(final Exception e) {
        return converters.stream()
                .filter(converter -> converter.support(e))
                .findFirst()
                .map(converter -> converter.convert(e))
                .orElseThrow(() -> new MissingConfigurationException("Not possible!"));
    }

}
