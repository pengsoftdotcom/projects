package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Building;
import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.service.TreeEntityService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link Building}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface BuildingService extends TreeEntityService<Building, String> {

    /**
     * Returns an {@link Optional} of a {@link Building} with given community, parent and name.
     *
     * @param community {@link Building}'s community
     * @param parent    {@link Building}'s parent
     * @param name      {@link Building}'s name
     */
    Optional<Building> findOneByCommunityAndParentAndName(@NotNull Community community, Building parent, @NotBlank String name);

}
