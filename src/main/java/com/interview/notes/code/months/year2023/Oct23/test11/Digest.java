package com.interview.notes.code.months.year2023.Oct23.test11;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentHashMap;

abstract class Digest {

    private final int CACHE_MAX_SIZE = 1000; // For illustration purposes
    private final ConcurrentHashMap<String, byte[]> cache = new ConcurrentHashMap<>();

    public byte[] getDigestedValue(byte[] key) {
        String keyStr = toHexString(key);
        return cache.computeIfAbsent(keyStr, k -> doDigesting(key));
    }

    protected abstract byte[] doDigesting(byte[] key);

    // Utility function to convert byte[] to its hex string representation
    private String toHexString(byte[] bytes) {
        return new BigInteger(1, bytes).toString(16);
    }

    // Optional: A mechanism to limit the cache size
    private void ensureCacheLimit() {
        if (cache.size() > CACHE_MAX_SIZE) {
            // Here, you can implement an eviction policy, for simplicity, I'll just clear the cache
            // Better strategies might involve LRU (Least Recently Used) policies, etc.
            cache.clear();
        }
    }
}
