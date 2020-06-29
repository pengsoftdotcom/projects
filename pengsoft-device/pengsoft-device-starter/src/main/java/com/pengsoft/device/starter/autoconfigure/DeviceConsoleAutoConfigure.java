package com.pengsoft.device.starter.autoconfigure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.pengsoft.device.starter.autoconfigure.properties.DeviceConsoleAutoConfigureProperties;

/**
 * Device console auto configure
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Configuration
@ComponentScan("com.*.device.biz.socket")
@EnableConfigurationProperties(DeviceConsoleAutoConfigureProperties.class)
public class DeviceConsoleAutoConfigure {

}
