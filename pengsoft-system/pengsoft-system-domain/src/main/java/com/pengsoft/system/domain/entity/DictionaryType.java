package com.pengsoft.system.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pengsoft.support.domain.entity.EntityImpl;
import com.pengsoft.support.domain.entity.Codeable;
import lombok.Getter;
import lombok.Setter;
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
 * Dictionary Type
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_dictionary_type", indexes = {
        @Index(name = "i_dictionary_type_code", columnList = "code", unique = true)
})
public class DictionaryType extends EntityImpl implements Codeable {

    private static final long serialVersionUID = 7355743632684719076L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String remark;

    @JsonIgnore
    @OneToMany(mappedBy = "type", cascade = CascadeType.REMOVE)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<DictionaryItem> items = new ArrayList<>();

}
