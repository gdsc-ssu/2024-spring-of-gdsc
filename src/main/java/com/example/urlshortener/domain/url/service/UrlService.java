package com.example.urlshortener.domain.url.service;

import com.example.urlshortener.common.utils.RandomStringUtil;
import com.example.urlshortener.domain.url.dto.ShortenedUrlDto;
import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.example.urlshortener.domain.url.exception.UrlNotFoundException;
import com.example.urlshortener.domain.url.repository.ShortenedUrlRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UrlService {
    private final ShortenedUrlRepository shortenedUrlRepository;

    @Transactional
    public ShortenedUrlDto createShortUrl(String url) {
        ShortenedUrl existingShortenedUrl = shortenedUrlRepository.findByOriginUrl(url).orElse(null);

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

        return new ShortenedUrlDto(shortenedUrl.getId(), shortenedUrl.getShortUrl(), shortenedUrl.getOriginUrl(), shortenedUrl.getCreatedAt());
    }

    public String getOriginUrl(String shortId) {
        ShortenedUrl shortenedUrl = shortenedUrlRepository.findByShortUrl(shortId)
                .orElseThrow(UrlNotFoundException::new);

        return shortenedUrl.getOriginUrl();
    }

    public List<ShortenedUrlDto> getAllShortUrlContainingInquiryByJPA(String inquiry) {
        List<ShortenedUrlDto> shortenedUrls = shortenedUrlRepository.findByOriginUrlContaining(inquiry)
                .stream().map(ShortenedUrlDto::from).collect(Collectors.toList());

        return shortenedUrls;
    }

    public List<ShortenedUrlDto> getAllShortUrlContainingInquiryByQueryDSL(String inquiry) {
        List<ShortenedUrlDto> shortenedUrls = shortenedUrlRepository.getAllShortUrlContainingInquiryByQueryDSL(inquiry)
                .stream().map(ShortenedUrlDto::from).collect(Collectors.toList());

        return shortenedUrls;
    }
}
