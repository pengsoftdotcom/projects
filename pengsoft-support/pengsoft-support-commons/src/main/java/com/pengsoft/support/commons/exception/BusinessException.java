package com.pengsoft.support.commons.exception;

/**
 * The business exception.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 499533128937536640L;

    private final String code;

    private final Object[] args;

    public BusinessException(final String code, final Object... args) {
        this.code = code;
        this.args = args;
    }

    public String getCode() {
        return code;
    }

    public Object[] getArgs() {
        return args;
    }

}
