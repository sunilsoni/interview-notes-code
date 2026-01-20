package com.interview.notes.code.year.y2026.jan.hackerrank.test1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

public class FaultyServerMonitor {

    public static int countFaults(int n, List<String> logs) {
        var errorCounts = new HashMap<String, Integer>();
        int replacements = 0;

        for (var log : logs) {
            var parts = log.split(" ");
            var serverId = parts[0];
            var status = parts[1];

            if ("error".equals(status)) {
                int count = errorCounts.getOrDefault(serverId, 0) + 1;
                if (count == 3) {
                    replacements++;
                    count = 0;
                }
                errorCounts.put(serverId, count);
            } else {
                errorCounts.put(serverId, 0);
            }
        }
        return replacements;
    }

    public static void main(String[] args) {
        runTestCase(1, 2, List.of(
            "s1 error", "s1 error", "s2 error", "s1 error", "s1 error", "s2 success"
        ), 1);

        runTestCase(2, 5, List.of(
            "s1 error", "s1 error", "s1 error", "s1 error", "s1 error", "s1 error"
        ), 2);

        runTestCase(3, 3, List.of(
            "s1 error", "s1 error", "s1 success", "s1 error", "s1 error", "s1 error"
        ), 1);

        runTestCase(4, 3, List.of(
            "s1 success", "s2 success", "s3 success"
        ), 0);

        List<String> largeLog = new ArrayList<>();
        IntStream.range(0, 10000).forEach(i -> largeLog.add("s1 error"));
        int expectedLarge = 10000 / 3;
        runTestCase(5, 1, largeLog, expectedLarge);
    }

    private static void runTestCase(int id, int n, List<String> logs, int expected) {
        long startTime = System.nanoTime();
        int result = countFaults(n, logs);
        long endTime = System.nanoTime();
        
        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("Test Case %d: %s (Expected: %d, Got: %d) - Time: %.3f ms%n", 
            id, status, expected, result, (endTime - startTime) / 1e6);
    }
}