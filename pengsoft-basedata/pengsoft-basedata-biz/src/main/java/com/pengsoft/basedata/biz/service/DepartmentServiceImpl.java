package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.DepartmentRepository;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link DepartmentService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class DepartmentServiceImpl extends TreeEntityServiceImpl<DepartmentRepository, Department, String> implements DepartmentService {

    @Override
    public Department save(final Department department) {
        getRepository().findOneByOrganizationAndParentAndName(department.getOrganization(), department.getParent(), department.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, department)) {
                throw getExceptions().constraintViolated("name", "Exists", department.getName());
            }
        });
        if (StringUtils.isBlank(department.getSimpleName())) {
            department.setSimpleName(department.getName());
        }
        return super.save(department);
    }

}
