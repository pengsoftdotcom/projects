package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.HouseFacade;
import com.pengsoft.basedata.domain.entity.House;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link House}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/house")
public class HouseApi extends EntityApi<HouseFacade, House, String> {

}
