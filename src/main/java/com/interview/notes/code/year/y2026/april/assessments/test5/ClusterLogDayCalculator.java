package com.interview.notes.code.year.y2026.april.assessments.test5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class ClusterLogDayCalculator {

    public static int manageServerLogs(List<String> serverLogs) {
        var counts = new HashMap<Integer, TreeMap<Integer, Integer>>();
        int day = 1, prev = -1, maxDay = 1;
        
        for (var log : serverLogs) {
            int h = Integer.parseInt(log.substring(1, 3));
            int m = Integer.parseInt(log.substring(4, 6));
            int min = (h == 12 ? 0 : h) * 60 + m + (log.toLowerCase().contains("p.m.") ? 720 : 0);
            
            if (min < prev) day++;
            prev = min;
            
            counts.computeIfAbsent(min, k -> new TreeMap<>()).merge(day, 1, Integer::sum);
            maxDay = day;
        }
        
        return counts.values().stream().mapToInt(entries -> {
            int carry = 0, last = 0;
            for (var e : entries.entrySet()) {
                int d = e.getKey(), c = e.getValue();
                if (carry > 0) carry -= Math.min(carry, Math.max(0, d - last - 1) * 10);
                carry += c;
                last = d;
                carry = Math.max(0, carry - 10);
            }
            return carry > 0 ? last + (carry + 9) / 10 : 0;
        }).reduce(maxDay, Math::max);
    }

    public static void main(String[] args) {
        test(List.of(
            "[05:00 a.m.]: Server is started",
            "[05:00 a.m.]: Rescan initialized",
            "[01:13 p.m.]: Request processed",
            "[01:10 p.m.]: Request processed",
            "[11:40 p.m.]: Rescan completed"
        ), 2, "Test Case 1: Standard Output Match");

        test(List.of(
            "[09:00 a.m.]: User logged in",
            "[08:00 a.m.]: User logged in",
            "[07:00 a.m.]: User logged in"
        ), 3, "Test Case 2: New Day Wrap Around");

        List<String> large1 = new ArrayList<>();
        for (int i = 0; i < 11; i++) {
            large1.add("[05:00 a.m.]: Server1 is started");
        }
        test(large1, 2, "Test Case 3: Exactly 11 Same Minute Logs");

        List<String> largeData = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            largeData.add("[12:00 a.m.]: Bulk Request");
        }
        test(largeData, 20000, "Test Case 4: Max Constraints Large Data (200,000 logs)");
    }

    private static void test(List<String> logs, int expected, String testName) {
        long start = System.nanoTime();
        int result = manageServerLogs(logs);
        long timeMs = (System.nanoTime() - start) / 1000000;
        
        if (result == expected) {
            System.out.println("PASS | " + testName + " [" + timeMs + "ms]");
        } else {
            System.out.println("FAIL | " + testName + " - Expected: " + expected + ", Got: " + result + " [" + timeMs + "ms]");
        }
    }
}