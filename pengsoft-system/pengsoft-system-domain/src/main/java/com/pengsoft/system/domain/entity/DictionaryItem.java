package com.pengsoft.system.domain.entity;

import com.pengsoft.support.domain.entity.Codeable;
import com.pengsoft.support.domain.entity.Sortable;
import com.pengsoft.support.domain.entity.TreeBean;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Dictionary Item
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_dictionary_item", indexes = {
        @Index(name = "i_dictionary_item_type_id", columnList = "type_id, parent_id, code", unique = true)
})
public class DictionaryItem extends TreeBean<DictionaryItem> implements Codeable, Sortable {

    private static final long serialVersionUID = 2316680631249751994L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    private long sequence;

    @Size(max = 255)
    private String remark;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DictionaryType type;

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

    @Override
    public long getSequence() {
        return sequence;
    }

    @Override
    public void setSequence(final long sequence) {
        this.sequence = sequence;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(final String remark) {
        this.remark = remark;
    }

    public DictionaryType getType() {
        return type;
    }

    public void setType(final DictionaryType type) {
        this.type = type;
    }

}
