package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.support.biz.service.BeanService;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The service interface of {@link UserProfile}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface UserProfileService extends BeanService<UserProfile, String> {

    /**
     * Returns an {@link Optional} of a {@link UserProfile} with given mobile.
     */
    Optional<UserProfile> findOneByMobile(@NotBlank String mobile);

}
