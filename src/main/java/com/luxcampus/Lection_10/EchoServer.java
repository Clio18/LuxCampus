package com.luxcampus.Lection_10;
import java.io.IOException;
import java.net.ServerSocket;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        while (true) {
            new EchoServerThread(serverSocket.accept()).start();
        }
    }
}
