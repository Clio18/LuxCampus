package com.luxcampus.Lection_11.staticServerV2;

import com.luxcampus.Lection_11.staticServerV2.domain.StatusCode;

public class ServerException extends RuntimeException {
    private StatusCode statusCode;

    public ServerException(StatusCode statusCode) {
        this.statusCode = statusCode;
    }

    public StatusCode getStatusCode() {
        return statusCode;
    }
}
