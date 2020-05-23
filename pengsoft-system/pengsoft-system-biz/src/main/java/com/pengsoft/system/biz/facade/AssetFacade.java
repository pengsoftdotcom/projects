package com.pengsoft.system.biz.facade;

import com.pengsoft.support.biz.facade.BeanFacade;
import com.pengsoft.system.biz.service.AssetService;
import com.pengsoft.system.domain.entity.Asset;

/**
 * The facade interface of {@link Asset}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface AssetFacade extends BeanFacade<AssetService, Asset, String>, AssetService {

}
