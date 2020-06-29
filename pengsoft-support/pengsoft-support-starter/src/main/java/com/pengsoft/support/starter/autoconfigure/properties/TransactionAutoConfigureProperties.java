package com.pengsoft.support.starter.autoconfigure.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Transaction auto configure properties
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@ConfigurationProperties("pengsoft.transaction")
public class TransactionAutoConfigureProperties {

    private List<String> readonly;

    private List<String> required = List.of("*");

}
