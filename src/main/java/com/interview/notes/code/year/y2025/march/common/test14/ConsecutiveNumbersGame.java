package com.interview.notes.code.year.y2025.march.common.test14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConsecutiveNumbersGame {

    // This method runs the game rounds on the list and returns the number of rounds needed
    public static int solve(List<Integer> arr) {
        int rounds = 0;                     // Counter for the number of rounds played
        int n = arr.size();                 // Total number of elements in the list

        // If there is less than 2 elements, the game is already over (nothing to update)
        if (n < 2) return rounds;

        // Continue rounds until the first n-1 elements are all multiples of 3
        while (!allMultiplesOf3(arr, n)) {
            rounds++;                      // Increase round count for each iteration

            // Process each pair (from index 0 to n-2) sequentially
            for (int i = 0; i < n - 1; i++) {
                int product = arr.get(i) * arr.get(i + 1);  // Compute the product of the current pair
                // If the product is divisible by 3, update the current element with this product
                if (product % 3 == 0) {
                    arr.set(i, product); // Replace a[i] with the product if condition meets
                }
            }
        }
        return rounds;  // Return the total rounds played when condition is met
    }

    // Helper method: Checks if the first n-1 elements in the list are multiples of 3 using streams
    private static boolean allMultiplesOf3(List<Integer> arr, int n) {
        return arr.subList(0, n - 1)
                .stream()
                .allMatch(x -> x % 3 == 0);  // True if every element is divisible by 3
    }

    // Test method: Runs various test cases and prints PASS/FAIL for each case.
    public static void runTests() {
        // List to hold our test cases
        List<TestCase> testCases = new ArrayList<>();

        // Example Test Case 1
        testCases.add(new TestCase(Arrays.asList(34, 56, 20, 90, 100), 3));
        // Example Test Case 2
        testCases.add(new TestCase(Arrays.asList(1, 333, 222, 22), 1));
        // Additional test: Already satisfies condition for first n-1 elements
        testCases.add(new TestCase(Arrays.asList(3, 6, 9, 5), 0));
        // Additional test: Single element list (no rounds needed)
        testCases.add(new TestCase(Arrays.asList(5), 0));
        // Additional test: Large input where all elements are already multiples of 3
        testCases.add(new TestCase(new ArrayList<>(Collections.nCopies(10000, 3)), 0));

        int passed = 0;  // Counter for passed test cases

        // Iterate over each test case and validate the result
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            // Create a copy to prevent side effects from previous tests
            List<Integer> arrCopy = new ArrayList<>(tc.input);
            int result = solve(arrCopy);
            if (result == tc.expected) {
                System.out.println("Test case " + (i + 1) + " PASS");
                passed++;
            } else {
                System.out.println("Test case " + (i + 1) + " FAIL. Expected: "
                        + tc.expected + ", Got: " + result);
            }
        }
        System.out.println(passed + " out of " + testCases.size() + " test cases passed.");
    }

    // Main method to run our test cases
    public static void main(String[] args) {
        runTests();  // Execute test cases to verify our solution
    }

    // Inner class to represent a test case with input and expected output
    private static class TestCase {
        List<Integer> input;  // The list of numbers as input
        int expected;         // The expected number of rounds

        TestCase(List<Integer> input, int expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}