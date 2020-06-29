package com.pengsoft.device.biz.facade;

import com.pengsoft.device.domain.entity.Device;
import com.pengsoft.device.starter.DeviceApplication;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.test.BaseFacadeTest;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link DeviceFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = DeviceApplication.class)
@ActiveProfiles({ "security", "system", "basedata", "device" })
public class DeviceFacadeTest extends BaseFacadeTest<DeviceFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Inject
    private ProductFacade productFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Device.class);
        authorityFacade.saveEntityAdminAuthorities(Device.class);
    }

    @Test
    public void findFirstByActivatedFalseAndProductAndCodeStartsWith() {
        final var product = productFacade.findPage(new BooleanBuilder(), PageRequest.of(0, 1)).getContent().get(0);
        getFacade().findFirstByActivatedFalseAndProductAndCodeStartsWith(product, "unactivated");
    }

}
