package ru.kvrk.urlshortener.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.kvrk.urlshortener.service.UrlShortenerService;

@RestController
@RequiredArgsConstructor
public class FullUrlController {
    private final UrlShortenerService urlShortenerService;

    @GetMapping("/{shortUrl}")
    public String getFullUrl(@PathVariable String shortUrl) {
        return urlShortenerService.getFullUrl(shortUrl);
    }
}
