package com.pengsoft.support.starter.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * The CORS auto configure properties.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
public class Cors {

    private boolean allowCredentials = false;

    private String allowedOrigin = "*";

    private String allowedHeader = "*";

    private String allowedMethod = "*";

}
