package com.pengsoft.system.biz.api;

import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.support.biz.api.EntityApi;
import com.pengsoft.support.commons.validation.Mobile;
import com.pengsoft.system.biz.facade.CaptchaFacade;
import com.pengsoft.system.domain.entity.Captcha;
import com.pengsoft.system.domain.json.CaptchaWrapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;

/**
 * The web api of {@link Captcha}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/captcha")
public class CaptchaApi extends EntityApi<CaptchaFacade, Captcha, String> {

    @Inject
    private UserFacade userFacade;

    //    @Messaging("authenticationCaptchaMessageBuilder")
    @PostMapping("generate")
    public CaptchaWrapper generateForAuthentication(@NotBlank @Mobile final String username) {
        final var user = userFacade.findOneByMobile(username).orElseThrow(() -> getExceptions().constraintViolated("username", "NotExists"));
        return new CaptchaWrapper(getFacade().generate(user));
    }

}
