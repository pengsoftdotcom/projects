package com.pengsoft.security.domain.validation;


import com.pengsoft.support.commons.validation.CharType;
import com.pengsoft.support.commons.validation.CharTypes;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The password constraint.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@CharTypes(allowed = { CharType.DIGIT, CharType.LOWERCASE_LETTER, CharType.UPPERCASE_LETTER, CharType.PUNCTUATION,
        CharType.SEPARATOR }, count = 2, message = "{CharTypes.password}")
@NotBlank
@Size(min = 6, max = 20)
@Documented
@Constraint(validatedBy = { })
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {

    String message() default "";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
