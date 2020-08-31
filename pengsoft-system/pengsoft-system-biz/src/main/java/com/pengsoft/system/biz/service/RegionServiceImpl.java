package com.pengsoft.system.biz.service;

import com.pengsoft.support.biz.service.TreeEntityServiceImpl;
import com.pengsoft.support.domain.util.EntityUtils;
import com.pengsoft.system.biz.repository.RegionRepository;
import com.pengsoft.system.domain.entity.Region;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The implementer of {@link RegionService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class RegionServiceImpl extends TreeEntityServiceImpl<RegionRepository, Region, String> implements RegionService {

    @Override
    public Region save(final Region region) {
        findOneByCode(region.getCode()).ifPresent(source -> {
            if (EntityUtils.ne(source, region)) {
                throw getExceptions().constraintViolated("code", "Exists", region.getCode());
            }
        });
        return super.save(region);
    }

    @Override
    public Optional<Region> findOneByCode(final String code) {
        return getRepository().findOneByCode(code);
    }

    @Override
    public List<Region> findAllIndexedCities() {
        return getRepository().findAllByIndexIsNotNullOrderByIndexAscCodeAsc();
    }

}
