package com.example.urlshortener.domain.url.exception;

import com.example.urlshortener.common.exception.BadRequestException;

public class UrlNotFoundException extends BadRequestException {

    private static final String MESSAGE = "존재하지 않는 URL입니다.";

    public UrlNotFoundException() {
        super(MESSAGE);
    }
}
