package com.pengsoft.support.biz.facade;

import com.pengsoft.support.biz.service.BeanService;
import com.pengsoft.support.domain.entity.Beanable;
import org.springframework.validation.annotation.Validated;

import java.io.Serializable;

/**
 * The facade interface of {@link Beanable}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Validated
public interface BeanFacade<S extends BeanService<T, ID>, T extends Beanable<ID>, ID extends Serializable> extends BeanService<T, ID> {

    S getService();

}
