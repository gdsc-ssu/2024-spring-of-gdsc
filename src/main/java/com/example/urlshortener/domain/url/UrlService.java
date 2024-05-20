package com.example.urlshortener.domain.url;

import com.example.urlshortener.common.utils.RandomStringUtil;
import com.example.urlshortener.domain.url.dto.ShortenedUrlDto;
import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.example.urlshortener.domain.url.exception.UrlNotFoundException;
import com.example.urlshortener.domain.url.repository.ShortenedUrlQueryRepository;
import com.example.urlshortener.domain.url.repository.ShortenedUrlRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UrlService {

    private final ShortenedUrlRepository shortenedUrlRepository;
    private final ShortenedUrlQueryRepository shortenedUrlQueryRepository;

    @Transactional
    public ShortenedUrlDto createShortUrl(String url) {
        ShortenedUrl existingShortenedUrl = shortenedUrlRepository.findByOriginUrl(url)
            .orElse(null);

        if (existingShortenedUrl != null) {
            return ShortenedUrlDto.from(existingShortenedUrl);
        }

        String shortUrl = RandomStringUtil.generateRandomString(8);

        ShortenedUrl shortenedUrl = new ShortenedUrl(shortUrl, url, LocalDateTime.now());
        shortenedUrl = shortenedUrlRepository.save(shortenedUrl);

        return ShortenedUrlDto.from(shortenedUrl);
    }

    public ShortenedUrlDto getShortUrl(String shortId) {
        ShortenedUrl shortenedUrl = shortenedUrlRepository.findByShortUrl(shortId)
            .orElseThrow(UrlNotFoundException::new);

        return new ShortenedUrlDto(shortenedUrl.getId(), shortenedUrl.getShortUrl(),
            shortenedUrl.getOriginUrl(), shortenedUrl.getCreatedAt());
    }

    public String getOriginUrl(String shortId) {
        ShortenedUrl shortenedUrl = shortenedUrlRepository.findByShortUrl(shortId)
            .orElseThrow(UrlNotFoundException::new);

        return shortenedUrl.getOriginUrl();
    }


    public List<ShortenedUrlDto> getShortUrlsWithJpa(String inquiry) {
        List<ShortenedUrl> shortenedUrls = shortenedUrlRepository.findAllByOriginUrlContains(
            inquiry);

        return ShortenedUrlDto.from(shortenedUrls);
    }

    public List<ShortenedUrlDto> getShortUrlsWithQueryDsl(String inquiry) {
        return shortenedUrlQueryRepository.getShortUrlsWithQueryDsl(inquiry);
    }
}
