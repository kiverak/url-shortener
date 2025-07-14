package ru.kvrk.urlshortener.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.UUID;

@Document(collection = "shorten_link")
@Getter
@Setter
public class ShortenLink extends BaseDocument {

    @MongoId
    @Field("id")
    private UUID id = UUID.randomUUID();

    @Indexed(name = "unique_short_url_idx", unique = true)
    @Field("short_url")
    private String shortUrl;

    @Size(max = 2048, message = "Full URL length must be less than or equal to 2048 characters")
    @NotBlank
    @Field("long_url")
    private String longUrl;
}