package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.HouseService;
import com.pengsoft.basedata.biz.service.PersonService;
import com.pengsoft.basedata.domain.entity.Floor;
import com.pengsoft.basedata.domain.entity.House;
import com.pengsoft.security.biz.service.RoleService;
import com.pengsoft.security.biz.service.UserService;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.security.domain.entity.UserRole;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.support.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * The implementer of {@link HouseFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class HouseFacadeImpl extends EntityFacadeImpl<HouseService, House, String> implements HouseFacade {

    private static final String HOUSE_OWNER = "house_owner";
    @Inject
    private PersonService personService;

    @Inject
    private UserService userService;

    @Inject
    private RoleService roleService;

    @Override
    public House save(final House house) {
        if (house.getOwner() != null) {
            final var person = personService.findOneByMobile(house.getOwner().getMobile()).orElse(house.getOwner());
            if (StringUtils.isBlank(person.getId())) {
                final var user = userService.findOneByMobile(person.getMobile()).orElse(new User(person.getMobile(), UUID.randomUUID().toString()));
                if (StringUtils.isBlank(user.getId())) {
                    userService.save(user);
                }
                person.setUser(user);
            } else {
                BeanUtils.copyProperties(house.getOwner(), person, "id", "mobile", "user", "version");
            }
            house.setOwner(personService.save(person));
            final var roles = person.getUser().getUserRoles().stream().map(UserRole::getRole).collect(Collectors.toList());
            if (roles.stream().noneMatch(role -> HOUSE_OWNER.equals(role.getCode()))) {
                final var role = roleService.findOneByCode(HOUSE_OWNER).orElseThrow(() -> getExceptions().entityNotFound(HOUSE_OWNER));
                roles.add(role);
                userService.grantRoles(person.getUser(), roles);
            }
        }
        return super.save(house);
    }

    @Override
    public Optional<House> findOneByFloorAndCode(final Floor floor, final String code) {
        return getService().findOneByFloorAndCode(floor, code);
    }

}
