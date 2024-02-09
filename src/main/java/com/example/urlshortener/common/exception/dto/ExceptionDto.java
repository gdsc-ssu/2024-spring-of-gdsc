package com.example.urlshortener.common.exception.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionDto {
    private int status;
    private String error;
    private String message;

    public static ExceptionDto of(int status, String error, String message) {
        return new ExceptionDto(status, error, message);
    }
}