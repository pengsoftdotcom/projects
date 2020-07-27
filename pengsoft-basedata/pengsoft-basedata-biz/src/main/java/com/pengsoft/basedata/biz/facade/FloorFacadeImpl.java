package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.FloorService;
import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link FloorFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class FloorFacadeImpl extends EntityFacadeImpl<FloorService, Floor, String> implements FloorFacade {

    @Override
    public Optional<Floor> findOneByBuildingAndName(final Building building, final String name) {
        return getService().findOneByBuildingAndName(building, name);
    }

}
