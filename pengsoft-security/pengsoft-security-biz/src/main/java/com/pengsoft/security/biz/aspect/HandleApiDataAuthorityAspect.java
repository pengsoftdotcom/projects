package com.pengsoft.security.biz.aspect;

import com.pengsoft.security.domain.entity.Owned;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.biz.aspect.JoinPoints;
import com.pengsoft.support.biz.util.QueryDslUtils;
import com.pengsoft.support.commons.util.ClassUtils;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.StringPath;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import javax.inject.Named;
import java.io.Serializable;

/**
 * Handle API data authorities from outside callers.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
@Aspect
public class HandleApiDataAuthoritiesAspect {

    @SuppressWarnings("unchecked")
    @Around(JoinPoints.ALL_API)
    public <T extends Beanable<ID>, ID extends Serializable> Object handle(final ProceedingJoinPoint jp) throws Throwable {
        final var args = jp.getArgs();
        final var apiClass = jp.getTarget().getClass();
        final var entityClass = (Class<T>) ClassUtils.getGenericType(apiClass, 1);
        final var moduleCode = SecurityUtils.getModuleCodeFromEntityClass(entityClass);
        final var entityCode = SecurityUtils.getEntityCodeFromEntityClass(entityClass);
        final var moduleAdmin = StringUtils.join(new String[] { moduleCode, Role.ADMIN }, StringUtils.UNDERLINE);
        final var entityAdmin = StringUtils.join(new String[] { moduleCode, entityCode, Role.ADMIN }, StringUtils.UNDERLINE);
        if (Owned.class.isAssignableFrom(entityClass) && !SecurityUtils.hasAnyRole(Role.ADMIN, moduleAdmin, entityAdmin)) {
            final var length = args.length;
            for (int i = 0; i < length; i++) {
                if (args[i] instanceof Predicate) {
                    final var createdByPath = (StringPath) QueryDslUtils.getPath(entityClass, "createdBy");
                    args[i] = createdByPath.eq(SecurityUtils.getUserId()).and((Predicate) args[i]);
                }
            }
        }
        return jp.proceed(args);
    }

}
