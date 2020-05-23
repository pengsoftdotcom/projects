package com.pengsoft.security.biz.facade;

import com.pengsoft.security.biz.service.AuthorityService;
import com.pengsoft.security.biz.service.RoleService;
import com.pengsoft.security.commons.annotation.Authenticated;
import com.pengsoft.security.domain.entity.Authority;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import com.pengsoft.support.commons.exception.MissingConfigurationException;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.Beanable;
import com.pengsoft.support.domain.entity.Enable;
import com.pengsoft.support.domain.entity.Sortable;
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
    public void saveEntityAdminAuthorities(final Class<? extends Beanable<? extends Serializable>> entityClass) {
        final var entityAdminCode = SecurityUtils.getEntityAdminCode(entityClass);
        final var entityAdmin = roleService.findOneByCode(entityAdminCode)
                .orElseThrow(() -> new MissingConfigurationException("'" + entityClass.getName() + "' entity admin not found"));

        final Class<?> apiClass;
        try {
            apiClass = Class.forName(RegExUtils.replaceFirst(entityClass.getName(), ".domain.entity.", ".biz.api.") + "Api");
        } catch (final ClassNotFoundException e) {
            throw new IllegalArgumentException("get api class from '" + entityClass.getName() + "' error", e);
        }
        final var authorityCodePrefix = SecurityUtils.getEntityAdminAuthorityCodePrefixFromEntityClass(entityClass) + StringUtils.GLOBAL_SEPARATOR;
        final var authorities = new ArrayList<Authority>();
        authorities.addAll(getAuthoritiesFromApi(apiClass, entityClass, RequestMapping.class, authorityCodePrefix));
        authorities.addAll(getAuthoritiesFromApi(apiClass, entityClass, GetMapping.class, authorityCodePrefix));
        authorities.addAll(getAuthoritiesFromApi(apiClass, entityClass, PostMapping.class, authorityCodePrefix));
        authorities.addAll(getAuthoritiesFromApi(apiClass, entityClass, PutMapping.class, authorityCodePrefix));
        authorities.addAll(getAuthoritiesFromApi(apiClass, entityClass, DeleteMapping.class, authorityCodePrefix));
        roleService.grantAuthorities(entityAdmin, authorities);
    }

    private List<Authority> getAuthoritiesFromApi(final Class<?> apiClass, final Class<? extends Beanable<? extends Serializable>> entityClass, final Class<? extends Annotation> mappingClass, final String authorityCodePrefix) {
        final var authorities = new ArrayList<Authority>();
        MethodUtils.getMethodsListWithAnnotation(apiClass, mappingClass, true, false).stream()
                .filter(method -> method.getAnnotation(Authenticated.class) == null)
                .map(method -> {
                    String authorityCode;
                    try {
                        authorityCode = authorityCodePrefix + ((String[]) MethodUtils.invokeMethod(method.getAnnotation(mappingClass), "value"))[0];
                        authorityCode = RegExUtils.replaceAll(authorityCode, StringUtils.HYPHEN, StringUtils.UNDERLINE);
                        authorityCode = authorityCode.replace("/", "");
                    } catch (final Exception e) {
                        throw new IllegalArgumentException("No value() method on mapping class or return value is empty");
                    }
                    return authorityCode;
                })
                .filter(authorityCode -> {
                    if (StringUtils.endsWith(authorityCode, StringUtils.GLOBAL_SEPARATOR + "sort")
                            && !Sortable.class.isAssignableFrom(entityClass)) {
                        return false;
                    }
                    if ((StringUtils.endsWith(authorityCode, StringUtils.GLOBAL_SEPARATOR + "enable")
                            || StringUtils.endsWith(authorityCode, StringUtils.GLOBAL_SEPARATOR + "disable"))
                            && !Enable.class.isAssignableFrom(entityClass)) {
                        return false;
                    }
                    return true;
                })
                .distinct()
                .forEach(authorityCode -> addAuthority(authorities, authorityCode));
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
