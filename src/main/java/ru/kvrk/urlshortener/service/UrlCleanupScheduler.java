package ru.kvrk.urlshortener.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlCleanupScheduler {

    private final LinkCleanupService linkCleanupService;

    @Scheduled(fixedDelay = 1000L * 60 * 10) // 1 hour
    public void removeOldLinks() {
        long totalDeleted = 0;
        while (totalDeleted < 10_000) {
            int batchDeleted = linkCleanupService.deleteOldDocumentsInBatches();
            if (batchDeleted == 0) break;
            totalDeleted += batchDeleted;
        }

        log.info("Total deleted: {}", totalDeleted);
    }
}
