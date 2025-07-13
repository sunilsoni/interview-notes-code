package com.interview.notes.code.year.y2025.july.codesignal.test1;

import java.util.Arrays;

public class ResourceExchangeCycles {

    public static int solution(String[] resources, int conversionRate) {
        // Count initial number of A and P
        long countA = Arrays.stream(resources).filter(r -> r.equals("A")).count();
        long countP = resources.length - countA;

        int cycles = 0;

        // Run cycles until no operation possible
        while (true) {
            if (countP >= conversionRate) {
                // Convert conversionRate P -> 1 A at beginning
                countP -= conversionRate;
                countA += 1;
            } else if (countA >= 1) {
                // Convert last A -> P
                countA -= 1;
                countP += 1;
            } else {
                // No A and not enough P to convert, stop
                break;
            }
            cycles++;
        }
        return cycles;
    }

    // Helper method to run test cases
    public static void runTest(String[] resources, int conversionRate, int expected, String testName) {
        int result = solution(resources, conversionRate);
        String status = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("%s: Expected=%d, Got=%d => %s%n", testName, expected, result, status);
    }

    public static void main(String[] args) {
        // Provided test cases
        runTest(new String[]{"A", "A", "A", "P", "P", "P"}, 2, 13, "Example 1");
        runTest(new String[]{"A", "A"}, 2, 4, "Example 2");
        runTest(new String[]{"P", "P", "P"}, 3, 2, "Example 3");

        // Additional edge tests

        // All A's only, no P
        runTest(new String[]{"A", "A", "A", "A"}, 2, 4, "All A's only");

        // All P's only, P count divisible by conversionRate
        runTest(new String[]{"P", "P", "P", "P"}, 2, 5, "All P's divisible");

        // All P's only, P count not divisible by conversionRate
        runTest(new String[]{"P", "P", "P", "P", "P"}, 2, 7, "All P's non-divisible");

        // Large input case: 500 elements, half A half P, conversionRate 10
        String[] largeInput = new String[500];
        Arrays.fill(largeInput, 0, 250, "A");
        Arrays.fill(largeInput, 250, 500, "P");
        runTest(largeInput, 10, -1, "Large Input"); // We'll compute expected dynamically

        // For large input test: Let's just print the result for info, no expected check
        int largeResult = solution(largeInput, 10);
        System.out.printf("Large Input: Cycles=%d%n", largeResult);
    }
}
