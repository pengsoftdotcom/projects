package com.pengsoft.device.biz.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pengsoft.device.biz.facade.ProductConfigFacade;
import com.pengsoft.device.domain.entity.ProductConfig;
import com.pengsoft.support.biz.api.EntityApi;

/**
 * The web api of {@link ProductConfig}
 *
 * @author dang.peng@pengsoft.com
 * @since  1.0.0
 */
@RestController
@RequestMapping("api/product-config")
public class ProductConfigApi extends EntityApi<ProductConfigFacade, ProductConfig, String> {

}
