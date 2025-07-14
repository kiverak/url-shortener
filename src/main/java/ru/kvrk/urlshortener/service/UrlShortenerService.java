package ru.kvrk.urlshortener.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kvrk.urlshortener.exception.ShortUrlCreationException;
import ru.kvrk.urlshortener.exception.ShortUrlNotFoundException;
import ru.kvrk.urlshortener.model.ShortenLink;
import ru.kvrk.urlshortener.repo.UrlShortenerRepo;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UrlShortenerService {
    private static final String BASE_URL = "http://localhost:8083";
    private static final short ATTEMPT_NUMBER = 10;

    private final UrlShortenerRepo urlShortenerRepo;

//    @Transactional
    public String shortUrl(String fullUrl) {
        String encodedUrl = getEncodedUrl(fullUrl);
        if (encodedUrl == null) {
            throw new ShortUrlCreationException(fullUrl);
        }

        ShortenLink shortenLink = new ShortenLink();
        shortenLink.setShortUrl(encodedUrl);
        shortenLink.setLongUrl(fullUrl);
        urlShortenerRepo.save(shortenLink);

        return BASE_URL + encodedUrl;
    }

    private String getEncodedUrl(String fullUrl) {
        for (int i = 0; i < ATTEMPT_NUMBER; i++) {
            String encodedUrl = EncodingUtils.generateShortKey();
            Optional<ShortenLink> shortUrlDoc = urlShortenerRepo.getFirstByShortUrl(encodedUrl);
            if (shortUrlDoc.isEmpty()) {
                return encodedUrl;
            } else if (fullUrl.equals(shortUrlDoc.get().getLongUrl())) {
                return encodedUrl;
            }
        }

        return null;
    }

    public String getFullUrl(String shortUrl) {
        Optional<ShortenLink> firstByShortUrl = urlShortenerRepo.getFirstByShortUrl(shortUrl);
        return firstByShortUrl.map(ShortenLink::getLongUrl).orElseThrow(() -> new ShortUrlNotFoundException(shortUrl));
    }
}
