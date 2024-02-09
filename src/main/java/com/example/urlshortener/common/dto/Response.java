package com.example.urlshortener.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {
    private int code;
    private String message;
    private T data;

    public static <T> Response<T> of(int code, String message, T data) {
        return new Response<>(code, message, data);
    }

    public static <T> Response<T> data(T data) {
        return new Response<>(0, "", data);
    }
}