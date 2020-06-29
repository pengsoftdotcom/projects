package com.pengsoft.basedata.domain.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.pengsoft.basedata.domain.entity.Job;
import com.pengsoft.basedata.domain.entity.Organization;

import java.io.IOException;
import java.util.Collection;

/**
 * The {@link JsonSerializer} of {@link Job} collection.
 *
 * @author dang.peng@pengsoft.com
 * @since 1.0.0
 */
public class OrganizationCollectionJsonSerializer extends JsonSerializer<Collection<Organization>> {

    private final OrganizationJsonSerializer organizationJsonSerializer = new OrganizationJsonSerializer();

    @Override
    public void serialize(final Collection<Organization> organizations, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
        gen.writeStartArray();
        for (final Organization organization : organizations) {
            organizationJsonSerializer.serialize(organization, gen, serializers);
        }
        gen.writeEndArray();
    }

}
