package com.pengsoft.device.biz.facade;

import com.pengsoft.device.biz.service.PurchaseBatchService;
import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.facade.EntityFacadeImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link PurchaseBatchFacade}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class PurchaseBatchFacadeImpl extends EntityFacadeImpl<PurchaseBatchService, PurchaseBatch, String> implements PurchaseBatchFacade {

    @Override
    public Optional<User> findOneByName(final String name) {
        return getService().findOneByName(name);
    }

}
