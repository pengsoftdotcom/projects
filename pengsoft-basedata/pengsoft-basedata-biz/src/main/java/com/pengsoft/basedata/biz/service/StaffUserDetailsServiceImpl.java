package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.StaffUserDetails;
import com.pengsoft.basedata.domain.UserProfileUserDetails;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.JobRole;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.security.biz.service.DefaultUserDetailsServiceImpl;
import com.pengsoft.security.domain.DefaultUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * The implementer of {@link StaffUserDetailsService}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class StaffUserDetailsServiceImpl extends DefaultUserDetailsServiceImpl implements StaffUserDetailsService {

    @Inject
    private StaffService staffService;

    @Inject
    private UserProfileService userProfileService;

    @Override
    public StaffUserDetails setMajorJob(@NotNull final Job job) {
        return null;
    }

    @Override
    public StaffUserDetails setCurrentJob(@NotNull final Job job) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final var userDetails = (DefaultUserDetails) super.loadUserByUsername(username);
        final var userProfileOptionl = userProfileService.findOneByUser(userDetails.getUser());
        if (userProfileOptionl.isEmpty()) {
            return userDetails;
        } else {
            final var userProfile = userProfileOptionl.get();
            final var staffOptional = staffService.findOneByUserProfileAndMajorTrue(userProfile);
            if (staffOptional.isEmpty()) {
                return new UserProfileUserDetails(userProfile, userDetails.getRoles(), userDetails.getMajorRole(), userDetails.getAuthorities());
            } else {
                final var staffs = staffService.findAllByUserProfile(userProfile);
                final var staff = staffOptional.get();
                final var jobs = staffs.stream().map(Staff::getJob).collect(Collectors.toList());
                final var job = staff.getJob();
                final var roles = job.getJobRoles().stream().map(JobRole::getRole).collect(Collectors.toList());
                final var authorities = new ArrayList<GrantedAuthority>();
                roles.forEach(role -> authorities.addAll(getAllAuthorities(role)));
                return new StaffUserDetails(staff, jobs, roles, authorities);
            }
        }
    }

}
