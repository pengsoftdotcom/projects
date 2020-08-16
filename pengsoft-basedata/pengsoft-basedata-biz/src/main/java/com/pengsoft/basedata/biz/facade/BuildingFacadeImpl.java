package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.BuildingService;
import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link BuildingFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class BuildingFacadeImpl extends TreeEntityFacadeImpl<BuildingService, Building, String> implements BuildingFacade {

}
