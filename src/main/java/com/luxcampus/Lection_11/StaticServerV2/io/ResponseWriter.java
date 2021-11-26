package com.luxcampus.Lection_11.StaticServerV2.io;

import com.luxcampus.Lection_11.StaticServerV2.domain.StatusCode;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class ResponseWriter {
    public static void writeSuccessResponse(InputStream content, OutputStream writer) throws IOException {
//        String response = "HTTP/1.1 200 OK\r\n" +
//                "Server: YarServer/2009-09-09\r\n" +
//                "Content-Length: " + content.length() + "\r\n" +
//                "Connection: close\r\n\r\n";
//        String result = response + content;
//        writer.write(result);
//        writer.flush();
        String line = "HTTP/1.1 200 OK \r\n";
        writer.write(line.getBytes(StandardCharsets.UTF_8));
        byte[] buffer = new byte[2048];
        int count;
        while ((count = content.read(buffer)) != -1) {
            writer.write(buffer, 0, count);
        }
        writer.flush();
    }

    public static void writeError(OutputStream writer, StatusCode statusCode) throws IOException {
        String line = "HTTP/1.1" + " " + statusCode.getCode() + " " + statusCode.getStatus() + "\r\n";
        writer.write(line.getBytes(StandardCharsets.UTF_8));
        writer.flush();
    }

}
