package com.luxcampus.Lection_11.CahtRoom;

import java.io.*;
import java.net.Socket;

public class ClientManager {
    public static Client getClient(Socket socket) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String name = reader.readLine();
        writer.write("Hello " + name + " from server!" + "\n");
        writer.flush();
        return new Client(name, reader, writer);
    }
}
