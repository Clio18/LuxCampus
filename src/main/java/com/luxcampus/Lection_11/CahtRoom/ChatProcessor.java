package com.luxcampus.Lection_11.CahtRoom;

import com.luxcampus.Lection_08.LinkedList.LinkedList;
import java.io.*;
import java.net.Socket;


public class ChatProcessor extends Thread {
    private Client client;
    private LinkedList<Client> clients;

    public ChatProcessor(Client client, LinkedList<Client> clients) {
        this.client = client;
        this.clients = clients;
    }

    public void run() {
        while (true) {
            try {
                System.out.println("Now in chat: ");
                for (Client client : clients) {
                    System.out.println(client.getName());
                }

                Socket socket = client.getSocket();
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String response = "[" + client.getName() + " :]" + reader.readLine() + "\n";
                String name = client.getName();
                for (Client chatClient : clients) {
                    if (!chatClient.getName().equals(name)) {
                        Socket clientSocket = chatClient.getSocket();
                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                        writer.write(response);
                        writer.flush();
                    }
                }
            } catch (IOException e) {
                System.err.println("Smth goes wrong!");
            }
        }
    }

}

