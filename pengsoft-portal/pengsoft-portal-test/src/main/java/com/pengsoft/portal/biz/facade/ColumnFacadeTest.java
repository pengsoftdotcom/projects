package com.pengsoft.portal.biz.facade;

import com.pengsoft.portal.domain.entity.Column;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.test.BaseFacadeTest;
import com.pengsoft.system.starter.SystemApplication;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link ColumnFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = SystemApplication.class)
@ActiveProfiles({ "security", "system", "basedata", "portal" })
public class ColumnFacadeTest extends BaseFacadeTest<ColumnFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Column.class);
        authorityFacade.saveEntityAdminAuthorities(Column.class);
    }

}
