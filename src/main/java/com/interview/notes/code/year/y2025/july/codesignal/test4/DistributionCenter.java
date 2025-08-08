package com.interview.notes.code.year.y2025.july.codesignal.test4;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DistributionCenter {

    public static int solution(int[] centerCapacities, String[] dailyLog) {
        int n = centerCapacities.length;
        int[] remainingCap = Arrays.copyOf(centerCapacities, n);
        int[] processed = new int[n];
        boolean[] closed = new boolean[n];

        int cur = 0; // current center to try

        for (String log : dailyLog) {
            if (log.startsWith("CLOSURE")) {
                int closeIdx = Integer.parseInt(log.split(" ")[1]);
                closed[closeIdx] = true;
                remainingCap[closeIdx] = 0; // can't process any more
            } else if ("PACKAGE".equals(log)) {
                // Find next center with available capacity and not closed
                int start = cur, looped = 0;
                while ((remainingCap[cur] == 0 || closed[cur]) && looped < n) {
                    cur = (cur + 1) % n;
                    looped++;
                }
                // Assign package if available
                if (!closed[cur] && remainingCap[cur] > 0) {
                    processed[cur]++;
                    remainingCap[cur]--;
                }
                // Move pointer for next time
                cur = (cur + 1) % n;

                // If all non-closed centers have 0 capacity, reset them
                boolean needReset = true;
                for (int i = 0; i < n; i++) {
                    if (!closed[i] && remainingCap[i] > 0) {
                        needReset = false;
                        break;
                    }
                }
                if (needReset) {
                    for (int i = 0; i < n; i++) {
                        if (!closed[i]) remainingCap[i] = centerCapacities[i];
                    }
                }
            }
        }

        // Find max processed, and highest index if tie
        int maxProcessed = IntStream.of(processed).max().getAsInt();
        int answer = -1;
        for (int i = n - 1; i >= 0; i--) {
            if (processed[i] == maxProcessed) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    // Simple main method for PASS/FAIL testing
    public static void main(String[] args) {
        // Test case 1: Example from problem
        int[] c1 = {1, 2, 1, 2, 1};
        String[] log1 = {"PACKAGE", "PACKAGE", "CLOSURE 2", "PACKAGE", "CLOSURE 3", "PACKAGE", "PACKAGE"};
        int expected1 = 1;
        runTest(1, c1, log1, expected1);

        // Test case 2: Only 1 center
        int[] c2 = {3};
        String[] log2 = {"PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE"};
        int expected2 = 0;
        runTest(2, c2, log2, expected2);

        // Test case 3: All centers closed except one
        int[] c3 = {2, 3, 2};
        String[] log3 = {"CLOSURE 0", "CLOSURE 2", "PACKAGE", "PACKAGE", "PACKAGE"};
        int expected3 = 1;
        runTest(3, c3, log3, expected3);

        // Test case 4: Large data input
        int n = 100;
        int[] c4 = new int[n];
        Arrays.fill(c4, 5);
        String[] log4 = new String[1000];
        for (int i = 0; i < 1000; i++) log4[i] = "PACKAGE";
        int expected4 = n - 1; // since all process equally, pick highest index
        runTest(4, c4, log4, expected4);

        // Test case 5: Edge reset
        int[] c5 = {2, 1};
        String[] log5 = {"PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE"};
        int expected5 = 0; // [2, 2]
        runTest(5, c5, log5, expected5);

        // Test case 6: Closure before package
        int[] c6 = {1, 1, 1};
        String[] log6 = {"CLOSURE 2", "PACKAGE", "PACKAGE"};
        int expected6 = 1;
        runTest(6, c6, log6, expected6);
    }

    private static void runTest(int testNo, int[] centerCap, String[] log, int expected) {
        int actual = solution(centerCap, log);
        if (actual == expected)
            System.out.println("Test " + testNo + ": PASS");
        else
            System.out.println("Test " + testNo + ": FAIL (Expected: " + expected + ", Got: " + actual + ")");
    }
}
