package com.luxcampus.Lection_11.staticServerV2;

import java.util.Map;

public class Request {
    private String uri;
    private Map<String, String> headers;
    private HttpMethod httpMethod;

//    public Request(String uri, Map<String, String> headers, HttpMethod httpMethod) {
//        this.uri = uri;
//        this.headers = headers;
//        this.httpMethod = httpMethod;
//    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public String toString() {
        return "Request{" +
                "uri='" + uri + '\'' +
                ", headers=" + headers +
                ", httpMethod=" + httpMethod +
                '}';
    }
}

enum HttpMethod{
    POST, GET
}