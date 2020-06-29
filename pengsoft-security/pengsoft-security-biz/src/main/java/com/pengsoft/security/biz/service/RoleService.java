package com.pengsoft.security.biz.service;

import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.support.biz.service.TreeEntityService;
import com.pengsoft.support.domain.entity.Entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * The service interface of {@link Role}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface RoleService extends TreeEntityService<Role, String> {

    /**
     * Save the entity admin by the given entity class
     *
     * @param entityClass The entity class
     * @return The entity admin.
     */
    Role saveEntityAdmin(Class<? extends Entity<? extends Serializable>> entityClass);

    /**
     * Grant authorities.
     *
     * @param role        The {@link Role}
     * @param authorities The authorities to be granted.
     */
    void grantAuthorities(@NotNull Role role, List<Authority> authorities);

    /**
     * Returns an {@link Optional} of a {@link Role} with given code.
     *
     * @param code {@link Authority}'s code
     */
    Optional<Role> findOneByCode(@NotBlank String code);

}
