package ru.kvrk.urlshortener.exception;

public class ShortUrlCreationException extends RuntimeException {
    public ShortUrlCreationException(String longUrl) {
        super(String.format("Exception while creating short URL for url: %s", longUrl));
    }
}
