package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.OrganizationService;
import com.pengsoft.basedata.biz.service.PersonService;
import com.pengsoft.basedata.domain.entity.Organization;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.security.biz.service.RoleService;
import com.pengsoft.security.biz.service.UserService;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.domain.entity.UserRole;
import com.pengsoft.support.biz.facade.TreeEntityFacadeImpl;
import com.pengsoft.support.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The implementer of {@link OrganizationFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class OrganizationFacadeImpl extends TreeEntityFacadeImpl<OrganizationService, Organization, String> implements OrganizationFacade {

    public static final String ORGANIZATION_ADMIN = "organization_admin";

    @Inject
    private PersonService personService;

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Override
    public Organization save(final Organization organization) {
        if (organization.getAdmin() != null) {
            final var person = personService.findOneByMobile(organization.getAdmin().getMobile()).orElse(organization.getAdmin());
            if (StringUtils.isBlank(person.getId())) {
                final var user = userService.findOneByMobile(person.getMobile()).orElse(new User(person.getMobile(), UUID.randomUUID().toString()));
                if (StringUtils.isBlank(user.getId())) {
                    userService.save(user);
                }
                person.setUser(user);
            } else {
                BeanUtils.copyProperties(organization.getAdmin(), person, "id", "mobile", "user", "version");
            }
            organization.setAdmin(personService.save(person));
            final var roles = person.getUser().getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toList());
            if (roles.stream().noneMatch(role -> ORGANIZATION_ADMIN.equals(role.getCode()))) {
                final var role = roleService.findOneByCode(ORGANIZATION_ADMIN).orElseThrow(() -> getExceptions().entityNotFound(ORGANIZATION_ADMIN));
                roles.add(role);
                userService.grantRoles(person.getUser(), roles);
            }
        }
        return super.save(organization);
    }

    @Override
    public Optional<Organization> findOneByCode(final String code) {
        return getService().findOneByCode(code);
    }

    @Override
    public Optional<Organization> findOneByName(final String name) {
        return getService().findOneByName(name);
    }

    @Override
    public List<Organization> findAllByAdmin(final Person admin) {
        return getService().findAllByAdmin(admin);
    }

}
