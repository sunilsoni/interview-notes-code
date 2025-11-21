package com.interview.notes.code.year.y2024.april24.test7;

import java.util.LinkedList;
import java.util.Queue;

public class IDCacheManager {
    private static final int CACHE_SIZE = 100;  // Optimal size based on performance testing
    private static final Queue<Integer> cache = new LinkedList<>();

    /**
     * Refills the cache by fetching IDs in bulk from the external library.
     */
    private static synchronized void refillCache() {
        if (cache.size() < CACHE_SIZE / 2) {  // Refill cache when it's half empty
            int[] newIds = new int[CACHE_SIZE];
            // ExternalLibrary.getIds(newIds, CACHE_SIZE);
            for (int id : newIds) {
                cache.add(id);
            }
        }
    }

    /**
     * Fetches a single ID, using cached IDs for better performance.
     *
     * @return a unique ID
     */
    public static synchronized int getOneId() {
        if (cache.isEmpty()) {
            refillCache();
        }
        return cache.poll();
    }

    public static void main(String[] args) {
        // Example usage
        int uniqueId = getOneId();
        System.out.println("Generated Unique ID: " + uniqueId);
    }
}
