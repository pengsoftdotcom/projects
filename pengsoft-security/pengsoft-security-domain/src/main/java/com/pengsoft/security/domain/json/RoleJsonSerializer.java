package com.pengsoft.security.domain.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pengsoft.security.domain.entity.Role;

import java.io.IOException;

/**
 * The {@link JsonSerializer} of {@link Role}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class RoleJsonSerializer extends JsonSerializer<Role> {

    @Override
    public void serialize(final Role role, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", role.getId());
        gen.writeStringField("code", role.getCode());
        gen.writeStringField("name", role.getName());
        gen.writeEndObject();
    }

}

