package com.luxcampus.Lection_10;
import java.io.*;
import java.net.Socket;


public class ClientTwins {
    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static OutputStream outputStream;
    static InputStream inputStream;

    public static void main(String[] args) throws IOException {
        //starting Client
        Socket socket = new Socket("localhost", 5000);

        while (true) {
            //reading from input
            String entered = reader.readLine();
            if (entered.equals("-1")) {
                break;
            }

            //writing to the socket
            outputStream = socket.getOutputStream();
            outputStream.write(entered.getBytes());

            //reading the response from Server
            inputStream = socket.getInputStream();
            //container for 100 cells (it can be any size)
            byte[] data = new byte[100];
            //here we figured out how many cells we really need
            int count = inputStream.read(data);
            //here we created the String which contains only the data without empty celld from container
            System.out.println(new String(data, 0, count));
        }

        outputStream.close();
        inputStream.close();
        reader.close();
    }
}
