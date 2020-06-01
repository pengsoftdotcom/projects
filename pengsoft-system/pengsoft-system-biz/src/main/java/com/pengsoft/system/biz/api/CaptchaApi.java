package com.pengsoft.system.biz.api;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.support.biz.api.BeanApi;
import com.pengsoft.support.commons.validation.Mobile;
import com.pengsoft.system.biz.facade.CaptchaFacade;
import com.pengsoft.system.biz.messaging.Messaging;
import com.pengsoft.system.domain.entity.Captcha;
import com.pengsoft.system.domain.json.CaptchaWrapper;

/**
 * The web api of {@link Captcha}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/captcha")
public class CaptchaApi extends BeanApi<CaptchaFacade, Captcha, String> {

    @Inject
    private UserFacade userFacade;

    @Messaging("authenticationCaptchaMessageBuilder")
    @PostMapping("generate-for-authentication")
    @JsonIgnore
    public CaptchaWrapper generateForAuthentication(@NotBlank @Mobile final String mobile) {
        final var user = userFacade.findOneByMobile(mobile).orElseThrow(() -> getExceptions().entityNotFound(mobile));
        return new CaptchaWrapper(getFacade().generate(user, 5 * 60));
    }

}
