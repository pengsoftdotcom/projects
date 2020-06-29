package com.pengsoft.device.biz.socket;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Default TCP/IP response object.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
public class Response {

    private String version = "1.0";

    private String type;

    private int seq;

    private int code;

    private String msg;

    private Map<String, Object> data;

}
