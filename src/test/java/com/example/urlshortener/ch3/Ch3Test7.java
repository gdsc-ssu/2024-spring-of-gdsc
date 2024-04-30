package com.example.urlshortener.ch3;

import com.example.urlshortener.domain.url.entity.QShortenedUrl;
import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.example.urlshortener.domain.url.repository.ShortenedUrlRepository;
import com.example.urlshortener.domain.url.repository.interfaces.ShortUrlOnly;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 3.7
@SpringBootTest
class Ch3Test7 {

    @Autowired
    private ShortenedUrlRepository shortenedUrlRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Test
    public void givenShortenedUrlsCreatedWhenLoadShortenedUrlsWithQueryDslThenExpectCorrectShortenedUrlDetails() {
        saveMockedShortenedUrls();

        QShortenedUrl shortenedUrl = QShortenedUrl.shortenedUrl;
        List<ShortenedUrl> shortenedUrls = jpaQueryFactory
                .selectFrom(shortenedUrl)
                .where(shortenedUrl.originUrl.contains("http"))
                .fetch();
        assertThat(shortenedUrls.size()).isEqualTo(5);

        List<ShortenedUrl> shortenedUrls2 = jpaQueryFactory
                .selectFrom(shortenedUrl)
                .where(shortenedUrl.originUrl.contains("http").and(shortenedUrl.originUrl.contains("page")))
                .orderBy(shortenedUrl.shortUrl.desc())
                .fetch();

        assertThat(shortenedUrls2.size()).isEqualTo(5);
        assertThat(shortenedUrls2.get(0).getOriginUrl()).isEqualTo("http://example.com/page5");

        // OrderSpecifier 테스트는 생략합니다.
    }

    @Test
    public void givenAShortenedUrlAvailableWhenGetShortenedUrlByNameThenGetShortenedUrlDescription() {
        saveMockedShortenedUrls();

        Iterable<ShortUrlOnly> result = shortenedUrlRepository.findShortenedUrlByOriginUrl("http://example.com/page1");

        assertThat(result).extracting("shortUrl").contains("abc");
    }

    private void saveMockedShortenedUrls() {
        List<ShortenedUrl> shortenedUrls = List.of(
                new ShortenedUrl("abc", "http://example.com/page1", LocalDateTime.parse("2024-04-01T10:00:00")),
                new ShortenedUrl("def", "http://example.com/page2", LocalDateTime.parse("2024-04-02T12:00:00")),
                new ShortenedUrl("ghi", "http://example.com/page3", LocalDateTime.parse("2024-04-03T14:00:00")),
                new ShortenedUrl("jkl", "http://example.com/page4", LocalDateTime.parse("2024-04-04T16:00:00")),
                new ShortenedUrl("mno", "http://example.com/page5", LocalDateTime.parse("2024-04-05T18:00:00"))
        );

        shortenedUrlRepository.saveAll(shortenedUrls);
    }
}
