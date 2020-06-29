package com.pengsoft.device.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pengsoft.basedata.domain.entity.OwnedExtEntity;
import com.pengsoft.support.domain.entity.Codeable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Device
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_device", indexes = {
        @Index(name = "i_device_code", columnList = "code", unique = true),
        @Index(name = "i_device_name", columnList = "name")
})
public class Device extends OwnedExtEntity implements Codeable {

    private static final long serialVersionUID = -1306555947695485569L;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String host;

    @Min(0)
    private int port;

    private boolean activated;

    private LocalDateTime activatedAt;

    private boolean online;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private DeviceGroup group;

    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Product product;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private PurchaseBatchItem purchaseBatchItem;

    @Type(type = "com.pengsoft.support.domain.type.MapType")
    private Map<String, Object> info = new HashMap<>();

    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(mappedBy = "device", cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<DeviceConfig> configs = new ArrayList<>();

    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(mappedBy = "device", cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<DeviceConnectionLog> logs = new ArrayList<>();

}
