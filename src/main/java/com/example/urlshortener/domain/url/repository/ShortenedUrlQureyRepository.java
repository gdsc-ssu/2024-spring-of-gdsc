package com.example.urlshortener.domain.url.repository;

import com.example.urlshortener.domain.url.dto.ShortenedUrlDto;
import com.example.urlshortener.domain.url.entity.QShortenedUrl;
import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ShortenedUrlQureyRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<ShortenedUrlDto> getShortUrlsWithQueryDsl(String inquiry) {
        QShortenedUrl shortenedUrl = QShortenedUrl.shortenedUrl;
        List<ShortenedUrl> shortenedUrls = jpaQueryFactory
                .selectFrom(shortenedUrl)
                .where(shortenedUrl.originUrl.contains(inquiry))
                .fetch();

        return ShortenedUrlDto.from(shortenedUrls);
    }
}
