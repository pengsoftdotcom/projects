package com.pengsoft.system.domain.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pengsoft.support.commons.util.DateUtils;

import java.io.IOException;

/**
 * {@link CaptchaWrapper} JSON serializer.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class CaptchaWrapperJsonSerializer extends JsonSerializer<CaptchaWrapper> {

    @Override
    public void serialize(final CaptchaWrapper wrapper, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("expiredAt", DateUtils.formatDateTime(wrapper.getCaptcha().getExpiredAt()));
        gen.writeEndObject();
    }

}
