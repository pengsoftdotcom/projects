package com.pengsoft.security.biz.aspect;

import com.pengsoft.security.domain.entity.Owned;
import com.pengsoft.support.biz.aspect.JoinPoints;
import com.pengsoft.support.commons.util.ClassUtils;
import com.pengsoft.support.domain.entity.Entity;
import com.querydsl.core.types.Predicate;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.access.AccessDeniedException;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Handle API data authorities from outside callers.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
@Aspect
public class HandleApiDataAuthorityAspect<T extends Entity<ID>, ID extends Serializable> {

    @Inject
    private ApiMethodArgumentsHandler<T, ID> handler;

    @SuppressWarnings("unchecked")
    @Around(JoinPoints.ALL_API)
    public Object handle(final ProceedingJoinPoint jp) throws Throwable {
        final var args = jp.getArgs();
        final var apiClass = jp.getTarget().getClass();
        final var entityClass = (Class<T>) ClassUtils.getSuperclassGenericType(apiClass, 1);
        if (Owned.class.isAssignableFrom(entityClass)) {
            final var length = args.length;
            for (int i = 0; i < length; i++) {
                if (args[i] instanceof Predicate) {
                    args[i] = handler.replace(entityClass, (Predicate) args[i]);
                } else if (args[i] instanceof Owned && !handler.check((Owned) args[i])
                        || args[i] instanceof List && ((List<?>) args[i]).get(0) instanceof Owned && !handler.check((List<Owned>) args[i])
                        || args[i] instanceof Map && !handler.check(entityClass, ((Map<ID, Object>) args[i]).keySet())) {
                    throw new AccessDeniedException("Not allowed");
                }
            }
        }
        return jp.proceed(args);
    }

}
