package com.luxcampus.Lection_11.StaticServerV2.handler;

import com.luxcampus.Lection_11.StaticServerV2.exception.ServerException;
import com.luxcampus.Lection_11.StaticServerV2.request.Request;
import com.luxcampus.Lection_11.StaticServerV2.io.ResourceReader;
import com.luxcampus.Lection_11.StaticServerV2.io.ResponseWriter;

import java.io.*;

public class RequestHandler {
    private InputStream reader;
    private OutputStream writer;
    private String webAppPath;

    public RequestHandler(InputStream reader, OutputStream writer, String webAppPath) {
        this.reader = reader;
        this.writer = writer;
        this.webAppPath = webAppPath;
    }

    public void handle() throws IOException {
        try {
            Request request = ResourceReader.parse(reader);
            //String content = ResourceReader.readResources(request.getUri(), webAppPath);
            InputStream content = ResourceReader.readResources(request.getUri(), webAppPath);
            byte[] f = content.readAllBytes();
            String a = new String(f);
            System.out.println(a);

            ResponseWriter.writeSuccessResponse(content, writer);
        } catch (ServerException e) {
            ResponseWriter.writeError(writer, e.getStatusCode());
        }
    }
}
