package com.example.urlshortener.common.exception;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class ForbiddenException extends ApiException {
    public ForbiddenException(String message) {
        super(FORBIDDEN, message);
    }
}