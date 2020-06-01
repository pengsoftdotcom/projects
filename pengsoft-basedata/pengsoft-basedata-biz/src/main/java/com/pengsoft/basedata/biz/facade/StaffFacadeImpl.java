package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.StaffService;
import com.pengsoft.basedata.biz.service.UserProfileService;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.security.biz.service.UserService;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import com.pengsoft.support.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The implementer of {@link StaffFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class StaffFacadeImpl extends BeanFacadeImpl<StaffService, Staff, String> implements StaffFacade {

    @Inject
    private UserProfileService userProfileService;

    @Inject
    private UserService userService;

    @Override
    public Staff save(final Staff staff) {
        final var userProfile = userProfileService.findOneByMobile(staff.getUserProfile().getMobile()).orElse(staff.getUserProfile());
        if (StringUtils.isBlank(userProfile.getId())) {
            final var user = userService.findOneByMobile(userProfile.getMobile()).orElse(new User(userProfile.getMobile(), UUID.randomUUID().toString()));
            if (StringUtils.isBlank(user.getId())) {
                userService.save(user);
            }
            userProfile.setUser(user);
        } else {
            BeanUtils.copyProperties(staff.getUserProfile(), userProfile, "id", "mobile", "user", "version");
        }
        staff.setUserProfile(userProfileService.save(userProfile));
        return super.save(staff);
    }

    @Override
    public void setMajorJob(final UserProfile userProfile, final Job job) {
        getService().setMajorJob(userProfile, job);
    }

    @Override
    public Optional<Staff> findOneByUserProfileAndJob(final UserProfile userProfile, final Job job) {
        return getService().findOneByUserProfileAndJob(userProfile, job);
    }

    @Override
    public Optional<Staff> findOneByUserProfileAndMajorTrue(final UserProfile userProfile) {
        return getService().findOneByUserProfileAndMajorTrue(userProfile);
    }

    @Override
    public List<Staff> findAllByUserProfile(final UserProfile userProfile) {
        return getService().findAllByUserProfile(userProfile);
    }

    @Override
    public List<Staff> findAllByJobIn(final List<Job> jobs) {
        return getService().findAllByJobIn(jobs);
    }

}
