package com.interview.notes.code.year.y2026.feb.HackerRank.test2;

import java.util.*;
import java.util.stream.IntStream;

public class CodeLockSolver {

    public static int decryptCodeLock(List<Integer> codeSequence, int maxValue) {
        int n = codeSequence.size();
        // Search range: existing numbers + high values near maxValue
        Set<Integer> candidates = new HashSet<>(codeSequence);
        int searchStart = Math.max(1, maxValue - n - 50);
        IntStream.rangeClosed(searchStart, maxValue).forEach(candidates::add);

        int maxLockCode = Integer.MIN_VALUE;

        for (int cand : candidates) {
            if (cand > maxValue) continue;

            int conflicts = 0;
            for (int num : codeSequence) {
                if (gcd(cand, num) > 1) {
                    conflicts++;
                }
            }

            int cost;
            if (codeSequence.contains(cand)) {
                // If candidate is 1, gcd(1,1) is 1, so conflicts=0. Cost should be 0.
                // If candidate > 1, it conflicts with itself in the loop, so subtract 1.
                cost = (cand == 1) ? 0 : conflicts - 1;
            } else {
                // If not in list, we must replace at least one number (cost 1).
                // If there are conflicts, we replace those specific numbers (cost = conflicts).
                cost = (conflicts == 0) ? 1 : conflicts;
            }

            maxLockCode = Math.max(maxLockCode, cand - cost);
        }
        return maxLockCode;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        // Sample Case 0
        runTest(List.of(1, 2, 3), 6, 4, "Sample 0");

        // Sample Case 1
        runTest(List.of(2, 4, 6, 8), 8, 6, "Sample 1");

        // Edge Case: Single element 1
        runTest(List.of(1), 1, 1, "Edge Case [1]");

        // Large Data Case
        // Array of 1000 2's. MaxValue 1000.
        // Optimal: Select 999 (odd, coprime to 2). Cost 1 (replace one 2). Score 999 - 1 = 998.
        List<Integer> largeData = new ArrayList<>(Collections.nCopies(1000, 2));
        runTest(largeData, 1000, 998, "Large Data");
    }

    private static void runTest(List<Integer> seq, int max, int expected, String testName) {
        long start = System.currentTimeMillis();
        int result = decryptCodeLock(seq, max);
        long end = System.currentTimeMillis();
        String status = (result == expected) ? "PASS" : "FAIL (Exp " + expected + ", Got " + result + ")";
        System.out.printf("%-15s: %s [%dms]%n", testName, status, (end - start));
    }
}