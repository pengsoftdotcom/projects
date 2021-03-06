package com.pengsoft.system.domain.entity;

import com.pengsoft.security.domain.entity.OwnedEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import java.io.InputStream;

/**
 * File information uploaded by the user.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_asset", indexes = {
        @Index(name = "i_asset_created_by", columnList = "createdBy, originalName, contentType, contentLength, createdAt, updatedAt"),
        @Index(name = "i_asset_updated_by", columnList = "updatedBy, originalName, contentType, contentLength, createdAt, updatedAt")
})
public class Asset extends OwnedEntity {

    private static final long serialVersionUID = -1180939469919353491L;

    @Size(max = 255)
    private String originalName;

    @Size(max = 255)
    private String presentName;

    @Size(max = 255)
    private String contentType;

    @Size(max = 255)
    private String storagePath;

    @Size(max = 255)
    private String accessPath;

    private long contentLength;

    private boolean locked;

    @Transient
    private transient InputStream inputStream;

}
