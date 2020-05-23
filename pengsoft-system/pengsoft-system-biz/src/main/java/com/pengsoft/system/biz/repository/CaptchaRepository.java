package com.pengsoft.system.biz.repository;

import com.pengsoft.support.biz.repository.BeanRepository;
import com.pengsoft.system.domain.entity.Captcha;
import com.pengsoft.system.domain.entity.QCaptcha;
import com.querydsl.core.types.dsl.DateTimePath;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

/**
 * The repository interface of {@link Captcha} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface CaptchaRepository extends BeanRepository<QCaptcha, Captcha, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QCaptcha root) {
        BeanRepository.super.customize(bindings, root);
        bindings.bind(root.expiredAt).first(DateTimePath::before);
    }

}
