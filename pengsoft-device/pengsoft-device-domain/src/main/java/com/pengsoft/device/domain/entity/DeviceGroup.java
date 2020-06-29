package com.pengsoft.device.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pengsoft.basedata.domain.entity.OwnedExtTreeEntity;
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
 * Device group
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_device_group", indexes = {
        @Index(name = "i_device_group_parent_id", columnList = "parent_id, name", unique = true)
})
public class DeviceGroup extends OwnedExtTreeEntity<DeviceGroup> {

    private static final long serialVersionUID = -1039853621712384938L;

    @NotBlank
    @Size(max = 255)
    private String name;

    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(mappedBy = "group", cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Device> devices = new ArrayList<>();

}
