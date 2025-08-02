package com.loja;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/api")
public class SwaggerApplication extends Application {

    public OpenAPI createOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                    .title("API Loja da Mãe")
                    .description("Documentação da API de Clientes, Produtos e Vendas")
                    .version("1.0"));
    }
}
