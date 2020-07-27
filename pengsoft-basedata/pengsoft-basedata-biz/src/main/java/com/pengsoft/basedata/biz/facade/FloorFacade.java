package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.FloorService;
import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link Floor}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface FloorFacade extends EntityFacade<FloorService, Floor, String>, FloorService {

}
