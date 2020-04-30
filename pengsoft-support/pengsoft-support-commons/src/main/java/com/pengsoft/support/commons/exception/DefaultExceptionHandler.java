package com.pengsoft.support.commons.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The default exception handler.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String PACKAGE_DELIMITER = ".";

    private static final Logger log = LoggerFactory.getLogger(DefaultExceptionHandler.class);

    @Inject
    private MessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public static ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException e) {
        final var body = e.getConstraintViolations().stream().collect(Collectors.toMap(
                cv -> {
                    var propertyPath = cv.getPropertyPath().toString();
                    if (propertyPath.contains(PACKAGE_DELIMITER)) {
                        propertyPath = StringUtils.substringAfter(propertyPath, PACKAGE_DELIMITER);
                        if (propertyPath.contains(PACKAGE_DELIMITER)) {
                            propertyPath = StringUtils.substringAfter(propertyPath, PACKAGE_DELIMITER);
                        }
                    }
                    return propertyPath;
                },
                cv -> List.of(cv.getMessage()),
                (final List<String> oldList, final List<String> newList) -> {
                    final var result = new ArrayList<String>();
                    result.addAll(oldList);
                    result.addAll(newList);
                    return result;
                }));
        log.debug("Validation failed.", e);
        return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusinessException(final BusinessException e) {
        final var code = e.getCode();
        final var args = e.getArgs();
        final var text = messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(Map.of("code", code, "text", text), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public static ResponseEntity<Object> handleUnexpectedException(final Exception e) {
        log.error("An unexpected exception occurred.", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
