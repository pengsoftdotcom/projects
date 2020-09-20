package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.PersonService;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.security.biz.service.RoleService;
import com.pengsoft.security.biz.service.UserService;
import com.pengsoft.security.domain.entity.Role;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.support.commons.exception.MissingConfigurationException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The implementer of {@link PersonFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class PersonFacadeImpl extends EntityFacadeImpl<PersonService, Person, String> implements PersonFacade {

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Override
    public Person save(final Person person) {
        final var user = userService.save(new User(person.getMobile(), UUID.randomUUID().toString()));
        final var roles = roleService.findOneByCode(Role.USER).map(List::of).orElseThrow(() -> new MissingConfigurationException("No role user configured."));
        userService.grantRoles(user, roles);
        person.setUser(user);
        return super.save(person);
    }

    @Override
    public Optional<Person> findOneByMobile(final String mobile) {
        return getService().findOneByMobile(mobile);
    }

    @Override
    public Optional<Person> findOneByUser(final User user) {
        return getService().findOneByUser(user);
    }

}
