package com.pengsoft.system.domain.entity;

import com.pengsoft.security.domain.entity.OwnableBean;
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
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_asset", indexes = {
        @Index(name = "i_asset_created_by", columnList = "createdBy, originalName, contentType, contentLength, createdAt, updatedAt"),
        @Index(name = "i_asset_updated_by", columnList = "updatedBy, originalName, contentType, contentLength, createdAt, updatedAt")
})
public class Asset extends OwnableBean {

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

    public String getOriginalName() {
        return originalName;
    }

    public void setOriginalName(final String originalName) {
        this.originalName = originalName;
    }

    public String getPresentName() {
        return presentName;
    }

    public void setPresentName(final String presentName) {
        this.presentName = presentName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public String getStoragePath() {
        return storagePath;
    }

    public void setStoragePath(final String storagePath) {
        this.storagePath = storagePath;
    }

    public String getAccessPath() {
        return accessPath;
    }

    public void setAccessPath(final String accessPath) {
        this.accessPath = accessPath;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(final long contentLength) {
        this.contentLength = contentLength;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(final boolean locked) {
        this.locked = locked;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(final InputStream inputStream) {
        this.inputStream = inputStream;
    }

}
