package com.pengsoft.device.biz.service;

import com.pengsoft.device.biz.repository.PurchaseBatchRepository;
import com.pengsoft.device.domain.entity.PurchaseBatch;
import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.EntityServiceImpl;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.support.domain.util.EntityUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * The implementer of {@link PurchaseBatchService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class PurchaseBatchServiceImpl extends EntityServiceImpl<PurchaseBatchRepository, PurchaseBatch, String> implements PurchaseBatchService {

    @Override
    public PurchaseBatch save(final PurchaseBatch purchaseBatch) {
        findOneByName(purchaseBatch.getName()).ifPresent(source -> {
            if (EntityUtils.ne(source, purchaseBatch)) {
                throw getExceptions().constraintViolated("name", "Exists", purchaseBatch.getName());
            }
        });
        if (purchaseBatch.getPurchasedAt() == null) {
            purchaseBatch.setPurchasedAt(DateUtils.currentDate());
        }
        return super.save(purchaseBatch);
    }

    @Override
    public Optional<User> findOneByName(final String name) {
        return getRepository().findOneByName(name);
    }

}
