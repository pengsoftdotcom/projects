package com.pengsoft.device.biz.socket;

import java.util.Map;

/**
 * Default TCP/IP request handler.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface RequestHandler {

    String getType();

    default boolean support(final Request request) {
        return getType().equals(request.getType());
    }

    Map<String, Object> handle(Request request);

}
