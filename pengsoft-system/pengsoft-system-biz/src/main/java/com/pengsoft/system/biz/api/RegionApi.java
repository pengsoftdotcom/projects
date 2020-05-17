package com.pengsoft.system.biz.api;

import com.pengsoft.support.biz.api.TreeBeanApi;
import com.pengsoft.system.biz.facade.RegionFacade;
import com.pengsoft.system.domain.entity.Region;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Region}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/region")
public class RegionApi extends TreeBeanApi<RegionFacade, Region, String> {

}
