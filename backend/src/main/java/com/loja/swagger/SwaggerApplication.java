package com.loja.swagger;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

import com.loja.resource.ClienteResource;
import com.loja.resource.OpenApiEndpoint; // Import your custom endpoint

@ApplicationPath("/api")
public class SwaggerApplication extends Application {
    
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(OpenApiEndpoint.class); // Use your custom endpoint
        resources.add(ClienteResource.class);
        return resources;
    }
}