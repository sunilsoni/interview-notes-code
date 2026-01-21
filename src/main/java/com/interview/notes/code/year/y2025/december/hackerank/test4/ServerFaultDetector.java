package com.interview.notes.code.year.y2025.december.hackerank.test4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ServerFaultDetector {

    public static int countFaults(int n, List<String> logs) {
        var replacements = 0;
        var errorCounts = new HashMap<String, Integer>();

        for (var log : logs) {
            var parts = log.split(" ");
            var id = parts[0];
            var status = parts[1];

            if (status.equals("error")) {
                var count = errorCounts.getOrDefault(id, 0) + 1;
                if (count == 3) {
                    replacements++;
                    count = 0;
                }
                errorCounts.put(id, count);
            } else {
                errorCounts.put(id, 0);
            }
        }
        return replacements;
    }

    public static void main(String[] args) {
        System.out.println("Running Tests...");

        // Test Case 0: Provided Example
        var logs0 = List.of(
                "s1 error", "s1 error", "s2 error", "s1 error", "s1 error", "s2 success"
        );
        runTestCase(0, 2, logs0, 1);

        // Test Case 1: No errors
        var logs1 = List.of("s1 success", "s2 success");
        runTestCase(1, 2, logs1, 0);

        // Test Case 2: Exact threshold trigger
        var logs2 = List.of("s1 error", "s1 error", "s1 error");
        runTestCase(2, 1, logs2, 1);

        // Test Case 3: Mixed reset
        var logs3 = List.of("s1 error", "s1 error", "s1 success", "s1 error", "s1 error", "s1 error");
        runTestCase(3, 1, logs3, 1);

        // Test Case 4: Large Data
        var largeLogs = new ArrayList<String>();
        for (int i = 0; i < 3000; i++) {
            largeLogs.add("server1 error");
        }
        // 3000 errors / 3 = 1000 replacements
        runTestCase(4, 1, largeLogs, 1000);

        System.out.println("Testing Complete.");
    }

    private static void runTestCase(int id, int n, List<String> logs, int expected) {
        var start = System.nanoTime();
        var result = countFaults(n, logs);
        var end = System.nanoTime();

        var status = (result == expected) ? "PASS" : "FAIL";
        var logSize = logs.size();

        System.out.printf("Case %d: [%s] N=%d, Logs=%d -> Expected: %d, Got: %d (Time: %.3f ms)%n",
                id, status, n, logSize, expected, result, (end - start) / 1e6);
    }
}