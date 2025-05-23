package com.interview.notes.code.year.y2025.may.common.test1;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class RateLimiter {

    private final Map<String, Deque<Long>> requestTimes;
    private final int maxRequests;
    private final int windowMillis;

    public RateLimiter(int maxRequests, int windowSeconds) {
        this.requestTimes = new ConcurrentHashMap<>();
        this.maxRequests = maxRequests;
        this.windowMillis = windowSeconds * 1000;
    }

    // Simple main method for testing
    public static void main(String[] args) throws InterruptedException {
        RateLimiter rateLimiter = new RateLimiter(10, 3); // 10 requests per 3 seconds per IP

        String testIp = "192.168.0.1";

        // Test Case 1: Allow exactly 10 requests
        boolean allPassed = true;
        for (int i = 0; i < 10; i++) {
            boolean allowed = rateLimiter.allowRequest(testIp);
            if (!allowed) allPassed = false;
        }
        System.out.println("Test Case 1: " + (allPassed ? "PASS" : "FAIL"));

        // Test Case 2: 11th request should fail
        boolean allowed = rateLimiter.allowRequest(testIp);
        System.out.println("Test Case 2: " + (!allowed ? "PASS" : "FAIL"));

        // Test Case 3: Wait 3 seconds, request should pass again
        Thread.sleep(3000);
        allowed = rateLimiter.allowRequest(testIp);
        System.out.println("Test Case 3: " + (allowed ? "PASS" : "FAIL"));

        // Edge Case: Large data input
        boolean largeDataTestPassed = true;
        String ip;
        for (int i = 0; i < 100000; i++) {
            ip = "192.168.1." + i;
            if (!rateLimiter.allowRequest(ip)) {
                largeDataTestPassed = false;
                break;
            }
        }
        System.out.println("Edge Case Large Data Test: " + (largeDataTestPassed ? "PASS" : "FAIL"));
    }

    public boolean allowRequest(String ip) {
        long currentTime = System.currentTimeMillis();
        requestTimes.putIfAbsent(ip, new ConcurrentLinkedDeque<>());
        Deque<Long> timestamps = requestTimes.get(ip);

        synchronized (timestamps) {
            while (!timestamps.isEmpty() && timestamps.peekFirst() <= currentTime - windowMillis) {
                timestamps.pollFirst();
            }

            if (timestamps.size() < maxRequests) {
                timestamps.addLast(currentTime);
                return true;
            } else {
                return false;
            }
        }
    }
}
