package com.interview.notes.code.year.y2024.april24.test7;

import java.util.LinkedList;
import java.util.Queue;

public class OptimizedUniqueIdGenerator {
    private static final int CACHE_SIZE = 100;
    private static Queue<Integer> idCache = new LinkedList<>();

    /**
     * Method to populate the cache with unique IDs in bulk.
     */
    private static void refillCache() {
        int[] ids = new int[CACHE_SIZE];
        UniqueIdGenerator.getIds(ids, CACHE_SIZE);
        for (int id : ids) {
            idCache.add(id);
        }
    }

    /**
     * Optimized method to get a single unique ID, using a caching mechanism.
     *
     * @return A single unique ID, fetched from cache for performance.
     */
    public static int getOneId() {
        if (idCache.isEmpty()) {
            refillCache();
        }
        return idCache.poll();
    }

    public static void main(String[] args) {
        int uniqueId = getOneId();  // Get a unique ID from the cache
        System.out.println("Generated Unique ID: " + uniqueId);  // Print the unique ID
    }
}
