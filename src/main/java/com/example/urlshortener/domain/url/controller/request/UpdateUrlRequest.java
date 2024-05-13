package com.example.urlshortener.domain.url.controller.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateUrlRequest {
    @NotBlank
    private String newOriginUrl;
}
