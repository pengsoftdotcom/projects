package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.CommunityService;
import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.facade.TreeEntityFacade;

/**
 * The facade interface of {@link Community}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface CommunityFacade extends TreeEntityFacade<CommunityService, Community, String>, CommunityService {

}
