package com.loja;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        // registra seus endpoints
        packages("com.loja.resource");

        // registra o Swagger/OpenAPI
        register(OpenApiResource.class);

        // configura o Jackson
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(mapper);

        register(provider);
    }
}
