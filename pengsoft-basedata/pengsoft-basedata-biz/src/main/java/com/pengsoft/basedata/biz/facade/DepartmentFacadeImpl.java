package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.DepartmentService;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.support.biz.facade.TreeBeanFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link DepartmentFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class DepartmentFacadeImpl extends TreeBeanFacadeImpl<DepartmentService, Department, String> implements DepartmentFacade {

    @Override
    public Optional<Organization> findOneByOrganizationAndParentAndName(final Organization organization, final Department parent, final String name) {
        return getService().findOneByOrganizationAndParentAndName(organization, parent, name);
    }

}
