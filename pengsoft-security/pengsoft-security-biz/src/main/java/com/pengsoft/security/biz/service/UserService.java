package com.pengsoft.security.biz.service;

import com.pengsoft.security.commons.validation.Password;
import com.pengsoft.security.commons.validation.Username;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.BeanService;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * The service interface of {@link User}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface UserService extends BeanService<User, String> {

    /**
     * Change password.
     *
     * @param id          The user id.
     * @param oldPassword The old password.
     * @param newPassword The new password.
     */
    void changePassword(@NotBlank String id, @Password String oldPassword, @Password String newPassword);

    /**
     * Reset password.
     *
     * @param id       The user id.
     * @param password The password to be reset.
     */
    void resetPassword(@NotBlank String id, @NotBlank String password);

    /**
     * Grant roles.
     *
     * @param user  The {@link User}.
     * @param roles The {@link Role}s to be granted.
     */
    void grantRoles(@NotNull User user, List<Role> roles);

    /**
     * Set the major role.
     *
     * @param user The {@link User}.
     * @param role The major {@link Role}.
     */
    void setMajorRole(@NotNull User user, @NotNull Role role);

    /**
     * Returns an {@link Optional} of a {@link User} with given username.
     *
     * @param username The username.
     */
    Optional<User> findOneByUsername(@Username String username);

}
