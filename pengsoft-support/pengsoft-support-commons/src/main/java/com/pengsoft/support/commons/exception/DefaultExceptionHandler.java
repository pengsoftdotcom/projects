package com.pengsoft.support.commons.exception;

import java.util.List;

import javax.inject.Inject;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * The default exception handler.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    @Inject
    private List<ExceptionResponseEntityConverter> converters;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(final Exception e) {
        return converters.stream()
                .filter(converter -> converter.support(e))
                .findFirst()
                .map(converter -> converter.convert(e))
                .orElseThrow(() -> new MissingConfigurationException("Not possible!"));
    }

}
