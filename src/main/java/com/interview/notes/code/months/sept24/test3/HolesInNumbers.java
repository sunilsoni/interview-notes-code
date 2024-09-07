package com.interview.notes.code.months.sept24.test3;

public class HolesInNumbers {
    public static String solve(int K) {
        if (K == 0) return "1"; // No holes needed

        StringBuilder result = new StringBuilder();
        int remainingHoles = K;

        // Start with 8 if we need at least 2 holes
        if (K >= 2) {
            result.append("8");
            remainingHoles -= 2;
        }

        // Add 0's for remaining holes
        while (remainingHoles > 0) {
            result.append("0");
            remainingHoles--;
        }

        // If we haven't added any digits yet, add a 4 or 6
        if (result.length() == 0) {
            result.append("4");
        }

        return result.toString();
    }

    /**
     * Finds the minimum positive integer with K holes in it.
     *
     * @param K The number of required holes.
     * @return The minimum positive integer with K holes as a string.
     * @throws IllegalArgumentException if K is negative.
     */
    public static String solve1(int K) {
        // Input validation
        if (K < 0) {
            throw new IllegalArgumentException("K must be non-negative");
        }

        // Base case: if K is 0, return "1" (smallest positive integer with no holes)
        if (K == 0) {
            return "1";
        }

        StringBuilder result = new StringBuilder();
        int remainingHoles = K;

        // First, add as many 8's as possible (each 8 contributes 2 holes)
        while (remainingHoles >= 2) {
            result.append('8');
            remainingHoles -= 2;
        }

        // If there's one hole left, prepend a 0 (smallest single-hole digit)
        if (remainingHoles == 1) {
            result.insert(0, '0');
        }

        // If the result starts with a 0, replace it with a 4 (next smallest single-hole digit)
        if (result.charAt(0) == '0') {
            result.setCharAt(0, '4');
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        testCase(1, "0", "Test case for 1 hole");
        testCase(2, "8", "Test case for 2 holes");
        testCase(3, "48", "Test case for 3 holes");
        testCase(0, "1", "Edge case: 0 holes");
        testCase(10, "8000000000", "Large input test");
    }

    private static void testCase(int input, String expected, String description) {
        String result = solve(input);
        System.out.println(description + ": " + (result.equals(expected) ? "Passed" : "Failed"));
        if (!result.equals(expected)) {
            System.out.println("  Expected: " + expected + ", Got: " + result);
        }
    }
}
