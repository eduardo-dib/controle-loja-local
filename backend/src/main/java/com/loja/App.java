package com.loja;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

public class App {
    public static final String BASE_URI = "http://localhost:8080/api/";

    public static HttpServer startServer() {
        // Define onde estão seus endpoints (pacote com os @Path)
        final ResourceConfig rc = new ResourceConfig().packages("com.loja.resource");
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();
        System.out.println("🚀 Servidor rodando em: " + BASE_URI);
        System.out.println("Pressione Enter para encerrar...");
        System.in.read();
        server.shutdownNow();
    }
}
