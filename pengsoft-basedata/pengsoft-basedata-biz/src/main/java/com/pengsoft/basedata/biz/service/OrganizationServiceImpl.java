package com.pengsoft.basedata.biz.service;

import java.util.Optional;

import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pengsoft.basedata.biz.repository.OrganizationRepository;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.support.biz.service.TreeBeanServiceImpl;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.util.EntityUtils;

/**
 * The implementer of {@link OrganizationService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class OrganizationServiceImpl extends TreeBeanServiceImpl<OrganizationRepository, Organization, String> implements OrganizationService {

    @Override
    public Organization save(final Organization organization) {
        findOneByCode(organization.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, organization)) {
                throw getExceptions().constraintViolated("code", "Exists", organization.getCode());
            }
        });
        findOneByName(organization.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, organization)) {
                throw getExceptions().constraintViolated("name", "Exists", organization.getCode());
            }
        });
        if (StringUtils.isBlank(organization.getSimpleName())) {
            organization.setSimpleName(organization.getName());
        }
        return super.save(organization);
    }

    @Override
    public Optional<Organization> findOneByCode(final String code) {
        return getRepository().findOneByCode(code);
    }

    @Override
    public Optional<Organization> findOneByName(@NotBlank final String name) {
        return getRepository().findOneByName(name);
    }

}
