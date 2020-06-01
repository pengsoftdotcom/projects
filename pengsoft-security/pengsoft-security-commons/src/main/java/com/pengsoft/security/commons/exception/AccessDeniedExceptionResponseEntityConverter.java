package com.pengsoft.security.commons.exception;

import com.pengsoft.support.commons.exception.ExceptionResponseEntityConverter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;

import javax.inject.Named;

/**
 * Convert a {@link AccessDeniedException} to a {@link ResponseEntity}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class AccessDeniedExceptionResponseEntityConverter implements ExceptionResponseEntityConverter {

    @Override
    public boolean support(final Exception e) {
        return e instanceof AccessDeniedException;
    }

    @Override
    public ResponseEntity<Object> convert(final Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
    }

}
