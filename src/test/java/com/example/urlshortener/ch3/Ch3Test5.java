package com.example.urlshortener.ch3;

import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.example.urlshortener.domain.url.repository.ShortenedUrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

// 3.5
@SpringBootTest
class Ch3Test5 {

    @Autowired
    private ShortenedUrlRepository shortenedUrlRepository;

    @Test
    public void givenShortenedUrlsWhenLoadShortenedUrlsWithQueryThenExpectCorrectShortenedUrlsDetails() {
        ShortenedUrl shortenedUrl = new ShortenedUrl("http://short.url/abc", "http://example.com/page1", LocalDateTime.now());
        ShortenedUrl savedShortenedUrl = shortenedUrlRepository.save(shortenedUrl);

        assertThat(shortenedUrlRepository.findByShortUrlWithQuery(savedShortenedUrl.getShortUrl()).get().getOriginUrl()).isEqualTo("http://example.com/page1");

        shortenedUrlRepository.updateOriginUrlByShortUrl("http://example.com/page2", savedShortenedUrl.getShortUrl());
        assertThat(shortenedUrlRepository.findByShortUrlWithQuery(savedShortenedUrl.getShortUrl()).get().getOriginUrl()).isEqualTo("http://example.com/page2");
    }
}
