package com.example.urlshortener.domain.url.repository;

import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, Long> {

    Optional<ShortenedUrl> findByOriginUrl(String longUrl);

    Optional<ShortenedUrl> findByShortUrl(String shortUrl);

}
