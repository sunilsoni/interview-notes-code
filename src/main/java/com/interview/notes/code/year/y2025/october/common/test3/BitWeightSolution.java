package com.interview.notes.code.year.y2025.october.common.test3;

// Class to find the closest integer (y) with the same number of 1 bits (same "weight") as a given integer x
public class BitWeightSolution {

    // Main method — acts as a manual test harness (no JUnit used)
    public static void main(String[] args) {

        // Input test cases to check the correctness of our solution
        int[] testCases = {92, 8, 15, 1024, 2147483646};

        // Expected outputs for each test case (pre-calculated manually or verified logically)
        int[] expectedResults = {91, 4, 23, 512, 2147483645};

        // Loop through each test case and compare the computed result to the expected value
        for (int i = 0; i < testCases.length; i++) {

            // Compute the closest number with the same number of 1s in its binary representation
            int result = findClosestWithSameWeight(testCases[i]);

            // Check if the obtained result matches the expected result
            boolean passed = result == expectedResults[i];

            // Print the outcome for each test case in a readable format
            System.out.printf(
                    "Test Case %d: Input=%d, Expected=%d, Got=%d, %s%n",
                    i + 1,
                    testCases[i],
                    expectedResults[i],
                    result,
                    passed ? "PASS" : "FAIL"
            );
        }
    }

    /**
     * Finds the closest integer y (not equal to x) that has the same number of 1 bits as x.
     * The algorithm searches both downward (smaller) and upward (larger) from x,
     * checking which nearby number first matches the same bit count.
     *
     * @param x input integer (must be non-negative, not all 0s or all 1s)
     * @return closest integer with the same bit weight as x
     */
    public static int findClosestWithSameWeight(int x) {

        // Calculate the number of bits set to 1 in x (its "weight")
        // Example: for 92 (binary 1011100), weight = 4
        int weight = Integer.bitCount(x);

        // Initialize two candidates: one smaller and one larger than x
        // We'll check both directions to find the nearest valid number
        int smaller = x - 1;
        int larger = x + 1;

        // Continue searching outward until a number with the same weight is found
        while (true) {

            // First check the smaller number — preference to smaller value when distance is equal
            if (Integer.bitCount(smaller) == weight) {
                // Return immediately if found
                return smaller;
            }

            // Then check the larger number
            if (Integer.bitCount(larger) == weight) {
                // Return immediately if found
                return larger;
            }

            // Neither matched, so expand the search range:
            // move one step down and one step up
            smaller--;
            larger++;
        }
    }
}