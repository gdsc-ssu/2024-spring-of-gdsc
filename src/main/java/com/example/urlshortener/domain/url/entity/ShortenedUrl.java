package com.example.urlshortener.domain.url.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "shortened_url")
public class ShortenedUrl {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "short_url", length = 2048, nullable = false)
    private String shortUrl;

    @NotBlank
    @Column(name = "origin_url", length = 2048, nullable = false)
    private String originUrl;

    // TODO: BaseEntity로 설정하기
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public ShortenedUrl(String shortUrl, String originUrl, LocalDateTime createdAt) {
        this.shortUrl = shortUrl;
        this.originUrl = originUrl;
        this.createdAt = createdAt;
    }
}
