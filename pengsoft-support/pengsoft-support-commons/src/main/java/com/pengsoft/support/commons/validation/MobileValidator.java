package com.pengsoft.support.commons.validation;


import com.pengsoft.support.commons.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The mobile phone number validator.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return StringUtils.isMobile(value);
    }

}
