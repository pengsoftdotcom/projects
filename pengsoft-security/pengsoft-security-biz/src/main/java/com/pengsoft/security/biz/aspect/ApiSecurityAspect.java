package com.pengsoft.security.biz.aspect;

import com.pengsoft.security.commons.annotation.Authenticated;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.biz.aspect.JoinPoints;
import com.pengsoft.support.commons.util.ClassUtils;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.Beanable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.GrantedAuthority;

import javax.inject.Named;
import java.io.Serializable;
import java.util.Collection;

/**
 * The api security aspect, handling the privileges of external callers.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
@Aspect
public class ApiSecurityAspect {

    @SuppressWarnings("unchecked")
    @Around(JoinPoints.ALL_API)
    public static Object handlerApiSecurity(final ProceedingJoinPoint jp) throws Throwable {
        final var args = jp.getArgs();
        final var apiClass = jp.getTarget().getClass();
        final var entityClass = (Class<? extends Beanable<? extends Serializable>>) ClassUtils.getGenericType(apiClass, 1);
        final var method = ((MethodSignature) jp.getSignature()).getMethod();
        if (apiClass.getAnnotation(Authenticated.class) == null && method.getAnnotation(Authenticated.class) == null && SecurityUtils.getUserDetails() != null) {
            final var packageName = apiClass.getPackageName();
            final var modulePart = SecurityUtils.getModuleCodeFromEntityClass(entityClass);
            final var entityPart = SecurityUtils.getEntityCodeFromEntityClass(entityClass);
            final var methodPart = StringUtils.camelCaseToSnakeCase(jp.getSignature().getName(), false);
            final var requiredAuthority = StringUtils.join(new String[]{modulePart, entityPart, methodPart}, StringUtils.GLOBAL_SEPARATOR);
            final var grantedAuthorities = SecurityUtils.get("authorities", Collection.class);
            if (grantedAuthorities.stream()
                    .noneMatch(grantedAuthority -> StringUtils.equals(requiredAuthority, ((GrantedAuthority) grantedAuthority).getAuthority()))) {
                throw new AccessDeniedException(requiredAuthority);
            }
        }
        return jp.proceed(args);
    }

    public static void main(final String[] args) {
        System.out.println(SecurityUtils.getModuleCodeFromEntityClass(Role.class));
    }

}
