package com.loja;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        // registra seus endpoints REST
        packages("com.loja.resource");

        // configura o Jackson para suportar datas (LocalDate, LocalDateTime, etc.)
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        JacksonJaxbJsonProvider provider = new JacksonJaxbJsonProvider();
        provider.setMapper(mapper);

        register(provider);
        
        // aqui não registramos o OpenApiResource porque depende de Servlet
    }
}
