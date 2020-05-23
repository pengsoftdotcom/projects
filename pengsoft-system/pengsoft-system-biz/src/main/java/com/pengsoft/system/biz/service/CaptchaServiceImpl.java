package com.pengsoft.system.biz.service;

import com.pengsoft.security.domain.entity.User;
import com.pengsoft.support.biz.service.BeanServiceImpl;
import com.pengsoft.support.commons.exception.BusinessException;
import com.pengsoft.support.commons.util.DateUtils;
import com.pengsoft.system.biz.repository.CaptchaRepository;
import com.pengsoft.system.domain.entity.Captcha;
import com.pengsoft.system.domain.entity.QCaptcha;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * The implementer of {@link CaptchaService} based on JPA.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Primary
@Service
public class CaptchaServiceImpl extends BeanServiceImpl<CaptchaRepository, Captcha, String> implements CaptchaService {

    private static final String EC_CAPTCHA_GENERATION_FAILED_EXCEEDED = "captcha.generation.failed.exceeded";

    private static final String EC_CAPTCHA_GENERATION_FAILED_FORBIDDEN = "captcha.generation.failed.forbidden";

    private static final int CAPTCHA_GENERATION_INTERVAL = 60;

    private static final int CAPTCHA_GENERATION_MAX_COUNT = 5;

    @Override
    public Captcha generate(final User user, final int expiration) {
        final var root = QCaptcha.captcha;
        if (count(root.user.eq(user).and(root.createdAt.after(DateUtils.currentDate().atStartOfDay()))) >= CAPTCHA_GENERATION_MAX_COUNT) {
            throw new BusinessException(EC_CAPTCHA_GENERATION_FAILED_EXCEEDED, CAPTCHA_GENERATION_MAX_COUNT);
        }
        final var optional = findUserLatest(user);
        final var currentDateTime = DateUtils.currentDateTime();
        if (optional.isPresent()) {
            final var captcha = optional.get();
            final var allowAt = captcha.getCreatedAt().plus(CAPTCHA_GENERATION_INTERVAL, ChronoUnit.SECONDS);
            if (allowAt.isAfter(currentDateTime)) {
                throw new BusinessException(EC_CAPTCHA_GENERATION_FAILED_FORBIDDEN, Duration.between(currentDateTime, allowAt).toSeconds());
            }
        }
        final var captcha = new Captcha();
        captcha.setUser(user);
        captcha.setCode(RandomStringUtils.randomNumeric(6));
        captcha.setExpiredAt(DateUtils.currentDateTime().plus(expiration, ChronoUnit.SECONDS));
        return save(captcha);
    }

    private Optional<Captcha> findUserLatest(final User user) {
        final Page<Captcha> page = findPage(QCaptcha.captcha.user.eq(user), PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "createdAt")));
        if (page.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(page.getContent().get(0));
        }
    }

    @Override
    public boolean isValid(final User user, final String code) {
        final var optional = findUserLatest(user).filter(captcha -> captcha.getCode().equals(code));
        if (optional.isPresent()) {
            delete(optional.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected Sort getDefaultSort() {
        return Sort.by(Sort.Direction.DESC, "createdAt");
    }

}
