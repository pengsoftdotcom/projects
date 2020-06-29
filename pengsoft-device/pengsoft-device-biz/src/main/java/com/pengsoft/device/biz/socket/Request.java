package com.pengsoft.device.biz.socket;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * Default TCP/IP request object.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
public class Request {

    private String version = "1.0";

    private String type;

    private int seq;

    private String sn;

    private Map<String, Object> data;

}
