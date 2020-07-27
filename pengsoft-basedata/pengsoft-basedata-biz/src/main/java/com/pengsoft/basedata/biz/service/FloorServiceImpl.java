package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.FloorRepository;
import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link FloorService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class FloorServiceImpl extends EntityServiceImpl<FloorRepository, Floor, String> implements FloorService {

    @Override
    public Floor save(final Floor floor) {
        findOneByBuildingAndName(floor.getBuilding(), floor.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, floor)) {
                throw getExceptions().constraintViolated("name", "Exists", floor.getName());
            }
        });
        return super.save(floor);
    }

    @Override
    public Optional<Floor> findOneByBuildingAndName(final Building building, final String name) {
        return getRepository().findOneByBuildingAndName(building, name);
    }

    @Override
    protected Sort getDefaultSort() {
        return Sort.by("name");
    }

}
