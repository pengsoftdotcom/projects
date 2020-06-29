package com.pengsoft.device.biz.facade;

import com.pengsoft.device.domain.entity.Group;
import com.pengsoft.device.starter.DeviceApplication;
import com.pengsoft.security.biz.facade.AuthorityFacade;
import com.pengsoft.security.biz.facade.RoleFacade;
import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.test.BaseFacadeTest;
import com.querydsl.core.types.dsl.StringPath;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.inject.Inject;

/**
 * {@link GroupFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = DeviceApplication.class)
@ActiveProfiles({ "security", "system", "basedata", "device" })
public class GroupFacadeTest extends BaseFacadeTest<GroupFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Inject
    private DeviceFacade deviceFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Group.class);
        authorityFacade.saveEntityAdminAuthorities(Group.class);
    }

    @Test
    public void findAll() {
        final var createdByPath = (StringPath) QueryDslUtils.getPath(Group.class, "createdBy");
        final var belongsToPath = (StringPath) QueryDslUtils.getPath(Group.class, "belongsTo");
        getFacade().findAll(createdByPath.in("1").and(belongsToPath.eq("2")), null);
    }

}
