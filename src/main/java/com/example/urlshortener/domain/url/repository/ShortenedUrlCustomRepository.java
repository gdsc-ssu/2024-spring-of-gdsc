package com.example.urlshortener.domain.url.repository;

import com.example.urlshortener.domain.url.entity.ShortenedUrl;

import java.util.List;

public interface ShortenedUrlCustomRepository {
    List<ShortenedUrl> getAllShortUrlContainingInquiryByQueryDSL(String inquiry);
}
