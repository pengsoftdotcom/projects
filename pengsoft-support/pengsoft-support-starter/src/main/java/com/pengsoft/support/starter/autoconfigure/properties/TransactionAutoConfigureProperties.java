package com.pengsoft.support.starter.autoconfigure.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Transaction auto configure properties
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@ConfigurationProperties("pengsoft.transaction")
public class TransactionAutoConfigureProperties {

    private List<String> readonly;

    private List<String> required;

    public List<String> getReadonly() {
        return readonly;
    }

    public void setReadonly(final List<String> readonly) {
        this.readonly = readonly;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(final List<String> required) {
        this.required = required;
    }

}
