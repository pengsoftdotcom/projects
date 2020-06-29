package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.PurchaseBatchItemService;
import com.pengsoft.device.domain.entity.PurchaseBatchItem;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link PurchaseBatchItem}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface PurchaseBatchItemFacade extends EntityFacade<PurchaseBatchItemService, PurchaseBatchItem, String>, PurchaseBatchItemService {

}
