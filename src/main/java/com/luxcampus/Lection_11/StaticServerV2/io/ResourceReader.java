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

    public static Request parse(BufferedReader reader) throws IOException {
        Request request = new Request();
        String firstLine = reader.readLine();
        injectURIAndMethod(firstLine, request);
        injectHeader(reader, request);
        return request;
    }

    public InputStream readResources(String uri) {
        File file = new File(webAppPath, uri);
        try {
            return new FileInputStream(file);
        } catch (FileNotFoundException e) {
            throw new ServerException(StatusCode.NOT_FOUND);
        }
    }

    private static void injectHeader(BufferedReader readerBuff, Request request) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        while (true) {
            String s = readerBuff.readLine();
            if (s == null || s.trim().length() == 0) {
                break;
            }
            String[] data = s.split(": ");
            map.put(data[0], data[1]);
        }
        request.setHeaders(map);
    }

    private static void injectURIAndMethod(String firstLine, Request request) {
        if (Objects.isNull(firstLine)) {
            throw new ServerException(StatusCode.BAD_REQUEST);
        }
        String method = getMethod(firstLine);
        String uri = getURI(firstLine);
        request.setUri(uri);
        HttpMethod httpMethod = HttpMethod.valueOf(method);
        if (httpMethod.equals(HttpMethod.GET)) {
            request.setHttpMethod(httpMethod);
        } else if (httpMethod.equals(HttpMethod.POST)) {
            throw new ServerException(StatusCode.METHOD_NOT_ALLOWED);
        }
    }

    private static String getURI(String firstLine) {
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
        return uri;
    }

    private static String getMethod(String firstLine) {
        String[] pattern = firstLine.split("/");
        String method = pattern[0].trim();
        return method;
    }
}
