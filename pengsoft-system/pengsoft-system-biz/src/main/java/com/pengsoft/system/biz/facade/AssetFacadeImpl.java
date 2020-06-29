package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import com.pengsoft.system.biz.service.AssetService;
import com.pengsoft.system.domain.entity.Asset;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link AssetFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class AssetFacadeImpl extends EntityFacadeImpl<AssetService, Asset, String> implements AssetFacade {

}
