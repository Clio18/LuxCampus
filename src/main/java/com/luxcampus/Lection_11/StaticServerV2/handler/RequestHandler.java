package com.luxcampus.Lection_11.StaticServerV2.handler;
import com.luxcampus.Lection_11.StaticServerV2.exception.ServerException;
import com.luxcampus.Lection_11.StaticServerV2.request.Request;
import com.luxcampus.Lection_11.StaticServerV2.io.ResourceReader;
import com.luxcampus.Lection_11.StaticServerV2.io.ResponseWriter;
import java.io.*;

public class RequestHandler {
    private InputStreamReader reader;
    private OutputStreamWriter writer;
    private String webAppPath;

    public RequestHandler(InputStreamReader reader, OutputStreamWriter writer, String webAppPath) {
        this.reader = reader;
        this.writer = writer;
        this.webAppPath = webAppPath;
    }

    public void handle() throws IOException {
        try {
                System.out.println("====START====");
                Request request = ResourceReader.parse(reader);
                String content = ResourceReader.readResources(request.getUri(), webAppPath);
                ResponseWriter.writeSuccessResponse(content, writer);
                System.out.println("====FINISH====");
            } catch (ServerException e) {
                ResponseWriter.writeError(writer, e.getStatusCode());
            }
    }
}
