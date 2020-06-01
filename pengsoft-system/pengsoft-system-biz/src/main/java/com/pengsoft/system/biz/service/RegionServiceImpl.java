package com.pengsoft.system.biz.service;

import java.util.Optional;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.pengsoft.support.biz.service.TreeBeanServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import com.pengsoft.system.biz.repository.RegionRepository;
import com.pengsoft.system.domain.entity.Region;

/**
 * The implementer of {@link RegionService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class RegionServiceImpl extends TreeBeanServiceImpl<RegionRepository, Region, String> implements RegionService {

    @Override
    public Region save(final Region region) {
        findOneByCode(region.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, region)) {
                throw getExceptions().constraintViolated("code", region.getCode());
            }
        });
        return super.save(region);
    }

    @Override
    public Optional<Region> findOneByCode(final String code) {
        return getRepository().findOneByCode(code);
    }

}
