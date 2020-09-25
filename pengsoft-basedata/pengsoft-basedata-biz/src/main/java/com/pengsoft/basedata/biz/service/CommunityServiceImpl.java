package com.pengsoft.basedata.biz.service;

import com.pengsoft.basedata.biz.repository.CommunityRepository;
import com.pengsoft.basedata.domain.entity.Community;
import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
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
public class CommunityServiceImpl extends TreeEntityServiceImpl<CommunityRepository, Community, String> implements CommunityService {

    @Override
    public Community save(final Community community) {
        final var parentId = Optional.ofNullable(community.getParent()).map(Community::getId).orElse(null);
        final var regionId = community.getRegion().getId();
        getRepository().findOneByParentIdAndRegionIdAndName(parentId, regionId, community.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, community)) {
                throw getExceptions().constraintViolated("name", "Exists", community.getName());
            }
        });
        return super.save(community);
    }

}
