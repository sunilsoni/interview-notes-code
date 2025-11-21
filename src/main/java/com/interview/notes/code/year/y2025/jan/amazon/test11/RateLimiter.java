package com.interview.notes.code.year.y2025.jan.amazon.test11;

import java.time.Instant;
import java.util.LinkedList;
import java.util.Queue;

/*
Design Client Side rate Limiter

 How would you design and implement a client-side rate limiter for security cameras to ensure that command and control traffic does not interfere with video streaming, while maintaining fair use of limited device resources and bandwidth?
 */
public interface RateLimiter {
    void setRequestLimit(int perSecondLimit);

    boolean isRequestAllowed();
}

class SlidingWindowRateLimiter implements RateLimiter {
    private final long windowSizeInMillis = 1000; // 1 second
    private int requestLimit;
    private final Queue<Long> requestTimestamps;

    public SlidingWindowRateLimiter(int perSecondLimit) {
        setRequestLimit(perSecondLimit);
        this.requestTimestamps = new LinkedList<>();
    }

    @Override
    public synchronized void setRequestLimit(int perSecondLimit) {
        if (perSecondLimit <= 0) {
            throw new IllegalArgumentException("Request limit must be positive.");
        }
        this.requestLimit = perSecondLimit;
    }

    @Override
    public synchronized boolean isRequestAllowed() {
        long currentTime = Instant.now().toEpochMilli();
        long windowStart = currentTime - windowSizeInMillis;

        // Remove outdated requests
        while (!requestTimestamps.isEmpty() && requestTimestamps.peek() < windowStart) {
            requestTimestamps.poll();
        }

        if (requestTimestamps.size() < requestLimit) {
            requestTimestamps.add(currentTime);
            return true;
        }

        return false;
    }
}
