package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.basedata.domain.entity.UserProfile;
import com.pengsoft.support.biz.service.BeanService;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * The service interface of {@link Staff}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface StaffService extends BeanService<Staff, String> {

    /**
     * Set the major job.
     *
     * @param userProfile The {@link UserProfile}.
     * @param job         The major {@link Job}.
     */
    void setMajorJob(@NotNull UserProfile userProfile, @NotNull Job job);

    /**
     * Returns an {@link Optional} of a {@link Staff} with given user profile and job.
     *
     * @param userProfile {@link Staff}'s userProfile
     * @param job         {@link Staff}'s job
     */
    Optional<Staff> findOneByUserProfileAndJob(@NotNull UserProfile userProfile, @NotNull Job job);

    /**
     * Returns an {@link Optional} of a {@link Staff} with given user profile and major true.
     *
     * @param userProfile {@link Staff}'s userProfile
     */
    Optional<Staff> findOneByUserProfileAndMajorTrue(@NotNull UserProfile userProfile);

    /**
     * Returns all {@link Staff}s with given user profile.
     *
     * @param userProfile {@link Staff}'s userProfile
     */
    List<Staff> findAllByUserProfile(@NotNull UserProfile userProfile);

    /**
     * Returns all {@link Staff}s with given jobs
     *
     * @param jobs The {@link Staff}'s job
     */
    List<Staff> findAllByJobIn(@NotEmpty List<Job> jobs);

}
