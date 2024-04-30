package com.example.urlshortener.domain.url.controller;

import com.example.urlshortener.common.dto.Response;
import com.example.urlshortener.domain.url.dto.ShortenedUrlDto;
import com.example.urlshortener.domain.url.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/short-links/list")
@Tag(name = "🍃 4주차 과제", description = "4주차 과제입니다.")
public class Week4Controller {

    private final UrlService urlService;

    @Operation(
        summary = "Data JPA로 URL 조회하기",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
        }
    )
    @GetMapping("/jpa")
    public Response<List<ShortenedUrlDto>> getShortUrlsWithJpa(@NotBlank @RequestParam("inquiry") String inquiry) {
        List<ShortenedUrlDto> shortenedUrls = urlService.getShortUrlsWithJpa(inquiry);
        return Response.data(shortenedUrls);
    }

    @Operation(
        summary = "QueryDsl로 URL 조회하기",
        responses = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
        }
    )
    @GetMapping("/query-dsl")
    public Response<List<ShortenedUrlDto>> getShortUrl(@NotBlank @RequestParam("inquiry") String inquiry) {
        List<ShortenedUrlDto> shortenedUrls = urlService.getShortUrlsWithQueryDsl(inquiry);
        return Response.data(shortenedUrls);
    }
}
