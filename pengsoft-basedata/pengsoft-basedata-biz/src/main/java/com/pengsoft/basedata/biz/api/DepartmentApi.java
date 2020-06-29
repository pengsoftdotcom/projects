package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.DepartmentFacade;
import com.pengsoft.basedata.domain.entity.Department;
import com.pengsoft.support.biz.api.TreeEntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Department}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/department")
public class DepartmentApi extends TreeEntityApi<DepartmentFacade, Department, String> {

}
