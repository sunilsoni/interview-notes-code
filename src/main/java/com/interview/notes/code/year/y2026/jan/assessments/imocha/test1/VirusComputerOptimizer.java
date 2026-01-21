package com.interview.notes.code.year.y2026.jan.assessments.imocha.test1;

import java.util.Arrays;
import java.util.HashMap;

public class VirusComputerOptimizer {

    public static int virusResolving(int N, int X, int Y, int[] damages) {
        if (X >= 2 * Y) {
            return N * Y;
        }

        var frequencyMap = new HashMap<Integer, Integer>();
        for (var damage : damages) {
            frequencyMap.merge(damage, 1, Integer::sum);
        }

        int maxFreq = 0;
        for (var count : frequencyMap.values()) {
            if (count > maxFreq) {
                maxFreq = count;
            }
        }

        int pairs = (maxFreq > N / 2) ? (N - maxFreq) : (N / 2);
        return (pairs * X) + ((N - 2 * pairs) * Y);
    }

    public static void main(String[] args) {
        System.out.println("--- Running Virus Resolution Tests ---");

        // Test Case 1: Sample Input
        // 1, 3, 1, 4, 3 -> Freqs: {1:2, 3:2, 4:1}. MaxFreq=2.
        // Pairs=2. Cost = 2*2 + 1*3 = 7.
        runTest("Sample Case", 5, 2, 3, new int[]{1, 3, 1, 4, 3}, 7);

        // Test Case 2: Dominant Virus (Cannot pair all)
        // 1, 1, 1, 1, 3 -> Freqs: {1:4, 3:1}. MaxFreq=4.
        // Pairs = 5-4=1. Singles=3. Cost = 1*2 + 3*3 = 11.
        runTest("Dominant Virus", 5, 2, 3, new int[]{1, 1, 1, 1, 3}, 11);

        // Test Case 3: Distinct Viruses (Max pairing)
        // 1, 2, 3, 4, 5 -> MaxFreq=1. Pairs=2. Single=1.
        // Cost = 2*2 + 1*3 = 7.
        runTest("All Distinct", 5, 2, 3, new int[]{1, 2, 3, 4, 5}, 7);

        // Test Case 4: X is expensive (Prefer Y)
        // X=10, Y=2. 2Y = 4. X > 2Y. Just do N*Y = 3*2 = 6.
        runTest("Expensive Pair", 3, 10, 2, new int[]{1, 2, 3}, 6);

        // Test Case 5: Large Data Input (Performance Check)
        // 50,000 items, all same value. MaxFreq=50000. Pairs=0.
        // Cost = 50000 * 3 = 150000.
        var largeData = new int[50000];
        Arrays.fill(largeData, 1);
        runTest("Large Data (Uniform)", 50000, 2, 3, largeData, 150000);

        System.out.println("--- All Tests Completed ---");
    }

    private static void runTest(String testName, int N, int X, int Y, int[] damages, int expected) {
        long startTime = System.currentTimeMillis();
        int actual = virusResolving(N, X, Y, damages);
        long endTime = System.currentTimeMillis();

        String status = (actual == expected) ? "PASS" : "FAIL";
        System.out.printf("%-20s | %-4s | Expected: %-6d | Actual: %-6d | Time: %dms%n",
                testName, status, expected, actual, (endTime - startTime));
    }
}