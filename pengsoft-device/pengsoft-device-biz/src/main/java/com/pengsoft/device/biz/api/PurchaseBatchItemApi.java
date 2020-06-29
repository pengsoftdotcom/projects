package com.pengsoft.device.biz.api;

import com.pengsoft.device.biz.facade.PurchaseBatchItemFacade;
import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.support.biz.api.EntityApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The web api of {@link PurchaseBatchItem}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@RestController
@RequestMapping("api/purchase-batch-item")
public class PurchaseBatchItemApi extends EntityApi<PurchaseBatchItemFacade, PurchaseBatchItem, String> {

}
