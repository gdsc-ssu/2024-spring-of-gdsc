package com.example.urlshortener.domain.url.repository;

import com.example.urlshortener.domain.url.entity.QShortenedUrl;
import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShortenedUrlCustomRepositoryImpl implements ShortenedUrlCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public ShortenedUrlCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<ShortenedUrl> getAllShortUrlContainingInquiryByQueryDSL(String inquiry) {
        QShortenedUrl shortenedUrl = QShortenedUrl.shortenedUrl;

        return jpaQueryFactory
                .selectFrom(shortenedUrl)
                .where(shortenedUrl.originUrl.contains(inquiry))
                .fetch();
    }
}
