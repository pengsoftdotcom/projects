package com.pengsoft.support.biz.aspect;

/**
 * The constants of join points.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class JoinPoints {

    private JoinPoints() {
    }

    public static final String ALL_SERVICE = "execution(public * com..biz.service.impl.*ServiceImpl.*(..))";

    public static final String ALL_FACADE = "execution(public * com..biz.facade.impl.*FacadeImpl.*(..))";

    public static final String ALL_API = "execution(public * com..biz.api.*Api.*(..))";

}
