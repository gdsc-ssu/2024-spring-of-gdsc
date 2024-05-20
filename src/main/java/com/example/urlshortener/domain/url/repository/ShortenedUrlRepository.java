package com.example.urlshortener.domain.url.repository;

import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, Long> {

    Optional<ShortenedUrl> findByOriginUrl(String longUrl);

    Optional<ShortenedUrl> findByShortUrl(String shortUrl);

    List<ShortenedUrl> findAllByOriginUrlContains(String inquiry);
}
