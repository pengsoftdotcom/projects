package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.OrganizationService;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.support.biz.facade.TreeEntityFacade;

/**
 * The facade interface of {@link Organization}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface OrganizationFacade extends TreeEntityFacade<OrganizationService, Organization, String>, OrganizationService {

}
