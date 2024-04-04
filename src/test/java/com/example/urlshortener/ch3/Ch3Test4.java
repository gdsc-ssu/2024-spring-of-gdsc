package com.example.urlshortener.ch3;

import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.example.urlshortener.domain.url.repository.ShortenedUrlRepository;
import org.assertj.core.api.Condition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// 3.4
@SpringBootTest
class Ch3Test4 {

    @Autowired
    private ShortenedUrlRepository shortenedUrlRepository;

    @Test
    public void givenCreateShortenedUrlWhenLoadTheShortenedUrlThenExpectSameShortenedUrl() {
        ShortenedUrl shortenedUrl = new ShortenedUrl("http://short.url/abc", "http://example.com/page1", LocalDateTime.now());
        ShortenedUrl savedShortenedUrl = shortenedUrlRepository.save(shortenedUrl);

        assertThat(shortenedUrlRepository.findByOriginUrl(savedShortenedUrl.getOriginUrl()).get().getId()).isEqualTo(savedShortenedUrl.getId());
        assertThat(shortenedUrlRepository.existsByShortUrl(savedShortenedUrl.getShortUrl())).isTrue();
        assertThat(shortenedUrlRepository.countByOriginUrl(savedShortenedUrl.getOriginUrl())).isEqualTo(1);
    }

    @Test
    public void givenDataAvailableWhenSortsFirstPageThenGetSortedData() {
        saveMockedShortenedUrls();

        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Order.desc("shortUrl")));

        Condition<ShortenedUrl> sortedFirstCourseCondition = new Condition<ShortenedUrl>() {
            @Override
            public boolean matches(ShortenedUrl shortenedUrl) {
                return shortenedUrl.getId() == 5L && shortenedUrl.getShortUrl().equals("http://short.url/mno");
            }
        };

        // short_url 역순 정렬 검증
        assertThat(shortenedUrlRepository.findAll(pageable).getContent()).first().has(sortedFirstCourseCondition);
    }

    private void saveMockedShortenedUrls() {
        List<ShortenedUrl> shortenedUrls = List.of(
                new ShortenedUrl("http://short.url/abc", "http://example.com/page1", LocalDateTime.parse("2024-04-01T10:00:00")),
                new ShortenedUrl("http://short.url/def", "http://example.com/page2", LocalDateTime.parse("2024-04-02T12:00:00")),
                new ShortenedUrl("http://short.url/ghi", "http://example.com/page3", LocalDateTime.parse("2024-04-03T14:00:00")),
                new ShortenedUrl("http://short.url/jkl", "http://example.com/page4", LocalDateTime.parse("2024-04-04T16:00:00")),
                new ShortenedUrl("http://short.url/mno", "http://example.com/page5", LocalDateTime.parse("2024-04-05T18:00:00"))
        );

        shortenedUrlRepository.saveAll(shortenedUrls);
    }
}
