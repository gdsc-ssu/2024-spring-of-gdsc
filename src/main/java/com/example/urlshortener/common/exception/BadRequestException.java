package com.example.urlshortener.common.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class BadRequestException extends ApiException {
    public BadRequestException(String message) {
        super(BAD_REQUEST, message);
    }
}

