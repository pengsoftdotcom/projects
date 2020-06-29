package com.pengsoft.basedata.domain.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pengsoft.basedata.domain.entity.Organization;

import java.io.IOException;

/**
 * The {@link JsonSerializer} of {@link Organization}.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class OrganizationJsonSerializer extends JsonSerializer<Organization> {

    @Override
    public void serialize(final Organization organization, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("id", organization.getId());
        gen.writeStringField("name", organization.getName());
        gen.writeEndObject();
    }

}
