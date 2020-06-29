package com.pengsoft.basedata.domain.entity;

import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.commons.validation.Chinese;
import com.pengsoft.support.commons.validation.Mobile;
import com.pengsoft.system.domain.entity.Asset;
import com.pengsoft.system.domain.entity.DictionaryItem;
import lombok.Getter;
import lombok.Setter;
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
 * Person
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_person", indexes = {
        @Index(name = "i_person_mobile", columnList = "mobile", unique = true),
        @Index(name = "i_person_name", columnList = "name")
})
public class Person extends OwnedBeanExt {

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

}
