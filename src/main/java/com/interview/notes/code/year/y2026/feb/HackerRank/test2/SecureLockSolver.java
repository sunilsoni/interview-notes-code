package com.interview.notes.code.year.y2026.feb.HackerRank.test2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class SecureLockSolver {

    public static int decryptCodeLock(List<Integer> codeSequence, int maxValue) {
        var candidates = new HashSet<>(codeSequence);
        int searchStart = Math.max(1, maxValue - codeSequence.size() - 5);
        IntStream.rangeClosed(searchStart, maxValue).forEach(candidates::add);

        int maxLockCode = Integer.MIN_VALUE;

        for (int candidate : candidates) {
            int conflicts = 0;
            for (int num : codeSequence) {
                if (gcd(candidate, num) > 1) {
                    conflicts++;
                }
            }

            int currentScore;
            if (codeSequence.contains(candidate)) {
                int bonus = (candidate > 1) ? 1 : 0;
                currentScore = candidate - conflicts + bonus;
            } else {
                int cost = (conflicts > 0) ? conflicts : 1;
                currentScore = candidate - cost;
            }

            if (currentScore > maxLockCode) {
                maxLockCode = currentScore;
            }
        }
        return maxLockCode;
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        runTestCase(List.of(1, 2, 3), 6, 4, "Sample Case 0");
        runTestCase(List.of(2, 4, 6, 8), 8, 6, "Sample Case 1");

        var largeData = new ArrayList<Integer>();
        for (int i = 0; i < 1000; i++) {
            largeData.add(2);
        }
        runTestCase(largeData, 1000, 996, "Large Data Input");
    }

    private static void runTestCase(List<Integer> seq, int maxVal, int expected, String id) {
        long start = System.currentTimeMillis();
        int result = decryptCodeLock(seq, maxVal);
        long time = System.currentTimeMillis() - start;
        String status = (result == expected) ? "PASS" : "FAIL (Expected " + expected + ", Got " + result + ")";
        System.out.println(id + ": " + status + " [Time: " + time + "ms]");
    }
}