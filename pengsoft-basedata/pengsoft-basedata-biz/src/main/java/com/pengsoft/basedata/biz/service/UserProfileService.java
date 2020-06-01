package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.BeanService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
     *
     * @param mobile {@link UserProfile}'s mobile
     */
    Optional<UserProfile> findOneByMobile(@NotBlank String mobile);

    /**
     * Returns an {@link Optional} of a {@link UserProfile} with given user.
     *
     * @param user {@link UserProfile}'s user
     */
    Optional<UserProfile> findOneByUser(@NotNull User user);


}
