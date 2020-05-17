package com.pengsoft.support.starter.autoconfigure;

import com.pengsoft.support.starter.autoconfigure.properties.TransactionAutoConfigureProperties;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * Transaction auto configure.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Configuration
@EnableTransactionManagement
@EnableConfigurationProperties(TransactionAutoConfigureProperties.class)
public class TransactionAutoConfigure {

    /**
     * Transaction interceptor
     */
    @Bean
    public TransactionInterceptor transactionAdvice(final TransactionManager transactionManager,
                                                    final TransactionAutoConfigureProperties properties) {
        final var readOnlyTransaction = new RuleBasedTransactionAttribute();
        readOnlyTransaction.setReadOnly(true);
        readOnlyTransaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_NOT_SUPPORTED);

        final var requiredTransaction = new RuleBasedTransactionAttribute();
        requiredTransaction.setRollbackRules(Collections.singletonList(new RollbackRuleAttribute(Exception.class)));
        requiredTransaction.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);

        final var transactionAttributes = new HashMap<String, TransactionAttribute>(16);
        properties.getReadonly().forEach(property -> transactionAttributes.put(property, readOnlyTransaction));
        properties.getRequired().forEach(property -> transactionAttributes.put(property, requiredTransaction));

        final var transactionAttributeSource = new NameMatchTransactionAttributeSource();
        transactionAttributeSource.setNameMap(transactionAttributes);

        return new TransactionInterceptor(transactionManager, transactionAttributeSource);
    }

    /**
     * Register transaction
     */
    @Bean
    public Advisor transactionAdviceAdvisor(final TransactionInterceptor transactionAdvice) {
        final var pointcut = new AspectJExpressionPointcut();
        final var expressions = new ArrayList<String>();
        expressions.add("execution(public * com..biz.repository.*Repository.*(..))");
        expressions.add("execution(public * com..biz.service.*Service.*(..))");
        expressions.add("execution(public * com..biz.facade.*Facade.*(..))");
        expressions.add("execution(public * com..biz.aspect.*Aspect.*(..))");
        pointcut.setExpression(String.join("||", expressions));
        return new DefaultPointcutAdvisor(pointcut, transactionAdvice);
    }

}
