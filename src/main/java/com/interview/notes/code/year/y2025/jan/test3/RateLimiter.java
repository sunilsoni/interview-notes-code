package com.interview.notes.code.year.y2025.jan.test3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.concurrent.ConcurrentHashMap;

public class RateLimiter {
    // Define the request limit and time window (in milliseconds)
    private static final int MAX_REQUESTS_PER_MINUTE = 5;
    private static final long ONE_MINUTE_IN_MILLIS = 60 * 1000L;

    // Map to store userId -> list of request timestamps
    private final ConcurrentHashMap<String, Deque<Long>> userRequestLog = new ConcurrentHashMap<>();

    /**
     * Call this method for each incoming request.
     *
     * @param userId The identifier of the user making the request.
     * @return true if the request is allowed; false if it should be rate-limited.
     */
    public boolean allowRequest(String userId) {
        long currentTime = System.currentTimeMillis();

        // Get or create a request log for the user.
        Deque<Long> requestTimestamps = userRequestLog.computeIfAbsent(userId, k -> new ArrayDeque<>());

        synchronized (requestTimestamps) {
            // Remove timestamps older than one minute.
            while (!requestTimestamps.isEmpty() && (currentTime - requestTimestamps.peekFirst() > ONE_MINUTE_IN_MILLIS)) {
                requestTimestamps.pollFirst();
            }

            // Check if the user has made fewer than MAX_REQUESTS_PER_MINUTE within the last minute.
            if (requestTimestamps.size() < MAX_REQUESTS_PER_MINUTE) {
                // Log this request timestamp and allow the request.
                requestTimestamps.offerLast(currentTime);
                return true;
            } else {
                // Too many requests: reject the request.
                return false;
            }
        }
    }
}
