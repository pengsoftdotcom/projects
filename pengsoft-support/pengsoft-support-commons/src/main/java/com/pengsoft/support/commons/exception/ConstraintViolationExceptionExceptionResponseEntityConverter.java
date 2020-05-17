package com.pengsoft.support.commons.exception;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
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
@Named
public class ConstraintViolationExceptionExceptionResponseEntityConverter implements ExceptionResponseEntityConverter<ConstraintViolationException> {

    private static final Logger log = LoggerFactory.getLogger(ConstraintViolationExceptionExceptionResponseEntityConverter.class);

    private static final String PACKAGE_DELIMITER = ".";

    @Inject
    private MessageSource messageSource;

    @Override
    public ResponseEntity<Object> convert(final ConstraintViolationException e) {
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
