package com.pengsoft.support.biz.service;

import com.pengsoft.support.domain.entity.Enable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.io.Serializable;

/**
 * The default implementer of {@link EnableService}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class EnableServiceImpl implements EnableService {

    @Inject
    private EntityManager entityManager;

    @Override
    public <T extends Enable<? extends Serializable>> void enable(final T enable) {
        update(enable, true);
    }

    @Override
    public <T extends Enable<? extends Serializable>> void disable(final T enable) {
        update(enable, false);
    }

    private <T extends Enable<? extends Serializable>> void update(final T bean, final boolean enabled) {
        bean.setEnabled(enabled);
        final var ql = "update " + bean.getClass().getSimpleName() + " set enabled = ?1 where id = ?2";
        final var query = entityManager.createQuery(ql);
        int index = 1;
        query.setParameter(index++, enabled);
        query.setParameter(index, bean.getId());
        query.executeUpdate();
    }

}
