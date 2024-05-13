package com.example.urlshortener.domain.url.dto;

import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ShortenedUrlDto {

    private Long id;
    private String shortUrl;
    private String originUrl;
    private LocalDateTime createdAt;

    public static ShortenedUrlDto from(ShortenedUrl shortenedUrl) {
        return ShortenedUrlDto.builder()
            .id(shortenedUrl.getId())
            .shortUrl(shortenedUrl.getShortUrl())
            .originUrl(shortenedUrl.getOriginUrl())
            .createdAt(shortenedUrl.getCreatedAt())
            .build();
    }
}
