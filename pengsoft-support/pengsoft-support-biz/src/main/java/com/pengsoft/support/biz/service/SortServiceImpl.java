package com.pengsoft.support.biz.service;

import com.pengsoft.support.domain.entity.Entity;
import com.pengsoft.support.domain.entity.Sortable;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Map;

/**
 * The default implementer of {@link SortService}
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Service
public class SortServiceImpl implements SortService {

    @Inject
    private EntityManager entityManager;

    @Override
    public void sort(final Map<? extends Sortable, Long> sortInfo) {
        if (sortInfo != null) {
            sortInfo.forEach((sortable, sequence) -> {
                final String ql = "update " + sortable.getClass().getSimpleName() + " set sequence = ?1 where id = ?2";
                final Query query = entityManager.createQuery(ql);
                int index = 1;
                query.setParameter(index++, sequence);
                query.setParameter(index, ((Entity<?>) sortable).getId());
                query.executeUpdate();
            });
        }
    }

}
