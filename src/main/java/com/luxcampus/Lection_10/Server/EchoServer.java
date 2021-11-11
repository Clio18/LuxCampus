package com.luxcampus.Lection_10.Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class EchoServer {
    public static void main(String[] args) throws IOException {
        //start Server
        ServerSocket serverSocket = new ServerSocket(5000);
        //accept the Socket from client
        Socket socket = serverSocket.accept();
        while (true) {
            //read data from socket
            InputStream inputStream = socket.getInputStream();
            byte[] buffer = new byte[100];
            int count = inputStream.read(buffer);
            String letter = new String(buffer, 0, count);
            //making Echo response
            String message = "Echo: " + letter;
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write(message.getBytes());
        }
    }
}
