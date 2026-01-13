package com.interview.notes.code.year.y2026.jan.assessments.imocha.test3;

import java.util.HashMap;
import java.util.Map;

public class VirusRemediation {

    public static int resolveViruses(int N, int X, int Y, int[] damages) {
        if (X >= 2 * Y) {
            return N * Y;
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int damage : damages) {
            frequencyMap.merge(damage, 1, Integer::sum);
        }

        int totalPairs = frequencyMap.values().stream()
                                     .mapToInt(count -> count / 2)
                                     .sum();
        
        return totalPairs * X + (N - totalPairs * 2) * Y;
    }

    public static void main(String[] args) {
        // Test Case 1: Provided Example
        runTest("Sample Case", 5, 2, 3, new int[]{1, 3, 1, 4, 3}, 7);

        // Test Case 2: X >= 2Y (Pairing is expensive)
        runTest("Expensive Pair", 3, 10, 2, new int[]{1, 1, 2}, 6);

        // Test Case 3: No Pairs Possible
        runTest("Unique Values", 3, 2, 5, new int[]{10, 20, 30}, 15);

        // Test Case 4: Large Data Input (Performance Check)
        int largeN = 50000;
        int[] largeInput = new int[largeN];
        // Fill with pairs of 1s and 2s
        for (int i = 0; i < largeN; i++) {
            largeInput[i] = (i % 2) + 1; 
        }
        // 25000 of '1' and 25000 of '2'. Total pairs = 25000. 
        // X=2, Y=5. Cost = 25000 * 2 = 50000.
        runTest("Large Data", largeN, 2, 5, largeInput, 50000);
    }

    private static void runTest(String testName, int N, int X, int Y, int[] damages, int expected) {
        long startTime = System.currentTimeMillis();
        int actual = resolveViruses(N, X, Y, damages);
        long endTime = System.currentTimeMillis();

        String status = (actual == expected) ? "PASS" : "FAIL";
        System.out.printf("[%s] %s | Expected: %d | Actual: %d | Time: %dms%n", 
            status, testName, expected, actual, (endTime - startTime));
    }
}