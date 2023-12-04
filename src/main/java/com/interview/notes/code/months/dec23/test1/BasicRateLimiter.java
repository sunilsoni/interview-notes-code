package com.interview.notes.code.months.dec23.test1;// Correct option: B. Use a sliding time window.

import java.util.*;
import java.util.concurrent.*;

public class BasicRateLimiter {

    // This HashMap will hold the timestamps of the last few requests per user
    private final Map<String, Deque<Long>> accessTimes = new ConcurrentHashMap<>();

    // Constants
    private static final long TIME_LIMIT = 60000; // 60 seconds in milliseconds
    private static final int REQUEST_LIMIT = 5; // Users can make 5 requests in 60 seconds

    public boolean isAllowed(String userId) {
        long currentTime = System.currentTimeMillis();
        Deque<Long> requestTimes = accessTimes.computeIfAbsent(userId, k -> new LinkedList<>());

        // Remove timestamps outside the current time window
        while (!requestTimes.isEmpty() && currentTime - requestTimes.peek() > TIME_LIMIT) {
            requestTimes.poll();
        }

        if (requestTimes.size() < REQUEST_LIMIT) {
            requestTimes.offer(currentTime);
            return true; // request is allowed
        } else {
            return false; // rate limit exceeded
        }
    }

    public String getWeatherData(String userId) {
        if (isAllowed(userId)) {
            return "Weather data here";
        } else {
            return "Rate limit exceeded";
        }
    }

    public static void main(String[] args) {
        BasicRateLimiter rateLimiter = new BasicRateLimiter();
        System.out.println(rateLimiter.getWeatherData("User123"));
    }
}
