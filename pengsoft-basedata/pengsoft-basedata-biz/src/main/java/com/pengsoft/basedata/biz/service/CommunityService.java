package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.service.EntityService;
import com.pengsoft.system.domain.entity.Region;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * The service interface of {@link Community}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface CommunityService extends EntityService<Community, String> {

    /**
     * Returns an {@link Optional} of a {@link Community} with given region and name.
     *
     * @param region {@link Community}'s region
     * @param name   {@link Community}'s name
     */
    Optional<Community> findOneByRegionAndName(@NotNull Region region, @NotBlank String name);

}
