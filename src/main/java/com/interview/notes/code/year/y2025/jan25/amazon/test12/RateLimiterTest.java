package com.interview.notes.code.year.y2025.jan25.amazon.test12;

public class RateLimiterTest {
    public static void main(String[] args) throws InterruptedException {
        testBasicLimit();
        testWindowReset();
        testDynamicLimitChange();
        testHighLoad();
        System.out.println("All tests passed!");
    }

    private static void testBasicLimit() {
        RateLimiter limiter = new FixedWindowRateLimiter(2);
        assertTrue(limiter.isRequestAllowed(), "Request 1 should pass");
        assertTrue(limiter.isRequestAllowed(), "Request 2 should pass");
        assertFalse(limiter.isRequestAllowed(), "Request 3 should fail");
    }

    private static void testWindowReset() throws InterruptedException {
        RateLimiter limiter = new FixedWindowRateLimiter(2);
        limiter.isRequestAllowed();
        limiter.isRequestAllowed();
        Thread.sleep(1000);
        assertTrue(limiter.isRequestAllowed(), "New window request should pass");
    }

    private static void testDynamicLimitChange() {
        FixedWindowRateLimiter limiter = new FixedWindowRateLimiter(5);
        limiter.setRequestLimit(3);
        int allowed = 0;
        for (int i = 0; i < 4; i++) {
            if (limiter.isRequestAllowed()) allowed++;
        }
        assertTrue(allowed == 3, "Only 3 requests should pass");
    }

    private static void testHighLoad() {
        int limit = 5000;
        RateLimiter limiter = new FixedWindowRateLimiter(limit);
        int allowed = 0;
        for (int i = 0; i < limit + 100; i++) {
            if (limiter.isRequestAllowed()) allowed++;
        }
        assertTrue(allowed == limit, "Limit should enforce " + limit);
    }

    private static void assertTrue(boolean condition, String message) {
        if (!condition) throw new AssertionError("Test failed: " + message);
    }

    private static void assertFalse(boolean condition, String message) {
        if (condition) throw new AssertionError("Test failed: " + message);
    }
}