package com.pengsoft.device.starter.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Device Console Auto Configure Properties
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties("pengsoft.device")
public class DeviceConsoleAutoConfigureProperties {

    private String host;

    private int port;

}
