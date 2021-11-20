package com.luxcampus.Lection_11.CahtRoom;

import com.luxcampus.Lection_08.LinkedList.LinkedList;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class Server {
    private int port;
    private LinkedList<Client> clients = new LinkedList<>();
    ChatProcessor chatProcessor;
    public void setPort(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true){
            Socket socket = serverSocket.accept();
            String name = getNameAndGreetings(socket);
            Client client = new Client(name, socket);

            //adding to list
            clients.add(client);
            chatProcessor = new ChatProcessor(client, clients);
            chatProcessor.start();
        }
    }

    private String getNameAndGreetings(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String name = reader.readLine();
        writer.write("Hello " + name + " from server!" + "\n");
        writer.flush();
        return name;
    }
}
