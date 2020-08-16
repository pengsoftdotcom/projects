package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.CommunityFacade;
import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.api.TreeEntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Community}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/community")
public class CommunityApi extends TreeEntityApi<CommunityFacade, Community, String> {

}
