package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.PurchaseBatchFacade;
import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link PurchaseBatch}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/purchase-batch")
public class PurchaseBatchApi extends EntityApi<PurchaseBatchFacade, PurchaseBatch, String> {

}
