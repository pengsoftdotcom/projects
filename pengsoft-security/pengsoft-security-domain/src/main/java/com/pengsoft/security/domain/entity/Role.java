package com.pengsoft.security.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pengsoft.support.commons.util.StringUtils;
import com.pengsoft.support.domain.entity.Codeable;
import com.pengsoft.support.domain.entity.TreeBean;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
 * Role
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_role", indexes = {
        @Index(name = "i_role_code", columnList = "code", unique = true),
        @Index(name = "i_role_name", columnList = "name", unique = true)
})
public class Role extends TreeBean<Role> implements Codeable {

    /**
     * Role code: admin
     */
    public static final String ADMIN = "admin";

    /**
     * Role code: security admin
     */
    public static final String SECURITY_ADMIN = "security_admin";

    /**
     * Role code: security user admin
     */
    public static final String SECURITY_USER_ADMIN = "security_user_admin";

    /**
     * Role code: security role admin
     */
    public static final String SECURITY_ROLE_ADMIN = "security_role_admin";

    /**
     * Role code: security authority admin
     */
    public static final String SECURITY_AUTHORITY_ADMIN = "security_authority_admin";

    private static final long serialVersionUID = 8899680261707632937L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String remark;

    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(mappedBy = "role", cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<RoleAuthority> roleAuthorities = new ArrayList<>();

    public Role() {

    }

    public Role(@NotBlank @Size(max = 255) final String code) {
        this.code = code;
        this.name = StringUtils.replace(code, StringUtils.UNDERLINE, StringUtils.SPACE);
    }

    public Role(@NotBlank @Size(max = 255) final String code, @NotBlank @Size(max = 255) final String name) {
        this.code = code;
        this.name = name;
    }

    public Role(final Role parent, @NotBlank @Size(max = 255) final String code) {
        setParent(parent);
        this.code = code;
        this.name = StringUtils.replace(code, StringUtils.UNDERLINE, StringUtils.SPACE);
    }

    public Role(final Role parent, @NotBlank @Size(max = 255) final String code, @NotBlank @Size(max = 255) final String name) {
        setParent(parent);
        this.code = code;
        this.name = name;
    }


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

    public String getRemark() {
        return remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public List<RoleAuthority> getRoleAuthorities() {
        return roleAuthorities;
    }

    public void setRoleAuthorities(final List<RoleAuthority> roleAuthorities) {
        this.roleAuthorities = roleAuthorities;
    }

}
