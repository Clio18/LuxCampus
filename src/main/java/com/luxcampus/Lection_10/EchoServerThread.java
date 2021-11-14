package com.luxcampus.Lection_10;

import java.io.*;
import java.net.Socket;

public class EchoServerThread extends Thread {
    private Socket socket;
    static OutputStream outputStream;
    static InputStream inputStream;

    public EchoServerThread (Socket socket) {
        this.socket = socket;
    }

    public void run() {
            while (true) {
                try {
                    inputStream = socket.getInputStream();
                    byte[] buffer = new byte[100];
                    int count = inputStream.read(buffer);
                    String letter = new String(buffer, 0, count);
                    //making Echo response
                    String message = "Echo: " + letter;
                    outputStream = socket.getOutputStream();
                    outputStream.write(message.getBytes());
                } catch (IOException e) {
                    try {
                        closeResources(socket, inputStream, outputStream);
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    }
                    e.printStackTrace();
                }
            }

    }

    private void closeResources(Socket socket,  InputStream inputStream, OutputStream outputStream) throws IOException {
        socket.close();
        inputStream.close();
        outputStream.close();
    }
}
