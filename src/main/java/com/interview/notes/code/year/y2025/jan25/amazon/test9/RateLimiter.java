package com.interview.notes.code.year.y2025.jan25.amazon.test9;

import java.time.Instant;

public interface RateLimiter {
    void setRequestLimit(int perSecondLimit);

    boolean isRequestAllowed();
}

class TokenBucketRateLimiter implements RateLimiter {
    private int maxTokens;
    private double refillRate; // tokens per second
    private double currentTokens;
    private long lastRefillTimestamp;

    public TokenBucketRateLimiter(int perSecondLimit) {
        setRequestLimit(perSecondLimit);
        this.currentTokens = perSecondLimit;
        this.lastRefillTimestamp = Instant.now().toEpochMilli();
    }

    @Override
    public void setRequestLimit(int perSecondLimit) {
        this.maxTokens = perSecondLimit;
        this.refillRate = perSecondLimit;
    }

    @Override
    public synchronized boolean isRequestAllowed() {
        refill();
        if (currentTokens >= 1) {
            currentTokens -= 1;
            return true;
        }
        return false;
    }

    private void refill() {
        long now = Instant.now().toEpochMilli();
        double tokensToAdd = ((now - lastRefillTimestamp) / 1000.0) * refillRate;
        if (tokensToAdd > 0) {
            currentTokens = Math.min(maxTokens, currentTokens + tokensToAdd);
            lastRefillTimestamp = now;
        }
    }
}

class RateLimiterTest {
    public static void main(String[] args) {
        // Test Case 1: Basic Functionality
        RateLimiter limiter = new TokenBucketRateLimiter(5); // 5 requests per second
        boolean passed = true;
        for (int i = 0; i < 5; i++) {
            if (!limiter.isRequestAllowed()) {
                passed = false;
                break;
            }
        }
        if (passed && !limiter.isRequestAllowed()) {
            System.out.println("Test Case 1 Passed");
        } else {
            System.out.println("Test Case 1 Failed");
        }

        // Test Case 2: Burst Requests
        limiter = new TokenBucketRateLimiter(10);
        passed = true;
        for (int i = 0; i < 10; i++) {
            if (!limiter.isRequestAllowed()) {
                passed = false;
                break;
            }
        }
        // 11th request should fail
        if (passed && !limiter.isRequestAllowed()) {
            System.out.println("Test Case 2 Passed");
        } else {
            System.out.println("Test Case 2 Failed");
        }

        // Test Case 3: Refill After Delay
        limiter = new TokenBucketRateLimiter(2);
        limiter.isRequestAllowed(); // 1
        limiter.isRequestAllowed(); // 2
        // Third request should fail
        if (!limiter.isRequestAllowed()) {
            // Wait for 1 second to refill
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Now, request should be allowed
            if (limiter.isRequestAllowed()) {
                System.out.println("Test Case 3 Passed");
            } else {
                System.out.println("Test Case 3 Failed");
            }
        } else {
            System.out.println("Test Case 3 Failed");
        }

        // Test Case 4: Large Number of Requests
        limiter = new TokenBucketRateLimiter(1000); // 1000 requests per second
        passed = true;
        for (int i = 0; i < 1000; i++) {
            if (!limiter.isRequestAllowed()) {
                passed = false;
                break;
            }
        }
        if (passed && !limiter.isRequestAllowed()) {
            System.out.println("Test Case 4 Passed");
        } else {
            System.out.println("Test Case 4 Failed");
        }

        // Test Case 5: High Burst with Refill
        limiter = new TokenBucketRateLimiter(5);
        passed = true;
        for (int i = 0; i < 5; i++) {
            if (!limiter.isRequestAllowed()) {
                passed = false;
                break;
            }
        }
        // Wait half a second and make 3 requests
        if (passed) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                if (!limiter.isRequestAllowed()) {
                    passed = false;
                    break;
                }
            }
            if (passed) {
                System.out.println("Test Case 5 Passed");
            } else {
                System.out.println("Test Case 5 Failed");
            }
        } else {
            System.out.println("Test Case 5 Failed");
        }
    }
}
