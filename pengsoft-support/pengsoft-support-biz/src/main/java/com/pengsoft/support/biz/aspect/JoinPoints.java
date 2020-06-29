package com.pengsoft.support.biz.aspect;

/**
 * The constants of join points.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class JoinPoints {

    public static final String ALL_SERVICE = "execution(public * com..biz.service.*Service.*(..))";

    public static final String ALL_FACADE = "execution(public * com..biz.facade.*Facade.*(..))";

    public static final String ALL_API = "execution(public * com..biz.api.*Api.*(..))";

    private JoinPoints() {
    }

}
