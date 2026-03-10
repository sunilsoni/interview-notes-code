package com.interview.notes.code.year.y2026.march.apple.test1;

import java.util.*;

public class ExclusiveTimeProfiler {

    public static int[] calculateExclusiveTime(int n, List<String> logs) {
        int[] res = new int[n]; // Array to store final execution times for each function ID
        Deque<Integer> stack = new ArrayDeque<>(); // Stack tracks the currently running function (LIFO)
        int prevTime = 0; // Tracks the timestamp of the last processed event

        // Parse strings into structured Log objects using Streams to minimize code lines
        var parsedLogs = logs.stream()
            .map(s -> s.split(":")) // Split by colon to extract ID, type, and time
            .map(p -> new Log(Integer.parseInt(p[0]), "start".equals(p[1]), Integer.parseInt(p[2]))) // Convert to Record
            .toList(); // Collect into an unmodifiable List

        for (var log : parsedLogs) { // Iterate through each log chronologically
            if (!stack.isEmpty()) { // If a function is currently on the stack, it was actively running
                res[stack.peek()] += log.time() - prevTime; // Add the exact time elapsed since the last event to the running function
            } // End of active check

            prevTime = log.time(); // Update prevTime to the current event's time for the next iteration's calculation

            if (log.isStart()) { // Check if the current event is a function starting
                stack.push(log.id()); // Push function ID to stack, making it the new active function
            } else { // If it's not a start, it must be an end event
                stack.pop(); // Remove finished function ID from stack, returning control to the parent function (if any)
            } // End of start/end check
        } // End of log loop

        return res; // Return the fully calculated execution times
    }

    public static void main(String[] args) {
        System.out.println("Starting tests...\n");

        // Test Case 1: The exact example from your screenshot
        // Expected: 0 -> 3, 1 -> 3
        List<String> logs1 = List.of("0:start:0", "1:start:2", "1:start:3", "1:end:4", "1:end:5", "0:end:6");
        runTest("Test Case 1 (Screenshot Example)", 2, logs1, new int[]{3, 3});

        // Test Case 2: Simple sequential (No overlap)
        List<String> logs2 = List.of("0:start:0", "0:end:2", "1:start:2", "1:end:5");
        runTest("Test Case 2 (Sequential)", 2, logs2, new int[]{2, 3});

        // Test Case 3: Large Data Stress Test
        int largeN = 1;
        int ops = 100_000;
        List<String> largeLogs = new ArrayList<>();
        largeLogs.add("0:start:0");
        largeLogs.add("0:end:" + ops);
        runTest("Test Case 3 (Large Data Input)", largeN, largeLogs, new int[]{ops});
    }

    // Helper method to keep testing clean and readable without JUnit
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
             System.out.println("❌ FAIL: " + name + " (Exception thrown)");
        }
    }

    // Record to hold parsed log data cleanly (Java 16+ feature)
    record Log(int id, boolean isStart, int time) {}
}