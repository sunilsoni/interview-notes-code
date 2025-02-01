package com.interview.notes.code.year.y2025.jan25.amazon.test12;

public interface RateLimiter {
    void setRequestLimit(int perSecondLimit);

    boolean isRequestAllowed();
}

class FixedWindowRateLimiter implements RateLimiter {
    private final Object lock = new Object();
    private int maxRequestsPerSecond;
    private long windowStartMillis;
    private int requestCount;

    public FixedWindowRateLimiter(int limit) {
        setRequestLimit(limit);
        windowStartMillis = System.currentTimeMillis();
        requestCount = 0;
    }

    @Override
    public void setRequestLimit(int perSecondLimit) {
        synchronized (lock) {
            maxRequestsPerSecond = perSecondLimit;
        }
    }

    @Override
    public boolean isRequestAllowed() {
        synchronized (lock) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - windowStartMillis >= 1000) {
                windowStartMillis = currentTime;
                requestCount = 0;
            }
            if (requestCount < maxRequestsPerSecond) {
                requestCount++;
                return true;
            }
            return false;
        }
    }
}