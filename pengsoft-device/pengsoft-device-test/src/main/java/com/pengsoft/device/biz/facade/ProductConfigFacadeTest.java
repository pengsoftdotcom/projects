package com.pengsoft.device.biz.facade;

import com.pengsoft.device.domain.entity.ProductConfig;
import com.pengsoft.device.starter.DeviceApplication;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.test.BaseFacadeTest;
import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link ProductConfigFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = DeviceApplication.class)
@ActiveProfiles({ "security", "system", "basedata", "device" })
public class ProductConfigFacadeTest extends BaseFacadeTest<ProductConfigFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Inject
    private ProductFacade productFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(ProductConfig.class);
        authorityFacade.saveEntityAdminAuthorities(ProductConfig.class);
    }

    @Test
    public void findOneByProductAndCode() {
        final var product = productFacade.findAll(new BooleanBuilder(), null).get(0);
        getFacade().findOneByProductAndCode(product, "1");
    }

    @Test
    public void findOneByProductAndName() {
        final var product = productFacade.findAll(new BooleanBuilder(), null).get(0);
        getFacade().findOneByProductAndName(product, "1");
    }

}
