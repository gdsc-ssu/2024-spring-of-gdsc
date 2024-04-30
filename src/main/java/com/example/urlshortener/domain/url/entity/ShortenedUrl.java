package com.example.urlshortener.domain.url.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
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

    @OneToMany(mappedBy = "shortenedUrl", fetch = FetchType.LAZY)
    private Set<UrlClick> urlClicks = new HashSet<>();

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
