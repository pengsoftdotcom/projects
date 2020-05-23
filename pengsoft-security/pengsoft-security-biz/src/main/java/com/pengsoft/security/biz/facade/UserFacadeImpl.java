package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.UserService;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The implementer of {@link UserFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class UserFacadeImpl extends BeanFacadeImpl<UserService, User, String> implements UserFacade {

    @Override
    public void changePassword(final String id, final String oldPassword, final String newPassword) {
        getService().changePassword(id, oldPassword, newPassword);
    }

    @Override
    public void resetPassword(final String id, final String password) {
        getService().resetPassword(id, password);
    }

    @Override
    public void grantRoles(final User user, final List<Role> roles) {
        getService().grantRoles(user, roles);
    }

    @Override
    public void setMajorRole(final User user, final Role role) {
        getService().setMajorRole(user, role);
    }

    @Override
    public void signInSuccess(final String username) {
        getService().signInSuccess(username);
    }

    @Override
    public void signInFailure(final String username, final int allowSignInFailure) {
        getService().signInFailure(username, allowSignInFailure);
    }

    @Override
    public Optional<User> findOneByUsername(final String username) {
        return getService().findOneByUsername(username);
    }

    @Override
    public Optional<User> findOneByMobile(final String mobile) {
        return getService().findOneByMobile(mobile);
    }

    @Override
    public Optional<User> findOneByEmail(final String email) {
        return getService().findOneByEmail(email);
    }

    @Override
    public Optional<User> findOneByMpOpenId(final String mpOpenId) {
        return getService().findOneByMpOpenId(mpOpenId);
    }

}
