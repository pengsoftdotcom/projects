package com.pengsoft.basedata.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.basedata.domain.json.OrganizationCollectionJsonSerializer;
import com.pengsoft.basedata.domain.json.OrganizationJsonSerializer;
import com.pengsoft.security.domain.DefaultUserDetails;
import com.pengsoft.security.domain.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * The implementer of {@link UserDetails} for {@link Person}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Getter
@Setter
public class PersonUserDetails extends DefaultUserDetails {

    private static final long serialVersionUID = 6686517470713795533L;

    private Person person;

    @JsonSerialize(using = OrganizationCollectionJsonSerializer.class)
    private Collection<Organization> organizations;

    @JsonSerialize(using = OrganizationJsonSerializer.class)
    private Organization organization;

    public PersonUserDetails(final Person person, final Collection<Organization> organizations, final Collection<Role> roles, final Role primaryRole, final Collection<? extends GrantedAuthority> authorities) {
        super(person.getUser(), roles, primaryRole, authorities);
        this.person = person;
        this.organizations = organizations;
        if (primaryRole != null && primaryRole.getCode().equals("organization_admin") && CollectionUtils.isNotEmpty(organizations)) {
            this.organization = organizations.iterator().next();
        }
    }

}
