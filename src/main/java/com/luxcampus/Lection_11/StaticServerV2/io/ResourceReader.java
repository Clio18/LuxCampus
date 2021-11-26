package com.luxcampus.Lection_11.StaticServerV2.io;

import com.luxcampus.Lection_11.StaticServerV2.domain.StatusCode;
import com.luxcampus.Lection_11.StaticServerV2.exception.ServerException;
import com.luxcampus.Lection_11.StaticServerV2.request.HttpMethod;
import com.luxcampus.Lection_11.StaticServerV2.request.Request;

import java.io.*;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringJoiner;


public class ResourceReader {
    private String webAppPath;

    public ResourceReader(String webAppPath) {
        this.webAppPath = webAppPath;
    }

    public static Request parse(InputStreamReader reader) throws IOException {
        Request request = new Request();
        BufferedReader readerBuff = new BufferedReader(reader);
        String firstLine = readerBuff.readLine();
        injectURIAndMethod(firstLine, request);
        injectHeader(readerBuff, request);
        return request;
    }

    public static String readResources(String uri, String webAppPath) throws IOException {
        File file = new File(webAppPath, uri);
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            while (true) {
                String line = bufferedReader.readLine();
                if (Objects.isNull(line) || line.isEmpty()) {
                    break;
                }
                stringBuilder.append(line + "\n");
            }
        } catch (FileNotFoundException e){
            throw new ServerException(StatusCode.NOT_FOUND);
        }
        String content = stringBuilder.toString();
        System.out.println("===== READ RESOURCES: =====");
        System.out.println(content);
        return content;
    }

    private static void injectHeader(BufferedReader readerBuff, Request request) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        while (true) {
            String s = readerBuff.readLine();
            if (s == null || s.trim().length() == 0) {
                break;
            }
            String[] data = s.split(":");
            map.put(data[0], data[1]);
        }
        request.setHeaders(map);
    }

    private static void injectURIAndMethod(String firstLine, Request request) {
        if (Objects.isNull(firstLine)) {
            throw new ServerException(StatusCode.BAD_REQUEST);
        }
        String[] pattern = firstLine.split("/");
        String method = pattern[0].trim();
        String[] patternOnWhiteSpace = firstLine.split(" ");
        String target = patternOnWhiteSpace[1];
        String[] patternOnSlash = target.split("/");
        StringJoiner stringJoiner = new StringJoiner("/");
        for (String piece : patternOnSlash) {
            if (piece.equals("localhost:8080") || piece.equals("")) {
                continue;
            }
            stringJoiner.add(piece);
        }
        String uri = stringJoiner.toString();
        request.setUri(uri);
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        if(httpMethod.equals(HttpMethod.GET)) {
            request.setHttpMethod(httpMethod);
        }else if (httpMethod.equals(HttpMethod.POST)) {
            throw new ServerException(StatusCode.METHOD_NOT_ALLOWED);
        }
    }
}
