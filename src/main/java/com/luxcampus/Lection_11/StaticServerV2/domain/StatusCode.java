package com.luxcampus.Lection_11.StaticServerV2.domain;

public enum StatusCode {
    NOT_FOUND(404, "Not found"),
    INTERNAL_SERVER_ERROR(500, "Internal server error"),
    BAD_REQUEST(400, "Bad request"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed");

    private final int code;
    private final String status;

    StatusCode(int code, String status) {
        this.code = code;
        this.status = status;
    }

    public int getCode() {
        return code;
    }

    public String getStatus() {
        return status;
    }
}
