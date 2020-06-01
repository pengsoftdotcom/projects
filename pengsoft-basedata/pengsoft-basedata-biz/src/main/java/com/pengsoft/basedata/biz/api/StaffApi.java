package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.StaffFacade;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.support.biz.api.BeanApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Staff}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/staff")
public class StaffApi extends BeanApi<StaffFacade, Staff, String> {

}
