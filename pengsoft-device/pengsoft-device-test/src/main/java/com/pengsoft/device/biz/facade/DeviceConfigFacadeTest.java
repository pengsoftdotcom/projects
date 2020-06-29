package com.pengsoft.device.biz.facade;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.pengsoft.device.domain.entity.DeviceConfig;
import com.pengsoft.device.starter.DeviceApplication;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.test.BaseFacadeTest;

/**
 * {@link DeviceConfigFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
@SpringBootTest(classes = DeviceApplication.class)
@ActiveProfiles({ "security", "system", "basedata", "device" })
public class DeviceConfigFacadeTest extends BaseFacadeTest<DeviceConfigFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(DeviceConfig.class);
        authorityFacade.saveEntityAdminAuthorities(DeviceConfig.class);
    }

}
