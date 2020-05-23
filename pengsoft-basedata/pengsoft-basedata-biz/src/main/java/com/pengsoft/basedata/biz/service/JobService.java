package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.support.biz.service.TreeBeanService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link Job}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface JobService extends TreeBeanService<Job, String> {

    /**
     * Returns an {@link Optional} of a {@link Job} with given department, parent and name.
     *
     * @param department {@link Job}'s department
     * @param parent     {@link Job}'s parent
     * @param name       {@link Job}'s name
     */
    Optional<Job> findOneByDepartmentAndParentAndName(@NotNull Department department, Job parent, @NotBlank String name);

}
