package com.example.urlshortener.domain.url.controller.request;

import com.example.urlshortener.common.annotation.Https;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateShortUrlRequest{
    // url은 https가 적용되어야 합니다.
    @Https
    private String url;

}
