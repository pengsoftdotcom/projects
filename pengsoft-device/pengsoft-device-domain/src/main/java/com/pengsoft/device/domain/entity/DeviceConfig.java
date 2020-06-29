package com.pengsoft.device.domain.entity;

import com.pengsoft.support.domain.entity.EntityImpl;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Device config
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_device_config", indexes = {
        @Index(name = "i_device_config_device_id_1", columnList = "device_id, code", unique = true),
        @Index(name = "i_device_config_device_id_2", columnList = "device_id, name", unique = true)
})
public class DeviceConfig extends EntityImpl implements Codeable {

    private static final long serialVersionUID = 4238800919788368580L;

    @NotNull
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private Device device;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotBlank
    @Size(max = 255)
    private String type;

    @Size(max = 255)
    private String value;

    @Size(max = 255)
    private String remark;

}
