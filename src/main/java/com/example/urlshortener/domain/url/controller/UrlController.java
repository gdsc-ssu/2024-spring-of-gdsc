package com.example.urlshortener.domain.url.controller;

import com.example.urlshortener.common.dto.Response;
import com.example.urlshortener.domain.url.controller.request.CreateShortUrlRequest;
import com.example.urlshortener.domain.url.controller.response.ShortUrlResponse;
import com.example.urlshortener.domain.url.dto.ShortenedUrlDto;
import com.example.urlshortener.domain.url.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RequiredArgsConstructor
@RestController
@RequestMapping("/short-links")
@Tag(name = "🔗 URL 단축기", description = "URL 단축기 API")
public class UrlController {

    private final UrlService urlService;

    @Operation(
            summary = "URL 단축하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @PostMapping
    public Response<ShortUrlResponse> createShortUrl(@Valid @RequestBody CreateShortUrlRequest request) {
        ShortenedUrlDto shortenedUrl = urlService.createShortUrl(request.getUrl());
        return Response.data(ShortUrlResponse.from(shortenedUrl));
    }

    @Operation(
            summary = "단축 URL 조회하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "URL_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/{short_id}")
    public Response<ShortUrlResponse> getShortUrl(@NotBlank @PathVariable("short_id") String shortId) {
        ShortenedUrlDto shortenedUrl = urlService.getShortUrl(shortId);
        return Response.data(ShortUrlResponse.from(shortenedUrl));
    }

    @Operation(
            summary = "Short URL 리디렉션",
            responses = {
                    @ApiResponse(responseCode = "302", description = "FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/r/{short_id}")
    public RedirectView redirectShortUrl(@NotBlank @PathVariable("short_id") String shortId) {
        String originUrl = urlService.getOriginUrl(shortId);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(originUrl);
        return redirectView;
    }
}
