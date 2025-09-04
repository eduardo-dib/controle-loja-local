package com.loja;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.grizzly.http.server.CLStaticHttpHandler;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

import java.io.IOException;
import java.net.URI;

public class App {
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        // Cria ResourceConfig da sua aplicação
        final JerseyConfig rc = new JerseyConfig();

        // Cria servidor Grizzly com JAX-RS
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc, false);

        // Adiciona handler para servir os arquivos estáticos do Swagger UI
        // swagger-ui deve estar em src/main/resources/swagger-ui/
        CLStaticHttpHandler staticHandler = new CLStaticHttpHandler(App.class.getClassLoader(), "swagger-ui/");
        server.getServerConfiguration().addHttpHandler(staticHandler, "/swagger-ui");

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return server;
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("🚀 API rodando em: " + BASE_URI);
        System.out.println("📄 Swagger UI: http://localhost:8080/swagger-ui/");
        System.out.println("Pressione Enter para encerrar...");
        System.in.read();
        server.shutdownNow();
    }
}
