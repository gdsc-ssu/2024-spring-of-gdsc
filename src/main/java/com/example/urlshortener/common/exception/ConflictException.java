package com.example.urlshortener.common.exception;

import static org.springframework.http.HttpStatus.CONFLICT;

public class ConflictException extends ApiException {
    public ConflictException(String message) {
        super(CONFLICT, message);
    }
}
