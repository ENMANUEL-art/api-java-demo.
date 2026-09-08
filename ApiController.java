package com.example.demo;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ApiController {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/api/resultado", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String respuesta = "{\"status\":\"Exitoso\",\"mensaje\":\"Hola, la API se ejecuto correctamente.\"}";
                exchange.getResponseHeaders().set("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, respuesta.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(respuesta.getBytes());
                os.close();
            }
        });
        server.setExecutor(null);
        server.start();
        System.out.println("Servidor API corriendo en http://localhost:8080/api/resultado");
    }
}
