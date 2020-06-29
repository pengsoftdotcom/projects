package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.ProductFacade;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link Product}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/product")
public class ProductApi extends EntityApi<ProductFacade, Product, String> {

}
