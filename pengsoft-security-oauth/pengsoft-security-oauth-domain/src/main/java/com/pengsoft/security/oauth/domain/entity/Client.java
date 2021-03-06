package com.pengsoft.security.oauth.domain.entity;

import com.pengsoft.support.domain.entity.EntityImpl;
import com.pengsoft.support.domain.entity.Codeable;
import lombok.Getter;
import lombok.Setter;
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
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_client", indexes = {
        @Index(name = "i_client_code", columnList = "code", unique = true),
        @Index(name = "i_client_name", columnList = "name", unique = true)
})
public class Client extends EntityImpl implements Codeable {

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

}
