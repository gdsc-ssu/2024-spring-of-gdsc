package com.example.urlshortener.domain.url.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "url_click")
public class UrlClick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shortened_url_id")
    private ShortenedUrl shortenedUrl;

    @NotNull
    @Column(name = "clicks", length = 128, nullable = false)
    private Long clicks;

    @NotNull
    @Column(name = "click_date", nullable = false)
    private LocalDate clickDate;

    // TODO: BaseEntity로 설정하기
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Builder
    public UrlClick(Long clicks, ShortenedUrl shortenedUrl, LocalDate clickDate, LocalDateTime createdAt) {
        this.shortenedUrl = shortenedUrl;
        this.clickDate = clickDate;
        this.clicks = clicks;
        this.createdAt = createdAt;
    }
}
