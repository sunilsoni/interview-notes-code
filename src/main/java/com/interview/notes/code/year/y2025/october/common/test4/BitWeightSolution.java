package com.interview.notes.code.year.y2025.october.common.test4;

public class BitWeightSolution {

    /**
     * Main method to test the bit weight solution with various test cases
     * Tests include regular numbers, powers of 2, and edge cases
     */
    public static void main(String[] args) {
        // Test array containing different scenarios: regular number, power of 2, consecutive 1s, large number
        int[] testCases = {92, 8, 15, 1024, 2147483646};

        // Expected results array - precomputed correct answers for each test case
        int[] expectedResults = {91, 4, 23, 512, 2147483645};

        // Iterate through each test case to verify our solution
        for (int i = 0; i < testCases.length; i++) {
            // Get result for current test case
            int result = findClosestWithSameWeight(testCases[i]);

            // Compare result with expected value
            boolean passed = result == expectedResults[i];

            // Print detailed test results including input, expected output, actual output, and pass/fail status
            System.out.printf("Test Case %d: Input=%d, Expected=%d, Got=%d, %s%n",
                    i + 1, testCases[i], expectedResults[i], result,
                    passed ? "PASS" : "FAIL");
        }
    }

    /**
     * Finds the closest number to x that has the same number of 1s in its binary representation
     *
     * @param x The input number to process
     * @return The closest number with the same binary weight
     */
    public static int findClosestWithSameWeight(int x) {
        // Convert input number to binary string for easier pattern matching
        // Example: 92 becomes "1011100"
        String binary = Integer.toBinaryString(x);

        // Iterate through binary string from right to left (except last digit)
        // Looking for adjacent bits that can be swapped
        for (int i = binary.length() - 2; i >= 0; i--) {

            // Check for "10" pattern - swapping these bits will give us a smaller number
            // Example: In 92 (1011100), we find "10" at positions 2,1
            if (binary.charAt(i) == '1' && binary.charAt(i + 1) == '0') {
                // Calculate position from right side (0-based)
                // Example: for 92, pos = 7-2-1 = 4
                int pos = binary.length() - i - 1;

                // Swap bits by:
                // 1. Subtracting 2^pos (removing 1 from current position)
                // 2. Adding 2^(pos-1) (adding 1 to position on right)
                // Example: 92 - 2^4 + 2^3 = 92 - 16 + 8 = 91
                return x - (1 << pos) + (1 << (pos - 1));
            }

            // Check for "01" pattern - swapping these bits will give us a larger number
            // This case handles when we need a larger number
            if (binary.charAt(i) == '0' && binary.charAt(i + 1) == '1') {
                // Calculate position from right side (0-based)
                int pos = binary.length() - i - 1;

                // Swap bits by:
                // 1. Adding 2^pos (adding 1 to current position)
                // 2. Subtracting 2^(pos-1) (removing 1 from position on right)
                return x + (1 << pos) - (1 << (pos - 1));
            }
        }

        // Return original number if no swappable patterns found
        // (This shouldn't happen given problem constraints)
        return x;
    }
}
