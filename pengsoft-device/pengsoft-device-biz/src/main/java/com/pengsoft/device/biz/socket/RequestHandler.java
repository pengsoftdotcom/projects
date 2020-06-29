package com.pengsoft.device.biz.socket;

import java.util.Map;

/**
 * Device request handler.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ClientRequestHandler {

    String getType();

    default boolean support(final Request request) {
        return getType().equals(request.getType());
    }

    Map<String, Object> handle(Request request);

}
