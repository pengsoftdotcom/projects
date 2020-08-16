package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.OrganizationRepository;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The implementer of {@link OrganizationService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class OrganizationServiceImpl extends TreeEntityServiceImpl<OrganizationRepository, Organization, String> implements OrganizationService {

    @Override
    public Organization save(final Organization organization) {
        getRepository().findOneByCode(organization.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, organization)) {
                throw getExceptions().constraintViolated("code", "Exists", organization.getCode());
            }
        });
        getRepository().findOneByName(organization.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, organization)) {
                throw getExceptions().constraintViolated("name", "Exists", organization.getCode());
            }
        });
        if (StringUtils.isBlank(organization.getSimpleName())) {
            organization.setSimpleName(organization.getName());
        }
        super.save(organization);
        if (!StringUtils.equals(organization.getId(), organization.getBelongsTo())) {
            getRepository().updateBelongsTo(organization.getId());
        }
        return organization;
    }

    @Override
    public List<Organization> findAllByAdmin(final Person admin) {
        return getRepository().findAllByAdmin(admin);
    }

}
