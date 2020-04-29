package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.AuthorityService;
import com.pengsoft.security.biz.service.RoleService;
import com.pengsoft.security.commons.annotation.Authenticated;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import com.pengsoft.support.commons.exception.MissingConfgurationException;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.Beanable;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The implementer of {@link AuthorityFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class AuthorityFacadeImpl extends BeanFacadeImpl<AuthorityService, Authority, String> implements AuthorityFacade {

    @Inject
    private RoleService roleService;

    @Override
    public List<Authority> saveEntityAdminAuthorities(final Class<? extends Beanable<? extends Serializable>> entityClass) {
        final var entityAdminCode = SecurityUtils.getEntityAdminCode(entityClass);
        final var entityAdmin = roleService.findOneByCode(entityAdminCode)
                .orElseThrow(() -> new MissingConfgurationException("'" + entityClass.getClass().getName() + "' entity admin not found"));

        Class<?> apiClass = null;
        try {
            apiClass = Class.forName(RegExUtils.replaceFirst(entityClass.getName(), ".domain.entity.", ".biz.api.") + "Api");
        } catch (final ClassNotFoundException e) {
            throw new IllegalArgumentException("get api class from '" + entityClass.getName() + "' error", e);
        }
        final var authorityCodePrefix = SecurityUtils.getEntityAdminAuthorityCodePrefixFromEntityClass(entityClass);
        final var authorities = new ArrayList<Authority>();
        authorities.addAll(getAuthoritiesFromApi(apiClass, RequestMapping.class, authorityCodePrefix));
        authorities.addAll(getAuthoritiesFromApi(apiClass, GetMapping.class, authorityCodePrefix));
        authorities.addAll(getAuthoritiesFromApi(apiClass, PostMapping.class, authorityCodePrefix));
        authorities.addAll(getAuthoritiesFromApi(apiClass, PutMapping.class, authorityCodePrefix));
        authorities.addAll(getAuthoritiesFromApi(apiClass, DeleteMapping.class, authorityCodePrefix));
        roleService.grantAuthorities(entityAdmin, authorities);
        return authorities;
    }

    private List<Authority> getAuthoritiesFromApi(final Class<?> apiClass, final Class<? extends Annotation> mappingClass, final String authorityCodePrefix) {
        final var authorities = new ArrayList<Authority>();
        MethodUtils.getMethodsListWithAnnotation(apiClass, mappingClass, true, false).stream()
                .filter(method -> method.getAnnotation(Authenticated.class) == null)
                .distinct()
                .forEach(method -> {
                    String authorityCode = null;
                    try {
                        authorityCode = authorityCodePrefix + ((String[]) MethodUtils.invokeMethod(method.getAnnotation(mappingClass), "value"))[0];
                        authorityCode = RegExUtils.replaceAll(authorityCode, StringUtils.HYPHEN, StringUtils.UNDERCROSS);
                        authorityCode = authorityCode.replace("/", "");
                    } catch (final Exception e) {
                        throw new IllegalArgumentException("No value() method on mapping class or return value is empty");
                    }
                    addAuthority(authorities, authorityCode);
                });
        return authorities;

    }

    private void addAuthority(final ArrayList<Authority> authorities, final String authorityCode) {
        final var authority = new Authority(authorityCode);
        final var optional = findOneByCode(authorityCode);
        if (optional.isPresent()) {
            authorities.add(optional.get());
        } else {
            authorities.add(save(authority));
        }
    }

    @Override
    public Optional<Authority> findOneByCode(@NotBlank final String code) {
        return getService().findOneByCode(code);
    }

}
