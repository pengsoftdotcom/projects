package com.pengsoft.support.commons.exception;

/**
 * If this exception occurs, it means that some necessary data or configuration is missing.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class MissingConfigurationException extends RuntimeException {

    private static final long serialVersionUID = 7049692874095706163L;

    public MissingConfigurationException(final String message) {
        super(message);
    }

}
