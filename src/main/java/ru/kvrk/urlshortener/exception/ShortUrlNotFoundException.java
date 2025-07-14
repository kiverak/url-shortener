package ru.kvrk.urlshortener.exception;

public class ShortUrlNotFoundException extends RuntimeException {
    public ShortUrlNotFoundException(String shortKey) {
        super(String.format("Short URL with key %s not found", shortKey));
    }
}
