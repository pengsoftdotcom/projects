package com.pengsoft.system.biz.repository;

import com.pengsoft.support.biz.repository.BeanRepository;
import com.pengsoft.system.domain.entity.MessageTemplate;
import com.pengsoft.system.domain.entity.QMessageTemplate;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.stereotype.Repository;

import javax.persistence.QueryHint;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

/**
 * The repository interface of {@link MessageTemplate} based on JPA
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Repository
public interface MessageTemplateRepository extends BeanRepository<QMessageTemplate, MessageTemplate, String> {

    @Override
    default void customize(final QuerydslBindings bindings, final QMessageTemplate root) {
        BeanRepository.super.customize(bindings, root);
        bindings.bind(root.code).first(StringPath::contains);
        bindings.bind(root.subject).first(StringPath::contains);
    }

    /**
     * Returns an {@link Optional} of a {@link MessageTemplate} with given code.
     *
     * @param code {@link MessageTemplate}'code
     */
    @QueryHints(value = @QueryHint(name = "org.hibernate.cacheable", value = "true"), forCounting = false)
    Optional<MessageTemplate> findOneByCode(@NotBlank String code);

}
