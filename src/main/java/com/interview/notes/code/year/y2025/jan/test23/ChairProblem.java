package com.interview.notes.code.year.y2025.jan.test23;

public class ChairProblem {

    public static void main(String[] args) {
        // Provided test cases
        testCase(6, 1);
        testCase(5, 4);
        testCase(4, 1);
        testCase(2, 2);
        testCase(1, 1);
        // Additional edge cases
        testCase(3, 1);
        testCase(7, 4);
    }

    private static void testCase(int n, int expected) {
        int result = findLastChair(n);
        System.out.println("Test case n=" + n + ": " + (result == expected ? "PASS" : "FAIL") + " (Expected: " + expected + ", Actual: " + result + ")");
    }

    public static int findLastChair(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("Number of chairs must be at least 1");
        }
        int res = 0;
        for (int i = 2; i <= n; i++) {
            res = (res + 3) % i;
        }
        return res + 1;
    }
}