package com.luxcampus.Lection_11.CahtRoom;

import com.luxcampus.Lection_08.LinkedList.LinkedList;
import java.io.*;


public class ChatProcessor extends Thread {
    private Client client;
    private LinkedList<Client> clients;

    public ChatProcessor(Client client, LinkedList<Client> clients) {
        this.client = client;
        this.clients = clients;
    }

    public void run() {
        try {
            for (Client client : clients) {
                System.out.println(client.toString());
            }
            BufferedReader reader = client.getReader();
            String response = "[" + client.getName() + " :]" + reader.readLine() + "\n";
            String name = client.getName();
            for (Client chatClient : clients) {
                if (!chatClient.getName().equals(name)) {
                    BufferedWriter writer = chatClient.getWriter();
                    writer.write(response);
                    writer.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Smth goes wrong!");
        }
    }

}

