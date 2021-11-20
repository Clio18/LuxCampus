package com.luxcampus.Lection_11.CahtRoom;

import com.luxcampus.Lection_08.LinkedList.LinkedList;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


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
            //forming client and send greetings
            Client client = ClientManager.getClient(socket);
            //adding to list
            clients.add(client);
            chatProcessor = new ChatProcessor(client, clients);
            chatProcessor.start();
        }
    }
}
