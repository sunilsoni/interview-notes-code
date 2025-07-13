package com.interview.notes.code.year.y2025.july.codesignal.test2;

import java.util.*;
import java.util.stream.*;

public class ResourceExchangeTest {

    // Core solution: tracks counts instead of manipulating arrays
    public static int solution(String[] resources, int conversionRate) {
        int aCount = 0, pCount = 0;
        for (String r : resources) {
            if ("A".equals(r)) aCount++;
            else if ("P".equals(r)) pCount++;
        }
        int cycles = 0;
        while (true) {
            if (pCount >= conversionRate) {
                pCount -= conversionRate;
                aCount += 1;
            } else if (aCount >= 1) {
                aCount -= 1;
                pCount += 1;
            } else {
                break;
            }
            cycles++;
        }
        return cycles;
    }

    // Simple test runner: each test is an int[]{conversionRate, expected, ...resource array...}
    public static void main(String[] args) {
        List<TestCase> tests = Arrays.asList(
            new TestCase(new String[]{"A","A","A","P","P","P"}, 2, 13),
            new TestCase(new String[]{"A","A"},           2, 4),
            new TestCase(new String[]{"P","P","P"},       3, 2)
        );

        // Run sample tests
        System.out.println("=== Sample Tests ===");
        tests.stream().forEach(tc -> {
            int actual = solution(tc.resources, tc.conversionRate);
            String result = (actual == tc.expected) ? "PASS" : "FAIL";
            System.out.printf("%s | rate=%d, expected=%d, got=%d%n",
                              result, tc.conversionRate, tc.expected, actual);
        });

        // Large random test at the stated limits
        System.out.println("\n=== Large Random Test ===");
        int N = 500, rate = 250;
        String[] large = new String[N];
        Random rnd = new Random(12345);
        // fill half A's then half P's (to respect ordering)
        int half = N/2;
        for (int i = 0; i < half; i++) large[i] = "A";
        for (int i = half; i < N; i++) large[i] = "P";

        long start = System.currentTimeMillis();
        int cycles = solution(large, rate);
        long elapsed = System.currentTimeMillis() - start;
        System.out.printf("Large case: N=%d, rate=%d → cycles=%d  (took %d ms)%n",
                          N, rate, cycles, elapsed);
    }

    // Helper to bundle a test
    static class TestCase {
        String[] resources;
        int conversionRate, expected;
        TestCase(String[] r, int rate, int exp) {
            this.resources = r;
            this.conversionRate = rate;
            this.expected = exp;
        }
    }
}
