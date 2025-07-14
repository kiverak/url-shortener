package ru.kvrk.urlshortener.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.kvrk.urlshortener.model.ShortenLink;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UrlShortenerRepo extends MongoRepository<ShortenLink, UUID> {

    Optional<ShortenLink> getFirstByShortUrl(String shortUrl);
}
