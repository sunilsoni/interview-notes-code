package com.interview.notes.code.months.year2023.Aug23.test3;

import java.util.HashMap;
import java.util.Map;

/**
 * In Java
 * <p>
 * <p>
 * ImpLement a rate-Limiting aLgorithm that can be used to restrict the number of caLLs to a web service, using either fixed window counter
 * or sLiding window counter approach
 * Given a function signature with the foLLowing parameters: unique key string, time period in seconds, and a maximum number of caLLs per
 * period, impLement an aLgorithm that determines if more caLLs can or cannot be be made within the current time period
 */
public class RateLimiterFixedWindow1 {
    private Map<String, Integer> counter = new HashMap<>();
    private Map<String, Long> timeStamp = new HashMap<>();
    private int timeWindowInSeconds;

    public RateLimiterFixedWindow1(int timeWindowInSeconds) {
        this.timeWindowInSeconds = timeWindowInSeconds;
    }

    public static void main(String[] args) {
        RateLimiterFixedWindow1 rateLimiter = new RateLimiterFixedWindow1(60);

        // Test with sample keys and thresholds
        System.out.println(rateLimiter.isAllowed("user1", 5));  // Should return true
        System.out.println(rateLimiter.isAllowed("user1", 5));  // Should return true
        System.out.println(rateLimiter.isAllowed("user1", 5));  // Should return true
        System.out.println(rateLimiter.isAllowed("user1", 5));  // Should return true
        System.out.println(rateLimiter.isAllowed("user1", 5));  // Should return true
        System.out.println(rateLimiter.isAllowed("user1", 5));  // Should return false
    }

    public synchronized boolean isAllowed(String uniqueKey, int maxCalls) {
        long currentTime = System.currentTimeMillis() / 1000;

        // If the key has not been used before, initialize counter and timestamp
        if (!counter.containsKey(uniqueKey)) {
            counter.put(uniqueKey, 1);
            timeStamp.put(uniqueKey, currentTime);
            return true;
        }

        // Reset the counter if the time has passed
        if (currentTime - timeStamp.get(uniqueKey) >= timeWindowInSeconds) {
            counter.put(uniqueKey, 1);
            timeStamp.put(uniqueKey, currentTime);
            return true;
        }

        // Check if max calls are exceeded for the key
        if (counter.get(uniqueKey) < maxCalls) {
            counter.put(uniqueKey, counter.get(uniqueKey) + 1);
            return true;
        }

        return false;
    }
}
