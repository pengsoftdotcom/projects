package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.starter.BasedataApplication;
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
 * {@link OrganizationFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = BasedataApplication.class)
@ActiveProfiles({ "security", "system", "basedata" })
public class OrganizationFacadeTest extends BaseFacadeTest<OrganizationFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Organization.class);
        authorityFacade.saveEntityAdminAuthorities(Organization.class);
    }

    @Test
    public void findAll() {
        final var createdByPath = (StringPath) QueryDslUtils.getPath(Organization.class, "createdBy");
        final var belongsToPath = (StringPath) QueryDslUtils.getPath(Organization.class, "belongsTo");
        getFacade().findAll(createdByPath.eq("1").and(belongsToPath.eq("2")), null);
    }

}
