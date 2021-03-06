package com.pengsoft.security.domain.entity;

import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.EntityImpl;
import com.pengsoft.support.domain.entity.Codeable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Authority
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_authority", indexes = {
        @Index(name = "i_authority_code", columnList = "code", unique = true),
        @Index(name = "i_authority_name", columnList = "name", unique = true)
})
public class Authority extends EntityImpl implements Codeable {

    private static final long serialVersionUID = -4116533377602985015L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String remark;

    @OneToMany(mappedBy = "authority", cascade = CascadeType.REMOVE)
    private List<RoleAuthority> roleAuthorities = new ArrayList<>();

    public Authority(@NotBlank @Size(max = 255) final String code) {
        this.code = code;
        this.name = StringUtils.replace(code, StringUtils.GLOBAL_SEPARATOR, StringUtils.SPACE);
    }

}
