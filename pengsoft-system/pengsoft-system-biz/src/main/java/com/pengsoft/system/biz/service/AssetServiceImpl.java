package com.pengsoft.system.biz.service;

import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.system.biz.repository.AssetRepository;
import com.pengsoft.system.domain.entity.Asset;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link AssetService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class AssetServiceImpl extends EntityServiceImpl<AssetRepository, Asset, String> implements AssetService {

}
