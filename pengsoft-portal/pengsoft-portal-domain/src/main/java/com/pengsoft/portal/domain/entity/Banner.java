package com.pengsoft.portal.domain.entity;

import com.pengsoft.basedata.domain.entity.OwnedExtTreeEntity;
import com.pengsoft.support.domain.entity.Sortable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Banner
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_banner", indexes = {
        @Index(name = "i_organization_parent_id", columnList = "parent_id, name", unique = true)
})
public class Banner extends OwnedExtTreeEntity<Banner> implements Sortable {

    private static final long serialVersionUID = -9196176174359239335L;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String link;

    @Size(max = 255)
    private String style;

    private long sequence;

}
