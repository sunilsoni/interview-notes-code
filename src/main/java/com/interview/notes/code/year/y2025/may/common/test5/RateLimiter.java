package com.interview.notes.code.year.y2025.may.common.test5;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    private static final int CAPACITY = 10;          // max tokens
    private static final long WINDOW_MS = 3_000L;    // 3 seconds
    private static final double REFILL_RATE = 10.0 / WINDOW_MS; // tokens per ms

    private static class Bucket {
        double tokens = CAPACITY;
        long lastRefillTime = System.currentTimeMillis();
    }

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();

    /** Returns true if the request should be processed. */
    public boolean allowRequest(String ip) {
        Bucket bucket = buckets.computeIfAbsent(ip, k -> new Bucket());
        synchronized (bucket) {                    // per-bucket lock
            long now = System.currentTimeMillis();
            long elapsed = now - bucket.lastRefillTime;

            // 1) Refill tokens
            if (elapsed > 0) {
                double added = elapsed * REFILL_RATE;
                bucket.tokens = Math.min(CAPACITY, bucket.tokens + added);
                bucket.lastRefillTime = now;
            }

            // 2) Consume one token if available
            if (bucket.tokens >= 1.0) {
                bucket.tokens -= 1.0;
                return true;                       // **allowed**
            }
            return false;                          // **blocked**
        }
    }
}
