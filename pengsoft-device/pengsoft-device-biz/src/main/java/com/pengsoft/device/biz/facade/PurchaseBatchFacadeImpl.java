package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.PurchaseBatchService;
import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.support.biz.facade.BeanFacadeImpl;
import org.springframework.stereotype.Service;

/**
 * The implementer of {@link PurchaseBatchFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class PurchaseBatchFacadeImpl extends BeanFacadeImpl<PurchaseBatchService, PurchaseBatch, String> implements PurchaseBatchFacade {

}
