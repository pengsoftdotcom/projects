package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.basedata.domain.entity.House;
import com.pengsoft.support.biz.service.EntityService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link House}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface HouseService extends EntityService<House, String> {

    /**
     * Returns an {@link Optional} of a {@link House} with given floor and code.
     *
     * @param floor {@link House}'s floor
     * @param code  {@link House}'s code
     */
    Optional<House> findOneByFloorAndCode(@NotNull Floor floor, @NotBlank String code);

}
