package ru.kvrk.urlshortener.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kvrk.urlshortener.service.UrlShortenerService;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UrlShortenerController {
    private final UrlShortenerService urlShortenerService;

    @PostMapping("/short")
    public String shortUrl(@RequestBody String url) {
        return urlShortenerService.shortUrl(url);
    }
}
