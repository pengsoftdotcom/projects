package com.pengsoft.basedata.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pengsoft.system.domain.entity.Region;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Community
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_community", indexes = {
        @Index(name = "i_community_region_id", columnList = "region_id, name", unique = true)
})
public class Community extends OwnedExtEntity {

    private static final long serialVersionUID = 7110857036766882988L;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Region region;

    @NotBlank
    @Size(max = 255)
    private String address;

    @JsonIgnore
    @OneToMany(mappedBy = "community", cascade = CascadeType.REMOVE)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Building> buildings = new ArrayList<>();

}
