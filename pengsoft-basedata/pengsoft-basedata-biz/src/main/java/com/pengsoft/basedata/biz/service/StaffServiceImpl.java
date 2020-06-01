package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.StaffRepository;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.support.biz.service.BeanServiceImpl;
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
public class StaffServiceImpl extends BeanServiceImpl<StaffRepository, Staff, String> implements StaffService {

    @Override
    public Staff save(final Staff staff) {
        findOneByUserProfileAndJob(staff.getUserProfile(), staff.getJob()).ifPresent(source -> {
            if (EntityUtils.ne(source, staff)) {
                throw new BusinessException("staff.save.unique", staff.getUserProfile().getName(), staff.getJob().getName());
            }
        });
        final var department = staff.getJob().getDepartment();
        staff.setDepartment(department);
        final var organization = department.getOrganization();
        staff.setOrganization(organization);
        super.save(staff);
        if (staff.isMajor()) {
            setMajorJob(staff.getUserProfile(), staff.getJob());
        }
        return staff;
    }

    @Override
    public void setMajorJob(final UserProfile userProfile, final Job job) {
        findAllByUserProfile(userProfile).forEach(staff -> {
            if (EntityUtils.eq(staff.getJob(), job)) {
                staff.setMajor(true);
                super.save(staff);
            } else {
                if (staff.isMajor()) {
                    staff.setMajor(false);
                    super.save(staff);
                }
            }
        });
    }

    @Override
    public Optional<Staff> findOneByUserProfileAndJob(final UserProfile userProfile, final Job job) {
        return getRepository().findOneByUserProfileAndJob(userProfile, job);
    }

    @Override
    public Optional<Staff> findOneByUserProfileAndMajorTrue(final UserProfile userProfile) {
        return getRepository().findOneByUserProfileAndMajorTrue(userProfile);
    }

    @Override
    public List<Staff> findAllByUserProfile(final UserProfile userProfile) {
        return getRepository().findAllByUserProfile(userProfile);
    }

    @Override
    public List<Staff> findAllByJobIn(final List<Job> jobs) {
        return getRepository().findAllByJobIn(jobs);
    }

}
