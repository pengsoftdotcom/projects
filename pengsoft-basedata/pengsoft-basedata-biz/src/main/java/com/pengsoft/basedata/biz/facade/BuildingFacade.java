package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.BuildingService;
import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.support.biz.facade.TreeEntityFacade;

/**
 * The facade interface of {@link Building}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface BuildingFacade extends TreeEntityFacade<BuildingService, Building, String>, BuildingService {

}
