package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.BuildingService;
import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link BuildingFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class BuildingFacadeImpl extends TreeEntityFacadeImpl<BuildingService, Building, String> implements BuildingFacade {

    @Override
    public Optional<Building> findOneByCommunityAndParentAndName(final Community community, final Building parent, final String name) {
        return getService().findOneByCommunityAndParentAndName(community, parent, name);
    }

}
