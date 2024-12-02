package com.interview.notes.code.year.y2024.nov24.CodeSignal.test1;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TripleCounter {

    public static int[] solution(String[] queries, int diff) {
        int[] result = new int[queries.length];
        Map<Integer, Long> counts = new HashMap<>();
        long tripleCount = 0;

        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            int num = Integer.parseInt(query.substring(1));

            if (query.charAt(0) == '+') {
                long count = counts.getOrDefault(num, 0L);
                long prev = counts.getOrDefault(num - diff, 0L);
                long next = counts.getOrDefault(num + diff, 0L);

                tripleCount += prev * next;
                counts.put(num, count + 1);
            } else {
                long count = counts.get(num);
                long prev = counts.getOrDefault(num - diff, 0L);
                long next = counts.getOrDefault(num + diff, 0L);

                tripleCount -= prev * next;
                if (count == 1) {
                    counts.remove(num);
                } else {
                    counts.put(num, count - 1);
                }
            }

            result[i] = (int) tripleCount;
        }

        return result;
    }

    public static void main(String[] args) {
        int totalTests = 0;
        int passedTests = 0;

        // Test case 1
        String[] queries1 = {"+4", "+5", "+6", "+4", "+3", "-4"};
        int diff1 = 1;
        int[] expected1 = {0, 0, 1, 2, 4, 0};
        boolean passed1 = runTest(queries1, diff1, expected1, "Test case 1");
        totalTests++;
        if (passed1) passedTests++;

        // Test case 2 (Edge case: empty result)
        String[] queries2 = {"+1", "+2", "+3"};
        int diff2 = 2;
        int[] expected2 = {0, 0, 0};
        boolean passed2 = runTest(queries2, diff2, expected2, "Test case 2");
        totalTests++;
        if (passed2) passedTests++;

        // Test case 3 (Large input)
        String[] queries3 = new String[100000];
        for (int i = 0; i < 100000; i++) {
            queries3[i] = "+" + (i + 1);
        }
        int diff3 = 1;
        int[] expected3 = new int[100000];
        for (int i = 2; i < 100000; i++) {
            expected3[i] = i - 1;
        }
        boolean passed3 = runTest(queries3, diff3, expected3, "Test case 3 (Large input)");
        totalTests++;
        if (passed3) passedTests++;

        // Summary
        System.out.println("\nTest Summary:");
        System.out.println("Total tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success rate: " + (passedTests * 100.0 / totalTests) + "%");
    }

    private static boolean runTest(String[] queries, int diff, int[] expected, String testName) {
        System.out.println("Running " + testName + "...");
        long startTime = System.currentTimeMillis();
        int[] result = solution(queries, diff);
        long endTime = System.currentTimeMillis();

        boolean passed = Arrays.equals(result, expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        System.out.println("Execution time: " + (endTime - startTime) + " ms");

        if (!passed) {
            System.out.println("Expected: " + Arrays.toString(expected));
            System.out.println("Actual: " + Arrays.toString(result));
        }
        System.out.println();

        return passed;
    }
}