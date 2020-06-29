package com.pengsoft.basedata.domain;

import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.security.domain.DefaultUserDetails;
import com.pengsoft.security.domain.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * The implementer of {@link UserDetails} for {@link Person}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class PersonUserDetails extends DefaultUserDetails {

    private static final long serialVersionUID = 6686517470713795533L;

    @Getter
    @Setter
    private Person person;

    public PersonUserDetails(final Person person, final List<Role> roles, final Role majorRole, final List<? extends GrantedAuthority> authorities) {
        super(person.getUser(), roles, majorRole, authorities);
        this.person = person;
    }

}
