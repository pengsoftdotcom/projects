package com.pengsoft.basedata.domain.util;

import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.JobRole;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.security.domain.util.SecurityUtils;

import java.util.Arrays;
import java.util.Optional;

/**
 * {@link SecurityUtils} extension
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class SecurityUtilsExt {

    private SecurityUtilsExt() {

    }

    /**
     * Returns current person.
     */
    public static Person getPerson() {
        return SecurityUtils.get("person", Person.class);
    }

    /**
     * Returns current user's current job.
     */
    public static Job getCurrentJob() {
        return SecurityUtils.get("currentJob", Job.class);
    }

    /**
     * Returns current user's primary job.
     */
    public static Job getPrimaryJob() {
        return SecurityUtils.get("primaryJob", Job.class);
    }

    /**
     * Returns current user's department.
     */
    public static Department getDepartment() {
        return Optional.ofNullable(SecurityUtils.get("department", Department.class)).orElse(null);
    }

    /**
     * Returns current user's department id.
     */
    public static String getDepartmentId() {
        return Optional.ofNullable(getDepartment()).map(Department::getId).orElse(null);
    }

    /**
     * Returns current user's organization.
     */
    public static Organization getOrganization() {
        return Optional.ofNullable(SecurityUtils.get("organization", Organization.class)).orElse(null);
    }

    /**
     * Returns current user's organization id.
     */
    public static String getOrganizationId() {
        return Optional.ofNullable(getOrganization()).map(Organization::getId).orElse(null);
    }

    public static boolean hasAnyRole(final String... roleCodes) {
        if (SecurityUtils.hasAnyRole(roleCodes)) {
            return true;
        } else {
            return getCurrentJob().getJobRoles().stream().map(JobRole::getRole).anyMatch(role -> Arrays.stream(roleCodes).anyMatch(roleCode -> roleCode.equals(role.getCode())));
        }
    }

}
