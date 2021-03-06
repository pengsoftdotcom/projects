package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.PersonUserDetails;
import com.pengsoft.basedata.domain.StaffUserDetails;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.JobRole;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.basedata.domain.util.SecurityUtilsExt;
import com.pengsoft.security.biz.service.DefaultUserDetailsServiceImpl;
import com.pengsoft.security.domain.DefaultUserDetails;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.domain.util.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * The implementer of {@link StaffUserDetailsService}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class StaffUserDetailsServiceImpl extends DefaultUserDetailsServiceImpl implements StaffUserDetailsService {

    public static final String ORGANIZATION_ADMIN = "organization_admin";
    @Inject
    private StaffService staffService;

    @Inject
    private PersonService personService;

    @Inject
    private OrganizationService organizationService;

    @Override
    public DefaultUserDetails setPrimaryJob(final Job job) {
        final var userDetails = (StaffUserDetails) SecurityUtils.getUserDetails();
        if (userDetails.getJobs().stream().anyMatch(j -> EntityUtils.eq(j, job))) {
            userDetails.setPrimaryJob(job);
            personService.findOne(SecurityUtilsExt.getPerson().getId()).ifPresent(userDetails::setPerson);
            staffService.setPrimaryJob(SecurityUtilsExt.getPerson(), job);
        }
        return userDetails;
    }

    @Override
    public DefaultUserDetails setCurrentJob(final Job job) {
        final var userDetails = (StaffUserDetails) SecurityUtils.getUserDetails();
        if (userDetails.getJobs().stream().anyMatch(j -> EntityUtils.eq(j, job))) {
            userDetails.setCurrentJob(job);
            userDetails.setDepartment(job.getDepartment());
            userDetails.setOrganization(job.getDepartment().getOrganization());
            final var authorities = new HashSet<GrantedAuthority>();
            userDetails.getRoles().forEach(role -> authorities.addAll(getAllAuthorities(role)));
            userDetails.setAuthorities(authorities);
        }
        return userDetails;
    }

    @Override
    public DefaultUserDetails setOrganization(final Organization organization) {
        final var userDetails = (PersonUserDetails) SecurityUtils.getUserDetails();
        final var role = userDetails.getCurrentRole();
        if (role != null && StringUtils.equals(ORGANIZATION_ADMIN, role.getCode())) {
            userDetails.setOrganization(organization);
        }
        return userDetails;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final var userDetails = (DefaultUserDetails) super.loadUserByUsername(username);
        final var personOptional = personService.findOneByUser(userDetails.getUser());
        if (personOptional.isEmpty()) {
            return userDetails;
        } else {
            final var person = personOptional.get();
            final var roles = userDetails.getRoles();
            Collection<Organization> organizations = null;
            if (roles.stream().anyMatch(role -> role.getCode().equals(ORGANIZATION_ADMIN))) {
                organizations = organizationService.findAllByAdmin(person);
            }
            final var staffOptional = staffService.findOneByPersonAndPrimaryTrue(person);
            if (staffOptional.isPresent()) {
                final var staffs = staffService.findAllByPerson(person);
                final var staff = staffOptional.get();
                final var jobs = staffs.stream().map(Staff::getJob).collect(Collectors.toList());
                final var job = staff.getJob();
                roles.addAll(job.getJobRoles().stream().map(JobRole::getRole).collect(Collectors.toList()));
                final var authorities = new ArrayList<GrantedAuthority>();
                roles.forEach(role -> authorities.addAll(getAllAuthorities(role)));
                return new StaffUserDetails(staff, jobs, organizations, roles, authorities);
            } else {
                return new PersonUserDetails(person, organizations, roles, userDetails.getPrimaryRole(), userDetails.getAuthorities());
            }
        }
    }

}
