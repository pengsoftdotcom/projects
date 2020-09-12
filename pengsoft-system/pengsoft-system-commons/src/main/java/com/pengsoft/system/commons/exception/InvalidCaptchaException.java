package com.pengsoft.system.commons.exception;

import org.springframework.security.oauth2.common.exceptions.ClientAuthenticationException;

/**
 * Invalid captcha exception
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class InvalidCaptchaException extends ClientAuthenticationException {

    public InvalidCaptchaException(String msg, Throwable t) {
        super(msg, t);
    }

    public InvalidCaptchaException(String msg) {
        super(msg);
    }

    @Override
    public String getOAuth2ErrorCode() {
        return "invalid_captcha";
    }

}
