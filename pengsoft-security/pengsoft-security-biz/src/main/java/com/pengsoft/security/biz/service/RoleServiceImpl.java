package com.pengsoft.security.biz.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.inject.Inject;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pengsoft.security.biz.repository.RoleAuthorityRepository;
import com.pengsoft.security.biz.repository.RoleRepository;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.RoleAuthority;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.biz.service.TreeBeanServiceImpl;
import com.pengsoft.support.domain.entity.Beanable;
import com.pengsoft.support.domain.util.EntityUtils;

/**
 * The implementer of {@link RoleService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class RoleServiceImpl extends TreeBeanServiceImpl<RoleRepository, Role, String> implements RoleService {

    @Inject
    private RoleAuthorityRepository roleAuthorityRepository;

    @Override
    public Role save(final Role role) {
        findOneByCode(role.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, role)) {
                throw getExceptions().constraintViolated("code", "Exists", role.getCode());
            }
        });
        return super.save(role);
    }

    @Override
    public Role saveEntityAdmin(final Class<? extends Beanable<? extends Serializable>> entityClass) {
        final var admin = createRoleIfNotExists(null, Role.ADMIN);
        final var moduleAdminCode = SecurityUtils.getModuleAdminCode(entityClass);
        final var moduleAdmin = createRoleIfNotExists(admin, moduleAdminCode);
        final var entityAdminCode = SecurityUtils.getEntityAdminCode(entityClass);
        return createRoleIfNotExists(moduleAdmin, entityAdminCode);
    }

    private Role createRoleIfNotExists(final Role parent, final String code) {
        final Optional<Role> optional = findOneByCode(code);
        if (optional.isEmpty()) {
            return save(new Role(parent, code));
        } else {
            return optional.get();
        }
    }

    @Override
    public void grantAuthorities(final Role role, final List<Authority> authorities) {
        final var source = role.getRoleAuthorities();
        final var target = authorities.stream().map(authority -> new RoleAuthority(role, authority)).collect(Collectors.toList());
        final var deleted = source.stream()
                .filter(s -> target.stream()
                        .noneMatch(t -> EntityUtils.eq(s.getRole(), t.getRole()) && EntityUtils.eq(s.getAuthority(), t.getAuthority())))
                .collect(Collectors.toList());
        roleAuthorityRepository.deleteAll(deleted);
        source.removeAll(deleted);
        final var created = target.stream()
                .filter(t -> source.stream()
                        .noneMatch(s -> EntityUtils.eq(t.getRole(), s.getRole()) && EntityUtils.eq(t.getAuthority(), s.getAuthority())))
                .collect(Collectors.toList());
        roleAuthorityRepository.saveAll(created);
        source.addAll(created);
        super.save(role);
    }

    @Override
    public Optional<Role> findOneByCode(final String code) {
        return getRepository().findOneByCode(code);
    }

}
