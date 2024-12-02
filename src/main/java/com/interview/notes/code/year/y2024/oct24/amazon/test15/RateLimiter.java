package com.interview.notes.code.year.y2024.oct24.amazon.test15;

import java.util.HashMap;
import java.util.Map;

public class RateLimiter {

    // Main solution method
    public static int getMinTime(int n, String requests, int minGap) {
        if (n == 0 || requests == null || requests.isEmpty()) {
            return 0;
        }

        // Track last occurrence time for each region
        Map<Character, Integer> lastOccurrence = new HashMap<>();
        int currentTime = 0;

        for (char request : requests.toCharArray()) {
            // Get the last time this region was processed
            Integer lastTime = lastOccurrence.get(request);

            if (lastTime == null) {
                // First occurrence of this region
                currentTime++;
            } else {
                // Check if we need to add gap
                int nextValidTime = lastTime + minGap + 1;
                currentTime = Math.max(currentTime + 1, nextValidTime);
            }

            lastOccurrence.put(request, currentTime);
        }

        return currentTime;
    }

    // Test method
    public static void runTest(int testNumber, int n, String requests, int minGap, int expected) {
        int result = getMinTime(n, requests, minGap);
        boolean passed = result == expected;
        System.out.printf("Test %d: %s (Expected: %d, Got: %d)%n",
                testNumber, passed ? "PASS" : "FAIL", expected, result);
    }

    public static void main(String[] args) {
        // Test Case 1: Sample case 0
        runTest(1, 12, "abacadaeafag", 2, 16);

        // Test Case 2: Sample case 1
        runTest(2, 6, "aaabbb", 0, 6);

        // Test Case 3: Single character
        runTest(3, 1, "a", 5, 1);

        // Test Case 4: Empty string
        runTest(4, 0, "", 1, 0);

        // Test Case 5: All same characters with gap
        runTest(5, 5, "aaaaa", 1, 9);

        // Test Case 6: Alternating characters
        runTest(6, 6, "ababab", 1, 6);

        // Test Case 7: Large gap
        runTest(7, 3, "aaa", 10, 23);

        // Test Case 8: Large input
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append((char) ('a' + i % 26));
        }
        long startTime = System.currentTimeMillis();
        int result = getMinTime(100000, largeInput.toString(), 1);
        long endTime = System.currentTimeMillis();
        System.out.printf("Test 8 (Large Input): Completed in %dms with result %d%n",
                (endTime - startTime), result);
    }
}