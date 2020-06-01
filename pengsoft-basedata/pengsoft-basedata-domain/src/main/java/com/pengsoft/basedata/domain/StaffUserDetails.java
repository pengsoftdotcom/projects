package com.pengsoft.basedata.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Staff;
import com.pengsoft.basedata.domain.json.JobCollectionJsonSerializer;
import com.pengsoft.basedata.domain.json.JobJsonSerializer;
import com.pengsoft.security.domain.entity.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * The implementer of {@link UserDetails} for {@link Staff}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class StaffUserDetails extends UserProfileUserDetails {

    private static final long serialVersionUID = -4262963739413486380L;

    @Getter
    @Setter
    @JsonSerialize(using = JobCollectionJsonSerializer.class)
    private List<Job> jobs;

    @Getter
    @Setter
    @JsonSerialize(using = JobJsonSerializer.class)
    private Job currentJob;

    @Getter
    @Setter
    @JsonSerialize(using = JobJsonSerializer.class)
    private Job majorJob;

    public StaffUserDetails(final Staff staff, final List<Job> jobs, final List<Role> roles, final List<? extends GrantedAuthority> authorities) {
        super(staff.getUserProfile(), roles, null, authorities);
        this.jobs = jobs;
        this.majorJob = staff.getJob();
        this.currentJob = staff.getJob();
    }

}
