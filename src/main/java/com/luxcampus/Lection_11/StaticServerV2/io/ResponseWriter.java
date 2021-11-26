package com.luxcampus.Lection_11.StaticServerV2.io;

import com.luxcampus.Lection_11.StaticServerV2.domain.StatusCode;
import com.luxcampus.Lection_11.StaticServerV2.exception.ServerException;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Objects;

public class ResponseWriter {
    public static void writeSuccessResponse(String content, OutputStreamWriter writer) throws IOException {
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

    public static void writeError(OutputStreamWriter writer, StatusCode statusCode) throws IOException {
        System.out.println(statusCode.getCode() + " " + statusCode.getStatus());
        writer.write("HTTP/1.1" + " " + statusCode.getCode() + " " + statusCode.getStatus() + "\r\n");
    }

    private static void writeResponse(String content, OutputStreamWriter writer) throws IOException {
        if (Objects.isNull(content)) {
            throw new ServerException(StatusCode.BAD_REQUEST);
        }
        ResponseWriter responseWriter = new ResponseWriter();
        responseWriter.writeSuccessResponse(content, writer);

    }


    public static void writeNotFoundResponse(OutputStreamWriter writer) throws IOException {
        String response = "HTTP/1.1 404 Not Found\r\n" +
                "Connection: close\r\n\r\n";
        writer.write(response);
        writer.flush();
    }

    public static void writeBadRequestResponse(OutputStreamWriter writer) throws IOException {
        String response = "HTTP/1.1 400 Bad request\r\n" +
                "Connection: close\r\n\r\n";
        writer.write(response);
        writer.flush();

    }



}
