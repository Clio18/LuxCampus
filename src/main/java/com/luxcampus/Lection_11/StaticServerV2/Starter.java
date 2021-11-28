package com.luxcampus.Lection_11.StaticServerV2;

import com.luxcampus.Lection_11.StaticServerV2.server.Server;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(5000);
        server.setWebAppPath("src/main/resources/webapp");
        server.start();
    }
}
