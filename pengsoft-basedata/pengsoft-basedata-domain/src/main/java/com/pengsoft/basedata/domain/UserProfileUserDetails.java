package com.pengsoft.basedata.domain;

import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.security.domain.DefaultUserDetails;
import com.pengsoft.security.domain.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * The implementer of {@link UserDetails} for {@link UserProfile}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class UserProfileUserDetails extends DefaultUserDetails {

    private static final long serialVersionUID = 6686517470713795533L;

    @Getter
    @Setter
    private UserProfile userProfile;

    public UserProfileUserDetails(final UserProfile userProfile, final List<Role> roles, final Role majorRole, final List<? extends GrantedAuthority> authorities) {
        super(userProfile.getUser(), roles, majorRole, authorities);
        this.userProfile = userProfile;
    }

}
