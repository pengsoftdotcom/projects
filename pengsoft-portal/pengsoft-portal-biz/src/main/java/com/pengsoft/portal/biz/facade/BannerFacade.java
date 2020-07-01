package com.pengsoft.portal.biz.facade;

import com.pengsoft.portal.biz.service.BannerService;
import com.pengsoft.portal.domain.entity.Banner;
import com.pengsoft.support.biz.facade.TreeEntityFacade;

/**
 * The facade interface of {@link Banner}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface BannerFacade extends TreeEntityFacade<BannerService, Banner, String>, BannerService {
}
