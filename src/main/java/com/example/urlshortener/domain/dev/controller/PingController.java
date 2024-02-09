package com.example.urlshortener.domain.dev.controller;

import com.example.urlshortener.common.dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/dev")
@Tag(name = "🖥️ 개발 전용 API", description = "개발 전용 API")
public class PingController {
    @Operation(
        summary = "Ping 테스트",
        responses = @ApiResponse(responseCode = "200", description = "pong을 반환합니다.")
    )
    @GetMapping("ping")
    public Response<String> ping() {
        return Response.data("pong");
    }
}
