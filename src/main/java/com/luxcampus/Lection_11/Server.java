package com.luxcampus.Lection_11;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringJoiner;

public class Server {
    private int port;
    private String webAppPath;

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getWebAppPath() {
        return webAppPath;
    }

    public void setWebAppPath(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public void start() throws IOException {
        while (true) {
            try (ServerSocket serverSocket = new ServerSocket(port);
                 Socket socket = serverSocket.accept();
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))
            ) {
                String uri = getRequest(socket, bufferedReader);
                System.out.println("Request obtained");
                sendPage(socket, uri, bufferedWriter);
                System.out.println("Response send");
            }
        }
    }

    private String getRequest(Socket client, BufferedReader reader) throws IOException {
        StringJoiner stringJoiner = new StringJoiner("\n");
        while (true) {
            String s = reader.readLine();
            if (s == null || s.trim().length() == 0) {
                break;
            }
            stringJoiner.add(s);
        }
        String lines = stringJoiner.toString();
        String header = lines.split("\n")[0];
        String[] pattern = header.split("/");
        String uri = "";
        for (int i = 0; i < pattern.length; i++) {
            //System.out.println(ss[i]);
            if (pattern[i].contains("HTTP")) {
                String[] uriWithHTTP = pattern[i].split(" ");
                uri = uriWithHTTP[0].trim();
            }
        }
        //reader.close();
        return uri;
    }


    private void sendPage(Socket client, String uri, BufferedWriter writer) throws IOException {
        System.out.println("Page writer called");
        String path = getWebAppPath() + "/" + uri;

        File index = new File(path);
        // the client
        BufferedReader reader = new BufferedReader(new FileReader(index));// grab a file and put it into the buffer
        // print HTTP headers
        writer.write("HTTP/1.1 200 OK");
        writer.newLine();
        writer.write("Content-Type: text/html");
        writer.newLine();
        writer.newLine();
        String line = reader.readLine();// line to go line by line from file
        while (line != null)// repeat till the file is read
        {
            writer.write(line);// print current line
            writer.newLine();
            line = reader.readLine();// read next line
        }
        //reader.close();// close the reader
        //printWriter.close();
    }

}
