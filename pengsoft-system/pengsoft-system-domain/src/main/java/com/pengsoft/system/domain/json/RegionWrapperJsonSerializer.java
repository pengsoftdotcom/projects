package com.pengsoft.system.domain.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pengsoft.support.commons.util.StringUtils;

import java.io.IOException;

/**
 * {@link RegionWrapper} JSON serializer.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class RegionWrapperJsonSerializer extends JsonSerializer<RegionWrapper> {

    @Override
    public void serialize(RegionWrapper wrapper, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("name", StringUtils.substringBeforeLast(wrapper.getRegion().getName(), "市"));
        gen.writeEndObject();
    }

}
