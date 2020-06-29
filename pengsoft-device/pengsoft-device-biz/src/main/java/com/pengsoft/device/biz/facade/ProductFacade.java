package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.ProductService;
import com.pengsoft.device.domain.entity.Product;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link Product}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface ProductFacade extends EntityFacade<ProductService, Product, String>, ProductService {

}
