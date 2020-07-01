package com.pengsoft.portal.biz.api;

import com.pengsoft.portal.biz.facade.BannerFacade;
import com.pengsoft.portal.domain.entity.Banner;
import com.pengsoft.support.biz.api.TreeEntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Banner}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/banner")
public class BannerApi extends TreeEntityApi<BannerFacade, Banner, String> {

}
