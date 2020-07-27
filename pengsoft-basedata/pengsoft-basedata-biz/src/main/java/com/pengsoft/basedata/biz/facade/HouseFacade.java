package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.HouseService;
import com.pengsoft.basedata.domain.entity.House;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link House}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface HouseFacade extends EntityFacade<HouseService, House, String>, HouseService {

}
