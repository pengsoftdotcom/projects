package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.ProductConfigService;
import com.pengsoft.device.domain.entity.ProductConfig;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link ProductConfig}
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
public interface ProductConfigFacade extends EntityFacade<ProductConfigService, ProductConfig, String>, ProductConfigService {

}
