package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.support.biz.service.EntityService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link Floor}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface FloorService extends EntityService<Floor, String> {

    /**
     * Returns an {@link Optional} of a {@link Floor} with given building and name.
     *
     * @param building {@link Floor}'s building
     * @param name     {@link Floor}'s name
     */
    Optional<Floor> findOneByBuildingAndName(@NotNull Building building, @NotBlank String name);

}
