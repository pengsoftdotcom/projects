package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.BeanFacadeImpl;
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
public class AssetFacadeImpl extends BeanFacadeImpl<AssetService, Asset, String> implements AssetFacade {

}
