package com.interview.notes.code.year.y2026.march.apple.tesr3;

import java.util.*;

public class ExclusiveTimeProfiler {

    public static int[] calculateExclusiveTime(int n, List<String> logs) {
        int[] res = new int[n]; // Array stores final execution times per function ID
        Deque<Integer> stack = new ArrayDeque<>(); // Stack tracks the active function (LIFO)
        int prevTime = 0; // Tracks the start of the current time block

        // Stream parses strings into structured Log objects to minimize code lines
        var parsedLogs = logs.stream()
            .map(s -> s.split(":")) // Split by colon to extract ID, type, and time
            .map(p -> new Log(Integer.parseInt(p[0]), "start".equals(p[1]), Integer.parseInt(p[2]))) // Map to Record
            .toList(); // Collect into an modern unmodifiable List

        for (var log : parsedLogs) { // Iterate through each log chronologically
            if (log.isStart()) { // If the current event is a function starting
                if (!stack.isEmpty()) { // If another function is already running
                    res[stack.peek()] += log.time() - prevTime; // Add elapsed time (exclusive of current start second)
                } // End active check

                stack.push(log.id()); // Push new function ID to make it active
                prevTime = log.time(); // Update prevTime to the start of this new interval

            } else { // If it's an end event (Inclusive logic applies here)
                res[stack.pop()] += log.time() - prevTime + 1; // Add elapsed time PLUS the current ending second (+1)
                prevTime = log.time() + 1; // Shift prevTime to the start of the NEXT second
            } // End start/end check
        } // End log loop

        return res; // Return the fully calculated times
    }

    // ---------------------------------------------------------
    // Simple Main Method for Testing (No JUnit utilized)
    // ---------------------------------------------------------
    public static void main(String[] args) {
        System.out.println("--- Testing with INCLUSIVE Logic ---\n");

        // Test Case 1: Standard Inclusive Intervals
        // 0:start:0 to 0:end:2 -> (2 - 0) + 1 = 3 units
        List<String> logs1 = List.of("0:start:0", "1:start:2", "1:end:5", "0:end:6");
        runTest("Test Case 1 (Standard Inclusive)", 2, logs1, new int[]{3, 4});

        // Test Case 2: Deeply Nested Calls
        List<String> logs2 = List.of("0:start:0", "1:start:2", "2:start:3", "2:end:4", "1:end:5", "0:end:6");
        runTest("Test Case 2 (Nested Calls)", 3, logs2, new int[]{2, 3, 2});

        // Test Case 3: Large Data Stress Test
        int largeN = 1;
        int ops = 100_000;
        List<String> largeLogs = new ArrayList<>(2);
        largeLogs.add("0:start:0");
        largeLogs.add("0:end:" + (ops - 1));
        // Operations - 1 because inclusive adds +1 back.
        runTest("Test Case 3 (Large Data Input)", largeN, largeLogs, new int[]{ops});
    }

    // Helper method to keep testing clean without external libraries
    private static void runTest(String name, int n, List<String> logs, int[] expected) {
        try {
            int[] actual = calculateExclusiveTime(n, logs);
            if (Arrays.equals(actual, expected)) {
                System.out.println("✅ PASS: " + name + " -> " + Arrays.toString(actual));
            } else {
                System.out.println("❌ FAIL: " + name);
                System.out.println("   Expected: " + Arrays.toString(expected));
                System.out.println("   Got:      " + Arrays.toString(actual));
            }
        } catch (Exception e) {
             System.out.println("❌ FAIL: " + name + " (Exception thrown: " + e.getMessage() + ")");
        }
    }

    // Record securely holds parsed log data cleanly and concisely without boilerplate
    record Log(int id, boolean isStart, int time) {}
}