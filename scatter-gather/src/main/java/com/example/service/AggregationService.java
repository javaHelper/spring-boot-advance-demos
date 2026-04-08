package com.example.service;

import com.example.model.UserData;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Service
public class AggregationService {
    private final ExternalService externalService;
    private final Executor executor;

    public AggregationService(ExternalService externalService,
                              @Qualifier("taskExecutor") Executor executor) {
        this.externalService = externalService;
        this.executor = executor;
    }

    public UserData getAggregatedData(){
        CompletableFuture<String> userFuture = CompletableFuture.supplyAsync(externalService::getUserInfo, executor)
                .orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(throwable -> "User Info Unavailable");

        CompletableFuture<String> ordersFuture = CompletableFuture.supplyAsync(externalService::getOrders, executor)
                .orTimeout(2, TimeUnit.SECONDS)
                .exceptionally(throwable -> "Orders Unavailable");

        CompletableFuture<String> recommendationsFuture = CompletableFuture.supplyAsync(externalService::getRecommendations, executor)
                        .orTimeout(2, TimeUnit.SECONDS)
                        .exceptionally(ex -> "Recommendations Unavailable");

        // Gather phase
        CompletableFuture<Void> allFutures =
                CompletableFuture.allOf(userFuture, ordersFuture, recommendationsFuture);

        return allFutures.thenApply(v -> {
            try {
                return new UserData(userFuture.get(), ordersFuture.get(), recommendationsFuture.get());
            } catch (Exception e) {
                throw new RuntimeException("Aggregation failed", e);
            }
        }).join();
    }
}
