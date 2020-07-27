package com.pengsoft.basedata.biz.api;

import com.pengsoft.basedata.biz.facade.BuildingFacade;
import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.support.biz.api.TreeEntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Building}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/building")
public class BuildingApi extends TreeEntityApi<BuildingFacade, Building, String> {

}
