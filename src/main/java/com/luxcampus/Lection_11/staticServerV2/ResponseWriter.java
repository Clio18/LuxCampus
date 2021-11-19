package com.luxcampus.Lection_11.staticServerV2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.sql.SQLOutput;

public class ResponseWriter {
    public void writeSuccessResponse(String content, BufferedWriter writer) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: YarServer/2009-09-09\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + content.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + content;
        System.out.println("===== WRITE CONTENT: =====");
        System.out.println(result);
        writer.write(result);
        writer.flush();
    }
}
