package com.pengsoft.support.commons.exception;

import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Convert an unexpected exception to a {@link ResponseEntity}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Order(Ordered.LOWEST_PRECEDENCE)
@Named
public class UnexpectedExceptionExceptionResponseEntityConverter implements ExceptionResponseEntityConverter {

    private static final Logger log = LoggerFactory.getLogger(UnexpectedExceptionExceptionResponseEntityConverter.class);

    @Override
    public boolean support(final Exception e) {
        return true;
    }

    @Override
    public ResponseEntity<Object> convert(final Exception e) {
        log.error("An unexpected exception occurred.", e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
