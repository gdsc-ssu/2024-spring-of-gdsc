package com.example.urlshortener.domain.url.controller;

import com.example.urlshortener.common.dto.Response;
import com.example.urlshortener.domain.url.controller.response.ShortUrlResponse;
import com.example.urlshortener.domain.url.dto.ShortenedUrlDto;
import com.example.urlshortener.domain.url.service.UrlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/short-links")
@Tag(name = "🖥🌿 4주차 추가 과제", description = "4주차 추가 과제입니다.")
public class Hw4MoreController {
    private final UrlService urlService;

    @Operation(
            summary = "id로 조회하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "ID_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("/id/{id}")
    public Response<ShortUrlResponse> getShortUrl(@NotNull @PathVariable("id") Long id) {
        ShortenedUrlDto shortenedUrl = urlService.getShortUrlById(id);
        return Response.data(ShortUrlResponse.from(shortenedUrl));
    }

    @Operation(
            summary = "id로 삭제하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "ID_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("id/delete/{id}")
    public Response<ShortUrlResponse> deleteShortUrl(@NotNull @PathVariable("id") Long id) {
        urlService.deleteShortUrl(id);
        return new Response<>(0, "삭제 성공", null);
    }

    @Operation(
            summary = "id에 해당하는 createAt을 현재로 수정하기",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "404", description = "ID_NOT_FOUND"),
                    @ApiResponse(responseCode = "500", description = "INTERNAL_SERVER_ERROR")
            }
    )
    @GetMapping("id/update/{id}")
    public Response<ShortUrlResponse> updateShortUrl(@NotNull @PathVariable("id") Long id) {
        ShortenedUrlDto shortenedUrl = urlService.updateShortUrl(id);
        return Response.data(ShortUrlResponse.from(shortenedUrl));
    }

}
