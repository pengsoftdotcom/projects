package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.FloorRepository;
import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
        getRepository().findOneByBuildingIdAndName(floor.getBuilding().getId(), floor.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, floor)) {
                throw getExceptions().constraintViolated("name", "Exists", floor.getName());
            }
        });
        return super.save(floor);
    }

    @Override
    protected Sort getDefaultSort() {
        return Sort.by("name");
    }

}
