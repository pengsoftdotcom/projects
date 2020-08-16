package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.FloorRepository;
import com.pengsoft.basedata.biz.repository.HouseRepository;
import com.pengsoft.basedata.domain.entity.House;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * The implementer of {@link HouseService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class HouseServiceImpl extends EntityServiceImpl<HouseRepository, House, String> implements HouseService {

    @Inject
    private FloorRepository floorRepository;

    @Override
    public House save(final House house) {
        final var floorId = house.getFloor().getId();
        final var floor = floorRepository.findById(floorId).orElseThrow(() -> getExceptions().entityNotFound(floorId));
        getRepository().findOneByFloorAndCode(floor, house.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, house)) {
                throw getExceptions().constraintViolated("code", "Exists", house.getName());
            }
        });
        if (StringUtils.isBlank(house.getName())) {
            house.setName(house.getCode());
        }
        house.setFloor(floor);
        house.setBuilding(floor.getBuilding());
        house.setCommunity(floor.getBuilding().getCommunity());
        return super.save(house);
    }

}
