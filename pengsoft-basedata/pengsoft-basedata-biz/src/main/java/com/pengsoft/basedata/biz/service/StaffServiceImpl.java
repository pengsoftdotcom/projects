package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.StaffRepository;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Person;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.commons.exception.BusinessException;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The implementer of {@link StaffService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class StaffServiceImpl extends EntityServiceImpl<StaffRepository, Staff, String> implements StaffService {

    @Override
    public Staff save(final Staff staff) {
        findOneByPersonAndJob(staff.getPerson(), staff.getJob()).ifPresent(source -> {
            if (EntityUtils.ne(source, staff)) {
                throw new BusinessException("staff.save.unique", staff.getPerson().getName(), staff.getJob().getName());
            }
        });
        final var department = staff.getJob().getDepartment();
        staff.setDepartment(department);
        final var organization = department.getOrganization();
        staff.setOrganization(organization);
        super.save(staff);
        if (staff.isPrimary()) {
            setPrimaryJob(staff.getPerson(), staff.getJob());
        }
        return staff;
    }

    @Override
    public void setPrimaryJob(final Person person, final Job job) {
        findAllByPerson(person).forEach(staff -> {
            if (EntityUtils.eq(staff.getJob(), job)) {
                staff.setPrimary(true);
                super.save(staff);
            } else {
                if (staff.isPrimary()) {
                    staff.setPrimary(false);
                    super.save(staff);
                }
            }
        });
    }

    @Override
    public Optional<Staff> findOneByPersonAndJob(final Person person, final Job job) {
        return getRepository().findOneByPersonAndJob(person, job);
    }

    @Override
    public Optional<Staff> findOneByPersonAndPrimaryTrue(final Person person) {
        return getRepository().findOneByPersonAndPrimaryTrue(person);
    }

    @Override
    public List<Staff> findAllByPerson(final Person person) {
        return getRepository().findAllByPerson(person);
    }

    @Override
    public List<Staff> findAllByJobIn(final List<Job> jobs) {
        return getRepository().findAllByJobIn(jobs);
    }

}
