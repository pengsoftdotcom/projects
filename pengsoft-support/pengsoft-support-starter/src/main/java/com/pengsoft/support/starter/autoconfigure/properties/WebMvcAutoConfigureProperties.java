package com.pengsoft.support.starter.autoconfigure.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Web mvc auto configure properties
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@ConfigurationProperties("pengsoft.web")
public class WebMvcAutoConfigureProperties {

    private Cors cors = new Cors();

    public Cors getCors() {
        return cors;
    }

    public void setCors(final Cors cors) {
        this.cors = cors;
    }

}
