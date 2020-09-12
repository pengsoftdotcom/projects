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
    public boolean support(final Throwable cause) {
        return cause instanceof AccessDeniedException;
    }

    @Override
    public ResponseEntity<Object> convert(final Throwable cause) {
        return new ResponseEntity<>(cause.getMessage(), HttpStatus.FORBIDDEN);
    }

}
