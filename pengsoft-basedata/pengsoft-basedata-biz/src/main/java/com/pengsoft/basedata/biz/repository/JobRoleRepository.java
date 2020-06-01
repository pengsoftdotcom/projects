package com.pengsoft.basedata.biz.repository;

import com.pengsoft.basedata.domain.entity.JobRole;
import com.pengsoft.basedata.domain.entity.QJobRole;
import com.pengsoft.support.biz.repository.BeanRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository interface of {@link JobRole} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface JobRoleRepository extends BeanRepository<QJobRole, JobRole, String> {

}
