package com.pengsoft.system.domain.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pengsoft.system.domain.entity.Captcha;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * This wrapper just for JSON serialization.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
@JsonSerialize(using = CaptchaWrapperJsonSerializer.class)
@Getter
@RequiredArgsConstructor
public class CaptchaWrapper implements Serializable {

    private static final long serialVersionUID = 5958167478830762828L;

    private final Captcha captcha;

}
