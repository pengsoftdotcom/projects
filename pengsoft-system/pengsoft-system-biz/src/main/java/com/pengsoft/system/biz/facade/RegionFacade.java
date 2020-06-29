package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.TreeEntityFacade;
import com.pengsoft.system.biz.service.RegionService;
import com.pengsoft.system.domain.entity.Region;

/**
 * The facade interface of {@link Region}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface RegionFacade extends TreeEntityFacade<RegionService, Region, String>, RegionService {

}
