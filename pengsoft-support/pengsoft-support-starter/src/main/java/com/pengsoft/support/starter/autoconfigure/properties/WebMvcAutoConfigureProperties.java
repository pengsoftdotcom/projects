package com.pengsoft.support.starter.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Web mvc auto configure properties
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties("pengsoft.web")
public class WebMvcAutoConfigureProperties {

    private Cors cors = new Cors();

}
