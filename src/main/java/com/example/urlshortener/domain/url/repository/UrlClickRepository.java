package com.example.urlshortener.domain.url.repository;

import com.example.urlshortener.domain.url.entity.UrlClick;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlClickRepository extends JpaRepository<UrlClick, Long> {
}
