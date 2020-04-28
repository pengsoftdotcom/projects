package com.pengsoft.security.biz.repository;

import com.pengsoft.security.domain.entity.QUserRole;
import com.pengsoft.security.domain.entity.UserRole;
import com.pengsoft.support.biz.repository.BeanRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository interface of {@link UserRole} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface UserRoleRepository extends BeanRepository<QUserRole, UserRole, String> {

}
