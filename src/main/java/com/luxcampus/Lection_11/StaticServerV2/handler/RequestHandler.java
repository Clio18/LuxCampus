package com.luxcampus.Lection_11.StaticServerV2.handler;

import com.luxcampus.Lection_11.StaticServerV2.exception.ServerException;
import com.luxcampus.Lection_11.StaticServerV2.request.Request;
import com.luxcampus.Lection_11.StaticServerV2.io.ResourceReader;
import com.luxcampus.Lection_11.StaticServerV2.io.ResponseWriter;

import java.io.*;

public class RequestHandler {
    private BufferedReader reader;
    private OutputStream writer;
    private ResourceReader resourceReader;

        public RequestHandler(BufferedReader reader, OutputStream writer, ResourceReader resourceReader) {
        this.reader = reader;
        this.writer = writer;
        this.resourceReader = resourceReader;
    }

    public void handle() throws IOException {
        Request request = ResourceReader.parse(reader);
        try (InputStream content = resourceReader.readResources(request.getUri())){

            System.out.println("===READ ALL CONTENT====");
            byte[] f = content.readAllBytes();
            String a = new String(f);
            System.out.println(a);

            ResponseWriter.writeSuccessResponse(content, writer);
        } catch (ServerException e) {
            ResponseWriter.writeError(writer, e.getStatusCode());
        }
    }
}
