package com.pengsoft.system.biz.service;

import com.pengsoft.support.biz.service.TreeBeanService;
import com.pengsoft.system.domain.entity.Region;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link Region}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface RegionService extends TreeBeanService<Region, String> {

    /**
     * Returns an {@link Optional} of a {@link Region} with given code.
     */
    Optional<Region> findOneByCode(@NotBlank String code);

}
