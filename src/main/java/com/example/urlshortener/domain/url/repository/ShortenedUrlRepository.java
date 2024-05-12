package com.example.urlshortener.domain.url.repository;

import com.example.urlshortener.domain.url.entity.ShortenedUrl;
import com.example.urlshortener.domain.url.repository.interfaces.ShortUrlOnly;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShortenedUrlRepository extends JpaRepository<ShortenedUrl, Long>, ShortenedUrlCustomRepository {

    Optional<ShortenedUrl> findByOriginUrl(String longUrl);

    Optional<ShortenedUrl> findByShortUrl(String shortUrl);

    boolean existsByShortUrl(String shortUrl);

    long countByOriginUrl(String longUrl);

    Page<ShortenedUrl> findAll(Pageable pageable);

    // TODO: @Query 사용은 지양하기
    @Query("SELECT s FROM ShortenedUrl s WHERE s.shortUrl = :shortUrl")
    Optional<ShortenedUrl> findByShortUrlWithQuery(@Param("shortUrl") String shortUrl);

    @Modifying
    @Transactional
    @Query("UPDATE ShortenedUrl s SET s.originUrl = :originUrl WHERE s.shortUrl = :shortUrl")
    int updateOriginUrlByShortUrl(@Param("originUrl") String originUrl, @Param("shortUrl") String shortUrl);

    Iterable<ShortUrlOnly> findShortenedUrlByOriginUrl(String originUrl);

    List<ShortenedUrl> findByOriginUrlContaining(String inquiry);
}
