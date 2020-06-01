package com.pengsoft.security.biz.aspect;

import com.pengsoft.security.commons.annotation.Authenticated;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.util.SecurityUtils;
import com.pengsoft.support.biz.aspect.JoinPoints;
import com.pengsoft.support.commons.util.ClassUtils;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.Beanable;
import com.querydsl.core.types.Predicate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import javax.inject.Inject;
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
public class HandleApiDataAuthorityAspect<T extends Beanable<ID>, ID extends Serializable> {

    @Inject
    private DataAuthorityPredicateBuilder builder;

    @SuppressWarnings("unchecked")
    @Around(JoinPoints.ALL_API)
    public Object handle(final ProceedingJoinPoint jp) throws Throwable {
        final var args = jp.getArgs();
        final var apiClass = jp.getTarget().getClass();
        final var method = ((MethodSignature) jp.getSignature()).getMethod();
        if (apiClass.getAnnotation(Authenticated.class) == null && method.getAnnotation(Authenticated.class) == null
                && SecurityUtils.getUserDetails() != null) {
            final var entityClass = (Class<T>) ClassUtils.getGenericType(apiClass, 1);
            final var moduleCode = SecurityUtils.getModuleCodeFromEntityClass(entityClass);
            final var entityCode = SecurityUtils.getEntityCodeFromEntityClass(entityClass);
            final var moduleAdmin = StringUtils.join(new String[] { moduleCode, Role.ADMIN }, StringUtils.UNDERLINE);
            final var entityAdmin = StringUtils.join(new String[] { moduleCode, entityCode, Role.ADMIN }, StringUtils.UNDERLINE);
            if (!SecurityUtils.hasAnyRole(Role.ADMIN, moduleAdmin, entityAdmin)) {
                final var length = args.length;
                for (int i = 0; i < length; i++) {
                    if (args[i] instanceof Predicate) {
                        args[i] = builder.build(entityClass, (Predicate) args[i]);
                    }
                }
            }
        }
        return jp.proceed(args);
    }

}
