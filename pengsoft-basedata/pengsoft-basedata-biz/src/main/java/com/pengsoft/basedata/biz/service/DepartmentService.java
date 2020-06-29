package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.support.biz.service.TreeEntityService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link Department}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DepartmentService extends TreeEntityService<Department, String> {

    /**
     * Returns an {@link Optional} of a {@link Department} with given organization, parent and name.
     *
     * @param organization {@link Department}'s organization
     * @param parent       {@link Department}'s parent
     * @param name         {@link Department}'s name
     */
    Optional<Organization> findOneByOrganizationAndParentAndName(@NotNull Organization organization, Department parent, @NotBlank String name);

}
