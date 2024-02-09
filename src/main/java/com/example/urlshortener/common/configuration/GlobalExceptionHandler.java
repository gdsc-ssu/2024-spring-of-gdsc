package com.example.urlshortener.common.configuration;

import com.example.urlshortener.common.exception.ApiException;
import com.example.urlshortener.common.exception.dto.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ExceptionDto> handleException(final Exception e) {
        e.printStackTrace();
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ExceptionDto.of(500, "INTERNAL_SERVER_ERROR", e.getMessage()));
    }

    @ExceptionHandler(ApiException.class)
    protected ResponseEntity<ExceptionDto> handleApiException(final ApiException e) {
        e.printStackTrace();
        return ResponseEntity
                .status(e.getStatusCode())
                .body(ExceptionDto.of(e.getStatusCode().value(), e.getStatusCode().toString(), e.getMessage()));
    }
}
