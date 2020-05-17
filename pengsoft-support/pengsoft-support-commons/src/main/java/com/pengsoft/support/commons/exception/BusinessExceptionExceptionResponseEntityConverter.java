package com.pengsoft.support.commons.exception;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

/**
 * Convert a {@link BusinessException} to a {@link ResponseEntity}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class BusinessExceptionExceptionResponseEntityConverter implements ExceptionResponseEntityConverter<BusinessException> {

    @Inject
    private MessageSource messageSource;

    @Override
    public ResponseEntity<Object> convert(final BusinessException e) {
        final var code = e.getCode();
        final var args = e.getArgs();
        final var text = messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
        return new ResponseEntity<>(Map.of("code", code, "text", text), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
