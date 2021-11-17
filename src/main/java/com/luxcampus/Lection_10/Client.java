package com.luxcampus.Lection_10;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class Client {
    public static void main(String[] args){
        try (//try with resources which are implemented Closable interface (no needed to close)
             Socket socket = new Socket("localhost", 5000);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String entered = scanner.nextLine();
                if (entered.equals("-1")) {
                    break;
                }
                //write to socket
                //out.println(entered);
                out.write(entered);
                out.println();

                //read from server
                System.out.println("Server response: ");
                System.out.println(in.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
