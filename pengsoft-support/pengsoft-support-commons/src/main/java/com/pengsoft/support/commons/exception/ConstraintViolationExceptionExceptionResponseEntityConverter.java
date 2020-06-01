package com.pengsoft.support.commons.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Named;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Convert a {@link ConstraintViolationException} to a {@link ResponseEntity}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Named
public class ConstraintViolationExceptionExceptionResponseEntityConverter implements ExceptionResponseEntityConverter {

    private static final String PACKAGE_DELIMITER = ".";

    @Override
    public boolean support(final Exception e) {
        return e instanceof ConstraintViolationException;
    }

    @Override
    public ResponseEntity<Object> convert(final Exception exception) {
        final var e = (ConstraintViolationException) exception;
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

}
