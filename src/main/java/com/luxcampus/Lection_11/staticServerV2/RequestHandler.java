package com.luxcampus.Lection_11.staticServerV2;

import com.luxcampus.Lection_11.staticServerV2.domain.StatusCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringJoiner;

public class RequestHandler {
    private BufferedReader reader;
    private BufferedWriter writer;
    private String webAppPath;

    public RequestHandler(BufferedReader reader, BufferedWriter writer, String webAppPath) {
        this.reader = reader;
        this.writer = writer;
        this.webAppPath = webAppPath;
    }

    void handle() throws IOException {
        try {
            try {
                Request request = parse(reader);
                String content = readResources(request.getUri());
                writeSuccessResponse(content, writer);
            } catch (Exception e) {
                writeBadRequestResponse(writer);
            }
        } catch (FileNotFoundException e) {
            writeNotFoundResponse(writer);
        }
    }

    private void writeSuccessResponse(String content, BufferedWriter writer) throws IOException {
        if (Objects.isNull(content)){
            throw new ServerException(StatusCode.BAD_REQUEST);
        }
        ResponseWriter responseWriter = new ResponseWriter();
        responseWriter.writeSuccessResponse(content, writer);

    }


    private void writeNotFoundResponse(BufferedWriter writer) throws IOException {
        String response = "HTTP/1.1 404 Not Found\r\n" +
                "Connection: close\r\n\r\n";
        writer.write(response);
        writer.flush();
    }

    private void writeBadRequestResponse(BufferedWriter writer) throws IOException {
        String response = "HTTP/1.1 400 Bad request\r\n" +
                "Connection: close\r\n\r\n";
        writer.write(response);
        writer.flush();

    }


    private String readResources(String uri) throws IOException {
        ResourceReader resourceReader = new ResourceReader(webAppPath);
        return resourceReader.readResources(uri);
    }

    private Request parse(BufferedReader reader) throws IOException {
        Request request = new Request();
        injectURIAndMethod(reader.readLine(), request);
        injectHeader(reader, request);
        System.out.println("===== REQUEST =====");
        System.out.println(request);
        return request;
    }

    private void injectHeader(BufferedReader reader, Request request) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        while (true) {
            String s = reader.readLine();
            if (s == null || s.trim().length() == 0) {
                break;
            }
            String[] data = s.split(":");
            map.put(data[0], data[1]);
        }
        request.setHeaders(map);
    }

    private static void injectURIAndMethod(String firstLine, Request request) {
        if (Objects.isNull(firstLine)){
            throw new ServerException(StatusCode.BAD_REQUEST);
        }
        String[] pattern = firstLine.split("/");
        String method = pattern[0];

        String[] patternOnWhiteSpace = firstLine.split(" ");
        String target = patternOnWhiteSpace[1];
        String [] patternOnSlash = target.split("/");
        StringJoiner stringJoiner = new StringJoiner("/");
        for (String piece : patternOnSlash) {
            if (piece.equals("localhost:8080")||piece.equals("")){
                continue;
            }
            stringJoiner.add(piece);
        }
        String uri = stringJoiner.toString();
        request.setUri(uri);
        if (method.equals(HttpMethod.GET.toString())) {
            request.setHttpMethod(HttpMethod.GET);
        } else if (method.equals(HttpMethod.POST.toString()))  {
            request.setHttpMethod(HttpMethod.POST);
        } else {
            throw new ServerException(StatusCode.METHOD_NOT_ALLOWED);
        }
    }
}
