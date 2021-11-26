package com.luxcampus.Lection_11.StaticServerV2.server;

import com.luxcampus.Lection_11.StaticServerV2.handler.RequestHandler;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private int port;
    private String webAppPath;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                try (Socket socket = serverSocket.accept();
                     InputStreamReader reader = new InputStreamReader(socket.getInputStream());
                     OutputStreamWriter writer = new OutputStreamWriter(socket.getOutputStream())) {
                    RequestHandler requestHandler = new RequestHandler(reader, writer, webAppPath);
                    System.out.println("====SERVER RUN====");
                    requestHandler.handle();
                }
            }
        }
    }
}
