package com.pengsoft.basedata.domain.entity;

import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.commons.validation.Chinese;
import com.pengsoft.support.commons.validation.Mobile;
import com.pengsoft.support.domain.entity.Bean;
import com.pengsoft.system.domain.entity.Asset;
import com.pengsoft.system.domain.entity.DictionaryItem;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * User profile
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_user_profile", indexes = {
        @Index(name = "i_user_profile_mobile", columnList = "mobile", unique = true),
        @Index(name = "i_user_profile_name", columnList = "name")
})
public class UserProfile extends Bean {

    private static final long serialVersionUID = -2643213888713403689L;

    @NotBlank
    @Size(min = 2, max = 20)
    @Chinese
    private String name;

    @Size(max = 255)
    private String nickname;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DictionaryItem gender;

    @OneToOne(cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private Asset avatar;

    @NotBlank
    @Mobile
    @Column(updatable = false)
    private String mobile;

    @OneToOne(cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private User user;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }

    public DictionaryItem getGender() {
        return gender;
    }

    public void setGender(final DictionaryItem gender) {
        this.gender = gender;
    }

    public Asset getAvatar() {
        return avatar;
    }

    public void setAvatar(final Asset avatar) {
        this.avatar = avatar;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}
