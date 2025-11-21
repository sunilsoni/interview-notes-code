package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncCache<K, V> {
    private final ConcurrentHashMap<K, CompletableFuture<V>> cache = new ConcurrentHashMap<>();
    private final Executor executor = Executors.newCachedThreadPool();

    // Simulate expensive DB call
    private V fetchFromDb(K key) {
        try {
            Thread.sleep(1000); // simulate delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return (V) ("Value for " + key); // replace with actual DB logic
    }

    public CompletableFuture<V> get(K key) {
        return cache.computeIfAbsent(key, k ->
                CompletableFuture.supplyAsync(() -> fetchFromDb(k), executor)
        );
    }
}
