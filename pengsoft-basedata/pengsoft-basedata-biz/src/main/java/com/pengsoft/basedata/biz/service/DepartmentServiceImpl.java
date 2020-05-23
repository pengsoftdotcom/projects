package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.DepartmentRepository;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.support.biz.service.TreeBeanServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link DepartmentService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class DepartmentServiceImpl extends TreeBeanServiceImpl<DepartmentRepository, Department, String> implements DepartmentService {

    @Override
    public Department save(final Department department) {
        findOneByOrganizationAndParentAndName(department.getOrganization(), department.getParent(), department.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, department)) {
                throw exceptions.constraintViolated("name", "Exists", department.getName());
            }
        });
        return super.save(department);
    }

    @Override
    public Optional<Organization> findOneByOrganizationAndParentAndName(final Organization organization, final Department parent, final String name) {
        return getRepository().findOneByOrganizationAndParentAndName(organization, parent, name);
    }

}
