package com.example.urlshortener.domain.url.controller;

import com.example.urlshortener.common.dto.Response;
import com.example.urlshortener.domain.url.controller.response.ShortUrlResponse;
import com.example.urlshortener.domain.url.dto.ShortenedUrlDto;
import com.example.urlshortener.domain.url.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/short-links")
@Tag(name = "🍃 4주차 과제", description = "🍃 4주차 과제입니다.")
public class UrlSearchController {

    private final UrlService urlService;

    public UrlSearchController(UrlService urlService) {
        this.urlService = urlService;
    }

    @Operation(
            summary = "특정 문자열을 포함하는 ShortUrl 조회하기 (JPA)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/list/jpa")
    public Response<List<ShortUrlResponse>> getShortUrlsContainingStringJpa(@RequestParam("inquiry") String inquiry) {
        List<ShortenedUrlDto> shortUrls = urlService.getShortUrlsContainingStringJpa(inquiry);
        return Response.data(shortUrls.stream().map(ShortUrlResponse::from).collect(Collectors.toList()));
    }

    @Operation(
            summary = "특정 문자열을 포함하는 ShortUrl 조회하기 (QueryDsl)",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/list/query-dsl")
    public Response<List<ShortUrlResponse>> getShortUrlsContainingStringQueryDsl(@RequestParam("inquiry") String inquiry) {
        List<ShortenedUrlDto> shortUrls = urlService.getShortUrlsContainingStringQueryDsl(inquiry);
        return Response.data(shortUrls.stream().map(ShortUrlResponse::from).collect(Collectors.toList()));
    }
}
