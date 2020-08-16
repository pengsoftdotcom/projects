package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.support.biz.service.TreeEntityService;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * The service interface of {@link Job}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface JobService extends TreeEntityService<Job, String> {

    /**
     * Grant roles.
     *
     * @param job   The {@link Job}.
     * @param roles The {@link Role}s to be granted.
     */
    void grantRoles(@NotNull Job job, List<Role> roles);

}
