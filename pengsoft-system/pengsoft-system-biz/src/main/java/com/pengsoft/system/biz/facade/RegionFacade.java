package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.TreeBeanFacade;
import com.pengsoft.system.biz.service.RegionService;
import com.pengsoft.system.domain.entity.Region;

/**
 * The implementer of {@link RegionFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface RegionFacade extends TreeBeanFacade<RegionService, Region, String>, RegionService {

}
