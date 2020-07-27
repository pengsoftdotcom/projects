package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.CommunityRepository;
import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import com.pengsoft.system.domain.entity.Region;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link CommunityService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class CommunityServiceImpl extends EntityServiceImpl<CommunityRepository, Community, String> implements CommunityService {

    @Override
    public Community save(final Community community) {
        findOneByRegionAndName(community.getRegion(), community.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, community)) {
                throw getExceptions().constraintViolated("name", "Exists", community.getName());
            }
        });
        return super.save(community);
    }

    @Override
    public Optional<Community> findOneByRegionAndName(final Region region, final String name) {
        return getRepository().findOneByRegionAndName(region, name);
    }

}
