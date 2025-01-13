package com.interview.notes.code.year.y2025.jan24.test1;

public class FibonacciGenerator {
    public static void main(String[] args) {
        // Test cases
        testFibonacci();
    }

    // Generate Fibonacci numbers
    public static long[] generateFibonacci(int count) {
        if (count <= 0) return new long[0];

        long[] fibonacci = new long[count];
        if (count >= 1) fibonacci[0] = 0;
        if (count >= 2) fibonacci[1] = 1;

        for (int i = 2; i < count; i++) {
            // Check for overflow
            if (Long.MAX_VALUE - fibonacci[i - 1] < fibonacci[i - 2]) {
                throw new ArithmeticException("Fibonacci number too large");
            }
            fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
        }

        return fibonacci;
    }

    // Test method
    public static void testFibonacci() {
        // Test case 1: Basic case
        test(5, new long[]{0, 1, 1, 2, 3}, "Basic case with 5 numbers");

        // Test case 2: Edge case - zero
        test(0, new long[]{}, "Zero input");

        // Test case 3: Single number
        test(1, new long[]{0}, "Single number");

        // Test case 4: Larger sequence
        test(7, new long[]{0, 1, 1, 2, 3, 5, 8}, "Seven numbers");

        // Test case 5: Negative input
        test(-1, new long[]{}, "Negative input");
    }

    // Helper method to run tests
    private static void test(int input, long[] expected, String testName) {
        try {
            System.out.println("\nRunning test: " + testName);
            long[] result = generateFibonacci(input);
            boolean passed = arrayEquals(result, expected);

            System.out.println("Input: " + input);
            System.out.println("Expected: " + arrayToString(expected));
            System.out.println("Got: " + arrayToString(result));
            System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        } catch (Exception e) {
            System.out.println("Test FAILED with exception: " + e.getMessage());
        }
    }

    // Helper methods for array comparison and printing
    private static boolean arrayEquals(long[] arr1, long[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    private static String arrayToString(long[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
