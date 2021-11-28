package com.luxcampus.Lection_11.StaticServerV2.io;

import com.luxcampus.Lection_11.StaticServerV2.domain.StatusCode;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class ResponseWriter {
    public static void writeSuccessResponse(InputStream content, OutputStream writer) throws IOException {
        System.out.println("===WRITE ALL CONTENT====");
        String line = "HTTP/1.1 200 OK \r\n";
        writer.write(line.getBytes());
        byte[] buffer = new byte[2048];
        int count;
        while ((count = content.read(buffer)) != -1) {
            System.out.println(buffer.length);
            writer.write(buffer, 0, count);
        }

    }

    public static void writeError(OutputStream writer, StatusCode statusCode) throws IOException {
        String line = "HTTP/1.1" + " " + statusCode.getCode() + " " + statusCode.getStatus();
        writer.write(line.getBytes());
    }

}
