package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.GroupFacade;
import com.pengsoft.device.domain.entity.DeviceGroup;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link DeviceGroup}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/group")
public class GroupApi extends EntityApi<GroupFacade, DeviceGroup, String> {

}
