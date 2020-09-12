package com.pengsoft.system.biz.filter;

import com.pengsoft.security.biz.facade.UserFacade;
import com.pengsoft.support.commons.exception.Exceptions;
import com.pengsoft.support.commons.json.ObjectMapper;
import com.pengsoft.system.biz.facade.CaptchaFacade;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * All URIs matched requests will do captcha verification by this filter.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@Named
public class CaptchaVerificationFilter extends OncePerRequestFilter {

    @Inject
    private UserFacade userFacade;

    @Inject
    private CaptchaFacade captchaFacade;

    @Inject
    private Exceptions exceptions;

    @Inject
    private MessageSource messageSource;

    @Inject
    private ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws ServletException, IOException {
        final var errors = new HashMap<String, List<String>>();
        final var username = req.getParameter("username");
        final var captcha = req.getParameter("captcha");
        var optional = userFacade.findOneByMobile(username);
        if (optional.isPresent()) {
            if (!captchaFacade.isValid(optional.get(), captcha)) {
                errors.put("captcha", List.of(messageSource.getMessage("captcha.invalid", null, LocaleContextHolder.getLocale())));
            }
        } else {
            errors.put("username", List.of(messageSource.getMessage("NotExists", null, LocaleContextHolder.getLocale())));
        }
        if (!errors.isEmpty()) {
            res.setCharacterEncoding("UTF-8");
            res.setContentType(MediaType.APPLICATION_JSON_VALUE);
            res.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
            objectMapper.writeValue(res.getWriter(), errors);
            return;
        }
        chain.doFilter(req, res);
    }

}
