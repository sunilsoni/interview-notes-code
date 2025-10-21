package com.interview.notes.code.year.y2025.october.common.test5;

public class BitWeightSolution {

    // Main method to test our solution with various test cases
    public static void main(String[] args) {
        // Test cases array containing input values to verify our solution
        int[] testCases = {92, 8, 15, 1024, 2147483646};

        // Expected results array corresponding to each test case
        int[] expectedResults = {91, 4, 23, 512, 2147483645};

        // Loop through each test case and verify results
        for (int i = 0; i < testCases.length; i++) {
            int result = findClosestWithSameWeight(testCases[i]);
            boolean passed = result == expectedResults[i];

            System.out.printf("Test Case %d: Input=%d, Expected=%d, Got=%d, %s%n",
                    i + 1, testCases[i], expectedResults[i], result,
                    passed ? "PASS" : "FAIL");
        }
    }

    // Method to find closest number with same weight (number of 1s in binary)
    public static int findClosestWithSameWeight(int x) {
        // Count the number of 1s in binary representation (weight)
        int weight = Integer.bitCount(x);

        // Variables to store potential candidates
        int smaller = x - 1;
        int larger = x + 1;

        // Keep searching until we find a number with same weight
        while (true) {
            // Check smaller number first as it might be closer
            if (Integer.bitCount(smaller) == weight) {
                return smaller;
            }
            // Check larger number
            if (Integer.bitCount(larger) == weight) {
                return larger;
            }
            // Move to next numbers in both directions
            smaller--;
            larger++;
        }
    }
}
