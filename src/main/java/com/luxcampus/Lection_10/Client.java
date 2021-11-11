package com.luxcampus.Lection_10;
import java.io.*;
import java.net.Socket;


public class Client {

    public static void main(String[] args) throws IOException {
        //starting Client
        Socket socket = new Socket("localhost", 5000);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        OutputStream outputStream = null;
        InputStream inputStream = null;
        while (true) {
            //reading from input
            String entered = reader.readLine();
            if(entered.equals("-1")){
                break;
            }
            //writing to the socket
            outputStream = socket.getOutputStream();
            outputStream.write(entered.getBytes());

            //reading the response from Server
            inputStream = socket.getInputStream();
            byte[] data = new byte[100];
            int count = inputStream.read(data);
            System.out.println(new String(data, 0, count));
        }
        outputStream.close();
        inputStream.close();
        reader.close();

    }
}
