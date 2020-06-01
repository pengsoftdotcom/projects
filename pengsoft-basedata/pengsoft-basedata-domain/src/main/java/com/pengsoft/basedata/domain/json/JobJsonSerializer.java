package com.pengsoft.basedata.domain.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pengsoft.basedata.domain.entity.Job;

import java.io.IOException;

/**
 * The {@link JsonSerializer} of {@link Job}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class JobJsonSerializer extends JsonSerializer<Job> {

    @Override
    public void serialize(final Job job, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", job.getId());
        gen.writeStringField("name", job.getName());
        gen.writeStringField("post", job.getPost().getName());
        gen.writeEndObject();
    }

}
