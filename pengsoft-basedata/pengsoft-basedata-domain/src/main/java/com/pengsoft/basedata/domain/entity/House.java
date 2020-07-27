package com.pengsoft.basedata.domain.entity;

import com.pengsoft.support.domain.entity.Codeable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * House
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_house", indexes = {
        @Index(name = "i_house_floor_id", columnList = "floor_id, code", unique = true),
        @Index(name = "i_house_community_id", columnList = "community_id, building_id, floor_id")
})
public class House extends OwnedExtEntity implements Codeable {

    private static final long serialVersionUID = 3399426252923096057L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @Size(max = 255)
    private String name;

    @Min(0)
    private float grossFloorArea;

    @Min(0)
    private float netFloorArea;

    @Valid
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Person owner;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Floor floor;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Building building;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Community community;

}
