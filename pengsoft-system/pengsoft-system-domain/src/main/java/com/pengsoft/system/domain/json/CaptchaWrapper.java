package com.pengsoft.system.domain.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pengsoft.system.domain.entity.Captcha;

import java.io.Serializable;

/**
 * This wrapper just for JSON serialization.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class CaptchaWrapper implements Serializable {

    private static final long serialVersionUID = 5958167478830762828L;

    @JsonIgnore
    private final Captcha captcha;

    public CaptchaWrapper(final Captcha captcha) {
        this.captcha = captcha;
    }

    public Captcha getCaptcha() {
        return captcha;
    }

}
