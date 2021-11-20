package com.luxcampus.Lection_11.CahtRoom;


import java.io.IOException;

public class Starter {
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.setPort(5000);
        server.start();
    }
}
