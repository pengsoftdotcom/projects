package com.pengsoft.security.biz.repository;

import com.pengsoft.security.domain.entity.QRoleAuthority;
import com.pengsoft.security.domain.entity.RoleAuthority;
import com.pengsoft.support.biz.repository.EntityRepository;
import org.springframework.stereotype.Repository;

/**
 * The repository interface of {@link RoleAuthority} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface RoleAuthorityRepository extends EntityRepository<QRoleAuthority, RoleAuthority, String> {

}
