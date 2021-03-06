package com.pengsoft.system.biz.facade;

import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.test.BaseFacadeTest;
import com.pengsoft.system.domain.entity.Captcha;
import com.pengsoft.system.starter.SystemApplication;
import lombok.With;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link CaptchaFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SystemApplication.class)
@ActiveProfiles({ "security", "system" })
public class CaptchaFacadeTest extends BaseFacadeTest<CaptchaFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Captcha.class);
        authorityFacade.saveEntityAdminAuthorities(Captcha.class);
    }

    @Test
    @WithUserDetails("18508101366")
    public void save() {
        var entity = new Captcha();
        entity.setCode("1");
        entity.setExpiredAt(DateUtils.currentDateTime());
        entity.setUser(SecurityUtils.getUser());
        getFacade().save(entity);
    }

}
