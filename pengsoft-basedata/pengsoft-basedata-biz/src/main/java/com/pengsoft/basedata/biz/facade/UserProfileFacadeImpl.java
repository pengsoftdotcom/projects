package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.UserProfileService;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.security.biz.service.RoleService;
import com.pengsoft.security.biz.service.UserService;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import com.pengsoft.support.commons.exception.MissingConfigurationException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The implementer of {@link UserProfileFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class UserProfileFacadeImpl extends BeanFacadeImpl<UserProfileService, UserProfile, String> implements UserProfileFacade {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Override
    public UserProfile save(final UserProfile userProfile) {
        final var user = userService.save(new User(userProfile.getMobile(), UUID.randomUUID().toString()));
        final var roles = roleService.findOneByCode(Role.USER).map(List::of).orElseThrow(() -> new MissingConfigurationException("No role user configured."));
        userService.grantRoles(user, roles);
        userProfile.setUser(user);
        return super.save(userProfile);
    }

    @Override
    public Optional<UserProfile> findOneByMobile(@NotBlank final String mobile) {
        return getService().findOneByMobile(mobile);
    }

    @Override
    public Optional<UserProfile> findOneByUser(@NotNull final User user) {
        return getService().findOneByUser(user);
    }

}
