package com.pengsoft.portal.facade;

import com.pengsoft.portal.biz.facade.BannerFacade;
import com.pengsoft.portal.domain.entity.Banner;
import com.pengsoft.portal.starter.PortalApplication;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.test.BaseFacadeTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link BannerFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = PortalApplication.class)
@ActiveProfiles({ "security", "system", "basedata", "portal" })
public class BannerFacadeTest extends BaseFacadeTest<BannerFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Banner.class);
        authorityFacade.saveEntityAdminAuthorities(Banner.class);
    }

}
