package com.pengsoft.support.biz.repository;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;

import com.pengsoft.support.domain.entity.TreeBean;
import com.pengsoft.support.domain.entity.TreeBeanable;
import com.querydsl.core.types.dsl.EntityPathBase;

/**
 * The repository interface of {@link TreeBean} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@NoRepositoryBean
public interface TreeBeanRepository<Q extends EntityPathBase<T>, T extends TreeBeanable<T, ID>, ID extends Serializable>
        extends BeanRepository<Q, T, ID> {

}
