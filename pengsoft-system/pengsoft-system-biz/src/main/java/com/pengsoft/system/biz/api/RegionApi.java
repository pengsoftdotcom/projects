package com.pengsoft.system.biz.api;

import com.pengsoft.security.commons.annotation.Authorized;
import com.pengsoft.support.biz.api.TreeEntityApi;
import com.pengsoft.system.biz.facade.RegionFacade;
import com.pengsoft.system.domain.entity.Region;
import com.pengsoft.system.domain.json.RegionWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The web api of {@link Region}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/region")
public class RegionApi extends TreeEntityApi<RegionFacade, Region, String> {

    @Authorized
    @GetMapping("find-all-indexed-cities")
    public List<RegionWrapper> findAllIndexedCities() {
        return getFacade().findAllIndexedCities().stream().map(RegionWrapper::new).collect(Collectors.toList());
    }

}