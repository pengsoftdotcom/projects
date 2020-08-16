package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.DepartmentService;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link DepartmentFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class DepartmentFacadeImpl extends TreeEntityFacadeImpl<DepartmentService, Department, String> implements DepartmentFacade {

}
