package com.example.urlshortener.common.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorCode {

    BAD_REQUEST(2001, "잘못된 요청입니다.");

    private final int code;
    private final String message;
}
