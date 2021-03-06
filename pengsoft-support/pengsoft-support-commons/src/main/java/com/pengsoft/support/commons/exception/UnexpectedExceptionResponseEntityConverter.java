package com.pengsoft.support.commons.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Named;

/**
 * Convert an unexpected exception to a {@link ResponseEntity}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@Named
public class UnexpectedExceptionResponseEntityConverter implements ExceptionResponseEntityConverter {

    @Override
    public boolean support(final Throwable cause) {
        return true;
    }

    @Override
    public ResponseEntity<Object> convert(final Throwable cause) {
        log.error("An unexpected exception occurred.", cause);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
