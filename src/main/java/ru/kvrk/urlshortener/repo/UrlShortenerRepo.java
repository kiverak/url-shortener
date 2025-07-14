package ru.kvrk.urlshortener.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.kvrk.urlshortener.model.ShortenLink;

import java.util.Optional;
import java.util.UUID;

public interface UrlShortenerRepo extends MongoRepository<ShortenLink, UUID> {

    Optional<ShortenLink> getFirstByShortUrl(String shortUrl);
}
