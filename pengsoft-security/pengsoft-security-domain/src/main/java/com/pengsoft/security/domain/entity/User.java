package com.pengsoft.security.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.pengsoft.security.domain.validation.Username;
import com.pengsoft.support.domain.entity.Bean;
import com.pengsoft.support.domain.entity.Enable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Basic user account information.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Entity
@Table(name = "t_user", indexes = {
        @Index(name = "i_user_username", columnList = "username", unique = true),
        @Index(name = "i_user_expired_at", columnList = "expiredAt")
})
public class User extends Bean implements Enable {

    private static final long serialVersionUID = -4947447323105702218L;

    @NonNull
    @Username
    @Column(updatable = false)
    private String username;

    @Size(max = 255)
    private String mobile;

    @Email
    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String mpOpenId;

    @NonNull
    @JsonSerialize(using = NullSerializer.class)
    @Column(updatable = false)
    private String password;

    @Size(max = 255)
    private String locale = Locale.SIMPLIFIED_CHINESE.toString();

    private LocalDateTime signedInAt;

    @Min(0)
    private long signInFailureCount;

    private LocalDateTime expiredAt;

    private boolean enabled = true;

    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<UserRole> userRoles = new ArrayList<>();

}
