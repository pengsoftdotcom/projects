package com.pengsoft.security.domain.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pengsoft.security.domain.entity.Role;

import java.io.IOException;
import java.util.Collection;

/**
 * The {@link JsonSerializer} of {@link Role} collection.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class RoleCollectionSerializer extends JsonSerializer<Collection<Role>> {

    private final RoleSerializer roleSerializer = new RoleSerializer();

    @Override
    public void serialize(final Collection<Role> roles, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (final Role role : roles) {
            roleSerializer.serialize(role, gen, serializers);
        }
        gen.writeEndArray();
    }

}
