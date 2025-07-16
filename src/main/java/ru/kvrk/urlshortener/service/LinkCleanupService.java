package ru.kvrk.urlshortener.service;

import com.mongodb.client.result.DeleteResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kvrk.urlshortener.model.ShortenLink;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class LinkCleanupService {

    @Value("${max.link.age}")
    private int maxLinkAge;

    private MongoTemplate mongoTemplate;

//    @Transactional
    public int deleteOldDocumentsInBatches() {
        Instant threshold = Instant.now().minus(maxLinkAge, ChronoUnit.DAYS);
        Query query = Query.query(Criteria.where("createdAt").lt(threshold));

        query.limit(100);

        List<ShortenLink> batch = mongoTemplate.find(query, ShortenLink.class, "shorten_links");
        if (batch.isEmpty()) return 0;

        List<UUID> idsToDelete = batch.stream()
                .map(ShortenLink::getId)
                .toList();

        Query deleteQuery = Query.query(Criteria.where("_id").in(idsToDelete));
        DeleteResult result = mongoTemplate.remove(deleteQuery, "shorten_links");

        return (int) result.getDeletedCount();
    }
}
