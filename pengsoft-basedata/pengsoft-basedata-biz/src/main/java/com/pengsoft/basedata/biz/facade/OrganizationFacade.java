package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.OrganizationService;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.support.biz.facade.TreeBeanFacade;

/**
 * The facade interface of {@link Organization}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface OrganizationFacade extends TreeBeanFacade<OrganizationService, Organization, String>, OrganizationService {

}
