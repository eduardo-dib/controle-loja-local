package com.loja.resource;

import io.swagger.v3.oas.integration.SwaggerConfiguration;
import io.swagger.v3.oas.integration.GenericOpenApiContextBuilder;
import io.swagger.v3.oas.integration.api.OpenApiContext;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.core.util.Json;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.Set;

@Path("/openapi.json")
public class OpenApiEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response openApi() {
        try {
            SwaggerConfiguration oasConfig = new SwaggerConfiguration()
                    .resourcePackages(Set.of("com.loja.resource")) // pacote onde estão os endpoints
                    .prettyPrint(true);

            OpenApiContext context = new GenericOpenApiContextBuilder()
                    .openApiConfiguration(oasConfig)
                    .buildContext(true);

            OpenAPI oas = context.read();

            // se mesmo assim vier null, cria manualmente um esqueleto
            if (oas == null) {
                oas = new OpenAPI();
            }

            if (oas.getOpenapi() == null) {
                oas.setOpenapi("3.0.1");
            }

            if (oas.getInfo() == null) {
                oas.info(new Info()
                        .title("Loja API")
                        .version("1.0.0")
                        .description("API da Loja com Grizzly + Jersey + Swagger"));
            }

            return Response.ok(Json.mapper().writeValueAsString(oas)).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().entity(e.getMessage()).build();
        }
    }
}