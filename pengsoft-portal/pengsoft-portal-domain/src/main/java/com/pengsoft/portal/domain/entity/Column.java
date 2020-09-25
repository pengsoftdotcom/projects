package com.pengsoft.portal.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pengsoft.basedata.domain.entity.OwnedExtTreeEntity;
import com.pengsoft.support.domain.entity.Sortable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Column
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_column")
public class Column extends OwnedExtTreeEntity<Column> implements Sortable {

    @NotBlank
    @Size(max = 255)
    private String title;

    private long sequence;

    @JsonIgnore
    @OneToMany(mappedBy = "column", cascade = CascadeType.REMOVE)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<ColumnItem> items = new ArrayList<>();

}
