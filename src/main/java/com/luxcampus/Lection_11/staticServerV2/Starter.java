package com.luxcampus.Lection_11.staticServerV2;

import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(8080);
        server.setWebAppPath("src/main/resources/webapp");
        server.start();
    }
}
