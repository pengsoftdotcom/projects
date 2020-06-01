package com.pengsoft.basedata.domain.util;

import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.security.domain.util.SecurityUtils;

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
     * Returns current user profile.
     */
    public static UserProfile getUserProfile() {
        return SecurityUtils.get("userProfile", UserProfile.class);
    }

    /**
     * Returns current user's current job.
     */
    public static Job getCurrentJob() {
        return SecurityUtils.get("currentJob", Job.class);
    }

    /**
     * Returns current user's major job.
     */
    public static Job getMajorJob() {
        return SecurityUtils.get("majorJob", Job.class);
    }

    /**
     * Returns current user's department.
     */
    public static Department getDepartment() {
        return Optional.ofNullable(SecurityUtils.get("currentJob", Job.class)).map(Job::getDepartment).orElse(null);
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
        return Optional.ofNullable(getDepartment()).map(Department::getOrganization).orElse(null);
    }

    /**
     * Returns current user's organization id.
     */
    public static String getOrganizationId() {
        return Optional.ofNullable(getOrganization()).map(Organization::getId).orElse(null);
    }

}
