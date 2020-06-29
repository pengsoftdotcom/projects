package com.pengsoft.device.biz.socket;

/**
 * Default TCP/IP response handler.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ResponseHandler {

    String getType();

    default boolean support(final Response response) {
        return getType().equals(response.getType());
    }

    void handle(Response response);

}
