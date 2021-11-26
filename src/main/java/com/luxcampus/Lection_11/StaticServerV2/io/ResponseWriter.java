package com.luxcampus.Lection_11.StaticServerV2.io;

import com.luxcampus.Lection_11.StaticServerV2.domain.StatusCode;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ResponseWriter {
    public static void writeSuccessResponse(String content, OutputStreamWriter writer) throws IOException {
        String response = "HTTP/1.1 200 OK\r\n" +
                "Server: YarServer/2009-09-09\r\n" +
                "Content-Length: " + content.length() + "\r\n" +
                "Connection: close\r\n\r\n";
        String result = response + content;
        writer.write(result);
        writer.flush();
    }

    public static void writeError(OutputStreamWriter writer, StatusCode statusCode) throws IOException {
        writer.write("HTTP/1.1" + " " + statusCode.getCode() + " " + statusCode.getStatus() + "\r\n");
        writer.flush();
    }

}
