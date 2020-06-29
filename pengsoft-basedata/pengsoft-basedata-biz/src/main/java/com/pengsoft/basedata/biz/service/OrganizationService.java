package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.support.biz.service.TreeEntityService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * The service interface of {@link Organization}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface OrganizationService extends TreeEntityService<Organization, String> {

    /**
     * Returns an {@link Optional} of a {@link Organization} with given code.
     */
    Optional<Organization> findOneByCode(@NotBlank String code);

    /**
     * Returns an {@link Optional} of a {@link Organization} with given name.
     */
    Optional<Organization> findOneByName(@NotBlank String name);

    /**
     * Returns all organizations with given admin.
     *
     * @param admin {@link Organization}'s admin
     */
    List<Organization> findAllByAdmin(@NotNull Person admin);

}
