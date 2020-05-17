package com.pengsoft.security.oauth.domain.entity;

import com.pengsoft.support.domain.entity.Bean;
import com.pengsoft.support.domain.entity.Codeable;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Client
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_client", indexes = {
        @Index(name = "i_client_code", columnList = "code", unique = true),
        @Index(name = "i_client_name", columnList = "name", unique = true)
})
public class Client extends Bean implements Codeable {

    private static final long serialVersionUID = -319215300315862407L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String secret;

    @NotBlank
    @Size(max = 255)
    private String grantTypes = "password";

    @Min(0)
    int validitySeconds;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(final String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(final String secret) {
        this.secret = secret;
    }

    public String getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(final String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public int getValiditySeconds() {
        return validitySeconds;
    }

    public void setValiditySeconds(final int validitySeconds) {
        this.validitySeconds = validitySeconds;
    }
}
