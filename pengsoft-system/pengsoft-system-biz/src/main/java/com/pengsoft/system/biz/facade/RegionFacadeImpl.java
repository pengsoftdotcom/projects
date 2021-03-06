package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import com.pengsoft.system.biz.service.RegionService;
import com.pengsoft.system.domain.entity.Region;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The implementer of {@link RegionFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class RegionFacadeImpl extends TreeEntityFacadeImpl<RegionService, Region, String> implements RegionFacade {

    @Override
    public Optional<Region> findOneByCode(final String code) {
        return getService().findOneByCode(code);
    }

    @Override
    public List<Region> findAllIndexedCities() {
        return getService().findAllIndexedCities();
    }

}
