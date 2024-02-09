package com.example.urlshortener.domain.url.controller.response;

import com.example.urlshortener.domain.url.dto.ShortenedUrlDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import static lombok.AccessLevel.PRIVATE;

@Getter
@AllArgsConstructor
@Builder(access = PRIVATE)
public class ShortUrlResponse {
    private String shortUrl;
    private String originUrl;
    private LocalDateTime createdAt;

    public static ShortUrlResponse from(ShortenedUrlDto shortenedUrl) {
        return ShortUrlResponse.builder()
            .shortUrl(shortenedUrl.getShortUrl())
            .originUrl(shortenedUrl.getOriginUrl())
            .createdAt(shortenedUrl.getCreatedAt())
            .build();
    }
}
