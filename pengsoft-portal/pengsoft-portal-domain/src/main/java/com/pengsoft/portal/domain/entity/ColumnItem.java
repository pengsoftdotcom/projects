package com.pengsoft.portal.domain.entity;

import com.pengsoft.basedata.domain.entity.OwnedExtTreeEntity;
import com.pengsoft.support.domain.entity.Sortable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Column item
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_column_item")
public class ColumnItem extends OwnedExtTreeEntity<Column> implements Sortable {


    private long sequence;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Column column;

}
