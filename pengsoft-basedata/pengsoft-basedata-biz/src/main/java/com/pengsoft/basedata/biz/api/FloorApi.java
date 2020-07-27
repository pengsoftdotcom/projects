package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.FloorFacade;
import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Floor}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/floor")
public class FloorApi extends EntityApi<FloorFacade, Floor, String> {

}
