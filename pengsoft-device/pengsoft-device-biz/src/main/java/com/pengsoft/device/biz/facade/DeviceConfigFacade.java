package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.ProductConfigService;
import com.pengsoft.device.domain.entity.ProductConfig;
import com.pengsoft.support.biz.facade.BeanFacade;

/**
 * The facade interface of {@link ProductConfig}
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
public interface ProductConfigFacade extends BeanFacade<ProductConfigService, ProductConfig, String>, ProductConfigService {

}
