package com.example.urlshortener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
@Slf4j
public class UrlShortenerApplication {

    // Lombok 사용 x
    // private static final Logger log = LoggerFactory.getLogger(UrlShortenerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(UrlShortenerApplication.class, args);

        // Slf4j logger로 로깅하기
        var now = LocalDateTime.now().toString();
        log.info("Application started at {}", now);
    }
}
