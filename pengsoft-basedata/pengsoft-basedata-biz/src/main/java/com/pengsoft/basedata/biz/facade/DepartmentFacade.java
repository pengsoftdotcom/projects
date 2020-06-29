package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.DepartmentService;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.support.biz.facade.TreeEntityFacade;

/**
 * The facade interface of {@link Department}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface DepartmentFacade extends TreeEntityFacade<DepartmentService, Department, String>, DepartmentService {

}
