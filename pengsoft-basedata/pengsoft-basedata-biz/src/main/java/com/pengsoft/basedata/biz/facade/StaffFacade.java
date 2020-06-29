package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.StaffService;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link Staff}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface StaffFacade extends EntityFacade<StaffService, Staff, String>, StaffService {

}
