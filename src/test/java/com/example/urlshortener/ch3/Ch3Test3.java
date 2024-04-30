package com.example.urlshortener.ch3;

import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.example.urlshortener.domain.url.repository.ShortenedUrlRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

// 3.3
@SpringBootTest
class Ch3Test3 {

    @Autowired
    private ShortenedUrlRepository shortenedUrlRepository;

    @Test
    public void givenCreateShortenedUrlWhenLoadTheShortenedUrlThenExpectSameShortenedUrl() {
        ShortenedUrl shortenedUrl = new ShortenedUrl("abc", "http://example.com/page1", LocalDateTime.now());
        ShortenedUrl savedShortenedUrl = shortenedUrlRepository.save(shortenedUrl);

        // TODO: 실제로는 이렇게 사용하면 안됨 with Transactional
        assertThat(shortenedUrlRepository.findById(savedShortenedUrl.getId()).get().getId()).isEqualTo(savedShortenedUrl.getId());
    }

    @Test
    public void givenUpdateShortenedUrlWhenLoadTheShortenedUrlThenExpectUpdatedShortenedUrl() {
        ShortenedUrl shortenedUrl = new ShortenedUrl("abc", "http://example.com/page1", LocalDateTime.now());
        shortenedUrlRepository.save(shortenedUrl);

        shortenedUrl.setOriginUrl("http://example.com/page2");
        // TODO: 영속성 컨텍스트 / 엔티티 생명주기 이야기
        ShortenedUrl updatedShortenedUrl = shortenedUrlRepository.save(shortenedUrl);

        assertThat(shortenedUrlRepository.findById(updatedShortenedUrl.getId()).get().getOriginUrl()).isEqualTo("http://example.com/page2");
    }

    @Test
    public void givenDeleteShortenedUrlWhenLoadTheShortenedUrlThenExpectNoShortenedUrl() {
        ShortenedUrl shortenedUrl = new ShortenedUrl("abc", "http://example.com/page1", LocalDateTime.now());
        ShortenedUrl savedShortenedUrl = shortenedUrlRepository.save(shortenedUrl);

        assertThat(shortenedUrlRepository.findById(savedShortenedUrl.getId()).get().getId()).isEqualTo(savedShortenedUrl.getId());

        shortenedUrlRepository.delete(savedShortenedUrl);
        assertThat(shortenedUrlRepository.findById(savedShortenedUrl.getId()).isPresent()).isFalse();
    }
}
