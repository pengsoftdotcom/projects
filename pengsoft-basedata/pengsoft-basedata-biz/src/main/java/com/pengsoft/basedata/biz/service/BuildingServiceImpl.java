package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.BuildingRepository;
import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link BuildingService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class BuildingServiceImpl extends TreeEntityServiceImpl<BuildingRepository, Building, String> implements BuildingService {

    @Override
    public Building save(final Building building) {
        final var communityId = building.getCommunity().getId();
        final var parentId = Optional.ofNullable(building.getParent()).map(Building::getId).orElse(null);
        getRepository().findOneByCommunityIdAndParentIdAndName(communityId, parentId, building.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, building)) {
                throw getExceptions().constraintViolated("name", "Exists", building.getName());
            }
        });
        return super.save(building);
    }

}
