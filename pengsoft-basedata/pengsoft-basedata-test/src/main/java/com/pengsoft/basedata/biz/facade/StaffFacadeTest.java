package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.basedata.starter.BasedataApplication;
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
 * {@link StaffFacade} unit test.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@SpringBootTest(classes = BasedataApplication.class)
@ActiveProfiles({ "security", "system", "basedata" })
public class StaffFacadeTest extends BaseFacadeTest<StaffFacade> {

    @Inject
    private RoleFacade roleFacade;

    @Inject
    private AuthorityFacade authorityFacade;

    @Inject
    private UserProfileFacade userProfileFacade;

    @Inject
    private JobFacade jobFacade;

    @Test
    public void init() {
        roleFacade.saveEntityAdmin(Staff.class);
        authorityFacade.saveEntityAdminAuthorities(Staff.class);
    }

    @Test
    public void save() {
        final var staff = new Staff();
        staff.setJob(jobFacade.findPage(new BooleanBuilder(), PageRequest.of(0, 1)).getContent().get(0));
        final var userProfile = new UserProfile();
        userProfile.setName("党鹏");
        userProfile.setMobile("18508101366");
        staff.setUserProfile(userProfile);
        getFacade().save(staff);
    }

    @Test
    public void findAllByJobIn() {
        getFacade().findAllByJobIn(jobFacade.findAll(new BooleanBuilder(), null));
    }

}
