package com.interview.notes.code.year.y2025.jan24.test13;

import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {

    private final Map<String, WindowCounter> counters = new ConcurrentHashMap<>();

    public boolean allowRequest(String key, int maxRequests, long windowSizeInSeconds) {
        long currentTimeSeconds = Instant.now().getEpochSecond();

        return counters.compute(key, (k, v) -> {
            if (v == null || currentTimeSeconds - v.windowStart >= windowSizeInSeconds) {
                return new WindowCounter(currentTimeSeconds, 1);
            }
            if (v.count < maxRequests) {
                v.count++;
                return v;
            }
            return null;
        }) != null;
    }

    private static class WindowCounter {
        final long windowStart;
        int count;

        WindowCounter(long windowStart, int count) {
            this.windowStart = windowStart;
            this.count = count;
        }
    }
}