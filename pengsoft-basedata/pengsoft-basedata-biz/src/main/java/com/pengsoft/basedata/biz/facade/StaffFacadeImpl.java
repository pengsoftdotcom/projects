package com.pengsoft.basedata.biz.facade;

import com.pengsoft.basedata.biz.service.PersonService;
import com.pengsoft.basedata.biz.service.StaffService;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.security.biz.service.UserService;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.support.commons.util.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * The implementer of {@link StaffFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class StaffFacadeImpl extends EntityFacadeImpl<StaffService, Staff, String> implements StaffFacade {

    @Inject
    private PersonService personService;

    @Inject
    private UserService userService;

    @Override
    public Staff save(final Staff staff) {
        final var person = personService.findOneByMobile(staff.getPerson().getMobile()).orElse(staff.getPerson());
        if (StringUtils.isBlank(person.getId())) {
            final var user = userService.findOneByMobile(person.getMobile()).orElse(new User(person.getMobile(), UUID.randomUUID().toString()));
            if (StringUtils.isBlank(user.getId())) {
                userService.save(user);
            }
            person.setUser(user);
        } else {
            BeanUtils.copyProperties(staff.getPerson(), person, "id", "mobile", "user", "version");
        }
        staff.setPerson(personService.save(person));
        return super.save(staff);
    }

    @Override
    public void setPrimaryJob(final Person person, final Job job) {
        getService().setPrimaryJob(person, job);
    }

    @Override
    public Optional<Staff> findOneByPersonAndPrimaryTrue(final Person person) {
        return getService().findOneByPersonAndPrimaryTrue(person);
    }

    @Override
    public List<Staff> findAllByPerson(final Person person) {
        return getService().findAllByPerson(person);
    }

    @Override
    public List<Staff> findAllByJobIn(final List<Job> jobs) {
        return getService().findAllByJobIn(jobs);
    }

}
