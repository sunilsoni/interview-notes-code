package com.interview.notes.code.year.y2026.april.assessments.test6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class OptimalClusterLogProcessor {

    public static int manageServerLogs(List<String> serverLogs) {
        var counts = new HashMap<Integer, TreeMap<Integer, Integer>>();
        int day = 1, prev = -1, maxDay = 1;
        
        for (var log : serverLogs) {
            int c = log.indexOf(':'), s = log.indexOf(' ', c);
            int h = Integer.parseInt(log.substring(1, c));
            int m = Integer.parseInt(log.substring(c + 1, s));
            char p = log.charAt(s + 1);
            int min = (h == 12 ? 0 : h) * 60 + m + ((p == 'p' || p == 'P') ? 720 : 0);
            
            if (min < prev) day++;
            prev = min;
            
            counts.computeIfAbsent(min, k -> new TreeMap<>()).merge(day, 1, Integer::sum);
            maxDay = day;
        }
        
        return counts.values().stream().mapToInt(entries -> {
            int carry = 0, last = 0;
            for (var e : entries.entrySet()) {
                int d = e.getKey(), v = e.getValue();
                if (carry > 0) carry -= Math.min(carry, Math.max(0, d - last - 1) * 10);
                carry += v;
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
        ), 2, "Test Case 1: Standard Progression");

        test(List.of(
            "[09:00 a.m.]: User logged in",
            "[08:00 a.m.]: User logged in",
            "[07:00 a.m.]: User logged in"
        ), 3, "Test Case 2: Daily Wrap Around");

        List<String> large1 = new ArrayList<>();
        for (int i = 0; i < 11; i++) large1.add("[05:00 a.m.]: Server1 is started");
        test(large1, 2, "Test Case 3: Overflow Shift Next Day");

        List<String> mockDump = List.of(
            "[03:21 a.m.]: xiuntoizrl KCUYC",
            "[12:55 p.m.]: emmstuef TWKZALJLH",
            "[12:55 p.m.]: emmstuef TWKZALJLH",
            "[05:53 p.m.]: fgzaojc UHITISY",
            "[05:53 p.m.]: fgzaojc UHITISY",
            "[07:11 p.m.]: qkftm EUYLLE",
            "[12:24 p.m.]: wgfhckx NSQTZWXPGA",
            "[06:40 a.m.]: ocsxqukmr XWLGBIQRUJ"
        );
        test(mockDump, 3, "Test Case 4: Random Log Payload Resistance");

        List<String> largeData = new ArrayList<>(200000);
        for (int i = 0; i < 200000; i++) largeData.add("[12:00 a.m.]: Bulk Request payload with p.m. trap");
        test(largeData, 20000, "Test Case 5: Max Constraints 200k Logs");
    }

    private static void test(List<String> logs, int expected, String testName) {
        long start = System.nanoTime();
        int result = manageServerLogs(logs);
        long timeMs = (System.nanoTime() - start) / 1000000;
        System.out.println((result == expected ? "PASS" : "FAIL") + " | " + testName + " - Exp: " + expected + ", Got: " + result + " [" + timeMs + "ms]");
    }
}