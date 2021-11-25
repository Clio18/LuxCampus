package com.luxcampus.Lection_11.staticServerV2;

import com.luxcampus.Lection_11.staticServerV2.domain.StatusCode;
import java.io.BufferedWriter;
import java.io.IOException;

public class ResponseWriter {
    public void writeSuccessResponse(String content, BufferedWriter writer) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: YarServer/2009-09-09\r\n" +
                "Content-Length: " + content.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + content;
        System.out.println("===== WRITE CONTENT: =====");
        System.out.println(result);
        writer.write(result);
        writer.flush();
    }

    public static void writeError(BufferedWriter writer, StatusCode statusCode) throws IOException {
        writer.write("HTTP/1.1 " + statusCode.getCode() + " " + statusCode.getStatus());
    }


}
