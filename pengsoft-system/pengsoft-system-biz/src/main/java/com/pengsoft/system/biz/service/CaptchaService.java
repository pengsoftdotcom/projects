package com.pengsoft.system.biz.service;

import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.EntityService;
import com.pengsoft.system.domain.entity.Captcha;

/**
 * The service interface of {@link Captcha}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface CaptchaService extends EntityService<Captcha, String> {

    /**
     * Generate a captcha.
     *
     * @param user The {@link User} request a captcha.
     * @return The generated captcha.
     */
    Captcha generate(User user);

    /**
     * If the captcha is valid, remove this captcha.
     *
     * @param user The {@link User} request a captcha.
     * @param code The input captcha.
     * @return If true, the captcha is valid.
     */
    boolean isValid(User user, String code);


}
