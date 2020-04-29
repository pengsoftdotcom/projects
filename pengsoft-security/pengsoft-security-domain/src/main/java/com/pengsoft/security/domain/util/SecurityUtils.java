package com.pengsoft.security.domain.util;

import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.Beanable;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

import static com.pengsoft.support.commons.util.StringUtils.ESCAPES;
import static com.pengsoft.support.commons.util.StringUtils.PACKAGE_SEPARATOR;
import static com.pengsoft.support.commons.util.StringUtils.UNDERCROSS;

/**
 * Security utility methods.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class SecurityUtils {

    public static final String ADMIN = "admin";
    private static final SpelExpressionParser parser = new SpelExpressionParser();

    private SecurityUtils() {
    }

    /**
     * Returns {@link UserDetails}
     */
    private static UserDetails getPrincipal() {
        final SecurityContext context = SecurityContextHolder.getContext();
        if (context != null) {
            final Authentication authentication = context.getAuthentication();
            if (authentication != null) {
                final Object principal = authentication.getPrincipal();
                if (principal instanceof UserDetails) {
                    return (UserDetails) principal;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * Returns the property of {@link UserDetails}
     *
     * @param expressionString The raw expression string to parse.
     * @param clazz            The class the caller would like the result to be
     */
    private static <T> T get(final String expressionString, final Class<T> clazz) {
        final var userDetails = getPrincipal();
        if (userDetails == null) {
            return null;
        } else {
            final var expression = parser.parseExpression(expressionString);
            return expression.getValue(userDetails, clazz);
        }
    }

    /**
     * Returns the current user.
     */
    public static User getCurrentUser() {
        return get("currentUser", User.class);
    }

    /**
     * Returns the current user id.
     */
    public static String getCurrentUserId() {
        return getPropertyId("currentUser");
    }

    /**
     * Return department id of the current user.
     */
    public static String getCurrentControlById() {
        return getPropertyId("controlBy");
    }

    /**
     * Return organization id of the current user.
     */
    public static String getCurrentBelongsToId() {
        return getPropertyId("belongsTo");
    }

    private static String getPropertyId(final String property) {
        if (isPropertyExists(property)) {
            return SecurityUtils.get(property + "?.id", String.class);
        } else {
            return null;
        }
    }

    private static boolean isPropertyExists(final String property) {
        final var userDetails = getPrincipal();
        if (userDetails == null) {
            return false;
        } else {
            return FieldUtils.getField(userDetails.getClass(), property, true) != null;
        }
    }

    /**
     * Returns the module admin role code.
     *
     * @param entityClass The entity class.
     * @return The module admin role code.
     */
    public static String getModuleAdminCode(final Class<? extends Beanable<? extends Serializable>> entityClass) {
        final var moduleCode = getModuleCodeFromEntityClass(entityClass).replaceAll(ESCAPES + PACKAGE_SEPARATOR, UNDERCROSS);
        return StringUtils.join(new String[]{moduleCode, ADMIN}, StringUtils.UNDERCROSS);
    }

    /**
     * Returns the entity admin role code.
     *
     * @param entityClass The entity class.
     * @return The entity admin role code
     */
    public static String getEntityAdminCode(final Class<? extends Beanable<? extends Serializable>> entityClass) {
        final var moduleCode = getModuleCodeFromEntityClass(entityClass);
        final var entityCode = getEntityCodeFromEntityClass(entityClass);
        return StringUtils.join(new String[]{moduleCode, entityCode, ADMIN}, UNDERCROSS);
    }

    /**
     * Returns the entity class.
     *
     * @param entityClass The entity class.
     * @return The entity code.
     */
    private static String getModuleCodeFromEntityClass(final Class<? extends Beanable<? extends Serializable>> entityClass) {
        return getModuleCodeFromPackageName(entityClass.getPackageName(), "domain.entity").replaceAll(ESCAPES + PACKAGE_SEPARATOR, UNDERCROSS);
    }

    /**
     * Returns the module code with the given package name and sub-package name.
     *
     * @param packageName    The package name.
     * @param subPackageName The sub-package name.
     * @return The module code.
     */
    private static String getModuleCodeFromPackageName(final String packageName, final String subPackageName) {
        return StringUtils.substringAfter(StringUtils.substringBetween(packageName, "com" + PACKAGE_SEPARATOR, PACKAGE_SEPARATOR + subPackageName), PACKAGE_SEPARATOR);
    }

    /**
     * Returns the entity code.
     *
     * @param entityClass The entity class.
     * @return The entity code.
     */
    private static String getEntityCodeFromEntityClass(final Class<? extends Beanable<? extends Serializable>> entityClass) {
        return StringUtils.camelCaseToSnakeCase(entityClass.getSimpleName(), false);
    }

    /**
     * Returns the entity admin authority code prefix by given entity class.
     *
     * @param entityClass The entity class.
     * @return The entity admin authority code prefix.
     */
    public static String getEntityAdminAuthorityCodePrefixFromEntityClass(final Class<? extends Beanable<? extends Serializable>> entityClass) {
        final var moduleCode = getModuleCodeFromEntityClass(entityClass);
        final var entityCode = getEntityCodeFromEntityClass(entityClass);
        return StringUtils.join(new String[]{moduleCode, entityCode}, StringUtils.GLOBAL_SEPARATOR);
    }

}
