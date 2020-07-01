package com.pengsoft.portal.biz.service;

import com.pengsoft.portal.domain.entity.Banner;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.TreeEntityService;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link Banner}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface BannerService extends TreeEntityService<Banner, String> {

    /**
     * Returns an {@link Optional} of a {@link Banner} with given parent and name.
     *
     * @param name {@link Banner}'s name
     */
    Optional<User> findOneByParentAndName(Banner parent, @NotBlank String name);

}
