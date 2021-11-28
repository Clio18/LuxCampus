package com.luxcampus.Lection_11.StaticServerV2.handler;

import com.luxcampus.Lection_11.StaticServerV2.exception.ServerException;
import com.luxcampus.Lection_11.StaticServerV2.request.Request;
import com.luxcampus.Lection_11.StaticServerV2.io.ResourceReader;
import com.luxcampus.Lection_11.StaticServerV2.io.ResponseWriter;

import java.io.*;

public class RequestHandler {
    private BufferedReader reader;
    private ResourceReader resourceReader;
    private OutputStream writer;


    public RequestHandler(BufferedReader reader, ResourceReader resourceReader, OutputStream writer) {
        this.reader = reader;
        this.resourceReader = resourceReader;
        this.writer = writer;
    }

    public void handle() throws IOException {
        Request request = ResourceReader.parse(reader);
        try {
            InputStream content = resourceReader.readResources(request.getUri());
            ResponseWriter.writeSuccessResponse(content, writer);
        } catch (ServerException e) {
            ResponseWriter.writeError(writer, e.getStatusCode());
        }
    }
}
