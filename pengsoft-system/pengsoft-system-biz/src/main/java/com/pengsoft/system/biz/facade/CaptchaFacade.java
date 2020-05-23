package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.BeanFacade;
import com.pengsoft.system.biz.service.CaptchaService;
import com.pengsoft.system.domain.entity.Captcha;

/**
 * The facade interface of {@link Captcha}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface CaptchaFacade extends BeanFacade<CaptchaService, Captcha, String>, CaptchaService {

}
