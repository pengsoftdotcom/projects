package com.pengsoft.portal.biz.repository;

import com.pengsoft.basedata.biz.repository.OwnedExtRepository;
import com.pengsoft.portal.domain.entity.Banner;
import com.pengsoft.portal.domain.entity.QBanner;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.repository.TreeEntityRepository;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link Banner} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface BannerRepository extends TreeEntityRepository<QBanner, Banner, String>, OwnedExtRepository {

    /**
     * Returns an {@link Optional} of a {@link Banner} with given parent and name.
     *
     * @param name {@link Banner}'s name
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<User> findOneByParentAndName(Banner parent, @NotBlank String name);

}
