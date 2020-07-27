package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.domain.entity.House;
import com.pengsoft.basedata.starter.BasedataApplication;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.test.BaseFacadeTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link HouseFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = BasedataApplication.class)
@ActiveProfiles({ "security", "system", "basedata" })
public class HouseFacadeTest extends BaseFacadeTest<HouseFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(House.class);
        authorityFacade.saveEntityAdminAuthorities(House.class);
    }

}
