package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.CommunityService;
import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link CommunityFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class CommunityFacadeImpl extends TreeEntityFacadeImpl<CommunityService, Community, String> implements CommunityFacade {

}
