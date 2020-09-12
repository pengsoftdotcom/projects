package com.pengsoft.system.biz.facade;

import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.system.biz.service.CaptchaService;
import com.pengsoft.system.domain.entity.Captcha;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link CaptchaFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class CaptchaFacadeImpl extends EntityFacadeImpl<CaptchaService, Captcha, String> implements CaptchaFacade {

    @Override
    public Captcha generate(final User user) {
        return getService().generate(user);
    }

    @Override
    public boolean isValid(final User user, final String code) {
        return getService().isValid(user, code);
    }

}
