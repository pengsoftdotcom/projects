package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.OrganizationFacade;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.support.biz.api.TreeBeanApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Organization}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/organization")
public class OrganizationApi extends TreeBeanApi<OrganizationFacade, Organization, String> {

}
