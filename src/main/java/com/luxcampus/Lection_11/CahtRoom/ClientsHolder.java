package com.luxcampus.Lection_11.CahtRoom;

import com.luxcampus.Lection_08.LinkedList.LinkedList;

public class ClientsHolder {
    private LinkedList<Client> linkedList = new LinkedList<>();

    public void addClient(Client client){
       linkedList.add(client);
        for (Client chatClient : linkedList) {
            System.out.println(chatClient.toString());
        }
    }


    public LinkedList<Client> getClients() {
        return linkedList;
    }
}
