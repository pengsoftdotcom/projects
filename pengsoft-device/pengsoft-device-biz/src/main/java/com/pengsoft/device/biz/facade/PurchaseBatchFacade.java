package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.PurchaseBatchService;
import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.support.biz.facade.EntityFacade;

/**
 * The facade interface of {@link PurchaseBatch}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public interface PurchaseBatchFacade extends EntityFacade<PurchaseBatchService, PurchaseBatch, String>, PurchaseBatchService {

}
