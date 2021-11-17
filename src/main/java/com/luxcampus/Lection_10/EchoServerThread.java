package com.luxcampus.Lection_10;

import java.io.*;
import java.net.Socket;

public class EchoServerThread extends Thread {
    private Socket socket;

    public EchoServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (true) {
                String message = "Echo: " + in.readLine();
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
