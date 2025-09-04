package com.loja.swagger;

import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

import com.loja.resource.*;

@ApplicationPath("/api")
public class SwaggerApplication extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(OpenApiResource.class); 
        resources.add(ClienteResource.class);
        return resources;
    }
}

