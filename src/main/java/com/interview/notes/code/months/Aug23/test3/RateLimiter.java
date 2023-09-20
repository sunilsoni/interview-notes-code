package com.interview.notes.code.months.Aug23.test3;

import java.util.HashMap;
import java.util.Map;

/**
 * ImpLement a rate-Limiting aLgorithm that can be used to restrict the number of caLLs to a web service, using either fixed window counter
 * or sLiding window counter approach
 * Given a function signature with the foLLowing parameters: unique key string, time period in seconds, and a maximum number of caLLs per
 * period, impLement an aLgorithm that determines if more caLLs can or cannot be be made within the current time period
 */
public class RateLimiter {
    private Map<String, Integer> counter = new HashMap<>();  // Counter for each key
    private Map<String, Long> timeStamp = new HashMap<>();   // Start timestamp of window for each key

    public static void main(String[] args) {
        RateLimiter rateLimiter = new RateLimiter();
        System.out.println(rateLimiter.isAllowed("user1", 60, 5));  // Should return true
        System.out.println(rateLimiter.isAllowed("user1", 60, 5));  // Should return true
        // ... Perform more tests


    }

    public synchronized boolean isAllowed(String key, int intervalInSecs, int maxLimit) {
        long currentTime = System.currentTimeMillis() / 1000;  // Current time in seconds

        // If this key is new, then insert it into the counter and timeStamp with initial values.
        if (!counter.containsKey(key)) {
            counter.put(key, 1);
            timeStamp.put(key, currentTime);
            return true;
        }

        // If the current request is outside the current window, then reset the window and counter.
        if (currentTime - timeStamp.get(key) >= intervalInSecs) {
            counter.put(key, 1);
            timeStamp.put(key, currentTime);
            return true;
        }

        // If the request is within the current window, then check the limit.
        if (counter.get(key) < maxLimit) {
            counter.put(key, counter.get(key) + 1);
            return true;
        }

        return false;
    }
}
