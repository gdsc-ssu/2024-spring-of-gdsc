package com.example.urlshortener.domain.url.controller;


import com.example.urlshortener.common.dto.Response;
import com.example.urlshortener.domain.url.controller.response.ShortUrlResponse;
import com.example.urlshortener.domain.url.dto.ShortenedUrlDto;
import com.example.urlshortener.domain.url.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/short-links")
@Tag(name = "🍃4주차 과제", description = "[4주차]선의 과제입니다")
public class Week4ShortUrl {
    private final UrlService urlService;

    @Operation(
            summary = "Data JPA로 URL 조회하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "URL_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/list/jpa")
    public Response<ShortUrlResponse> getShortUrl(@RequestParam String inquiry) {
        ShortenedUrlDto shortenedUrl = urlService.getShortUrl(inquiry);
        return Response.data(ShortUrlResponse.from(shortenedUrl));
    }

    @Operation(
            summary = "QueryDsl로 URL 조회하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "URL_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/list/query-dsl")
    public Response<List<ShortenedUrlDto>> getShortUrlQureyDsl(@RequestParam String inquiry) {
        List<ShortenedUrlDto> shortenedUrl = urlService.getShortUrlQureyDsl(inquiry);
        return Response.data(shortenedUrl);
    }
}
