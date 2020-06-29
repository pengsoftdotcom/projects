package com.pengsoft.device.biz.facade;

import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.device.starter.DeviceApplication;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.test.BaseFacadeTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link PurchaseBatchFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = DeviceApplication.class)
@ActiveProfiles({ "security", "system", "basedata", "device" })
public class PurchaseBatchFacadeTest extends BaseFacadeTest<PurchaseBatchFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(PurchaseBatch.class);
        authorityFacade.saveEntityAdminAuthorities(PurchaseBatch.class);
    }

}
