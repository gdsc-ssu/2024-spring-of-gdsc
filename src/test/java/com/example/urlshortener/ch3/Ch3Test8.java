package com.example.urlshortener.ch3;

import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.example.urlshortener.domain.url.entity.UrlClick;
import com.example.urlshortener.domain.url.repository.ShortenedUrlRepository;
import com.example.urlshortener.domain.url.repository.UrlClickRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

// 3.8
@SpringBootTest
class Ch3Test8 {

    @Autowired
    private ShortenedUrlRepository shortenedUrlRepository;

    @Autowired
    private UrlClickRepository urlClickRepository;

    // sql.init.data-locations 가 data.sql로 설정되어야 함
    // 영속성 컨텍스트(FetchType.LAZY)로 @Transactional 적용
    @Test
    @Transactional
    public void givenUrlClicksCreatedWhenLoadUrlClicksThenExpectCorrectUrlClicksDetails() {
        ShortenedUrl shortenedUrl = shortenedUrlRepository.findById(1L).get();

        assertThat(shortenedUrl.getUrlClicks().size()).isEqualTo(3);

        Long totalClicks =  shortenedUrl.getUrlClicks().stream()
                .mapToLong(UrlClick::getClicks)
                .sum();

        assertThat(totalClicks).isEqualTo(75L);
    }
}
