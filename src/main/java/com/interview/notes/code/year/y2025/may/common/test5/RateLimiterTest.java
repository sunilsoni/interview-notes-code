package com.interview.notes.code.year.y2025.may.common.test5;

public class RateLimiterTest {
    public static void main(String[] args) throws InterruptedException {
        RateLimiter limiter = new RateLimiter();
        String ip = "127.0.0.1";

        int passed = 0, failed = 0;

        // 10 quick requests → should PASS
        for (int i = 1; i <= 10; i++) {
            if (limiter.allowRequest(ip)) passed++;
            else failed++;
        }

        // 11-th inside same 3 s → should FAIL
        if (limiter.allowRequest(ip)) failed++; else passed++;

        // Wait 3 s, bucket refills
        Thread.sleep(3_100);

        // Next request should PASS again
        if (limiter.allowRequest(ip)) passed++; else failed++;

        System.out.println("PASS: " + passed + "  FAIL: " + failed);
    }
}
