package com.interview.notes.code.year.y2026.april.LinkedIn.test1;

import java.util.Arrays; // Importing Arrays utility to handle array formatting in console output
import java.util.stream.Stream; // Importing Stream API to process our test cases cleanly

public class ArtificialIsland { // Defining the main class that contains our solution and test runner

    // --- SOLUTION ALGORITHM ---
    static int findLargestIsland(int[] islands, int material) { // Method takes the map and available materials
        var left = 0; // 'var' is a modern Java feature; this tracks the left edge of our sliding window
        var maxIslandSize = 0; // Keeps track of the largest valid island we have seen so far
        var usedMaterial = 0; // Counts how many ocean cells (0s) we have temporarily converted to land

        for (var right = 0; right < islands.length; right++) { // Loop expands the right edge of our window one by one
            if (islands[right] == 0) { // Checks if the new cell entering our window is ocean
                usedMaterial++; // If it's an ocean, we consume one unit of material to make it land
            } // Closes the if-statement

            while (usedMaterial > material) { // If we've used more material than we actually have...
                if (islands[left] == 0) { // Check if the left-most cell we are about to drop was an ocean
                    usedMaterial--; // Since we are dropping an ocean cell from the window, we get that material back
                } // Closes the if-statement
                left++; // Shrink the window by moving the left edge forward
            } // Closes the while-loop

            var currentWindowSize = right - left + 1; // Calculate the size of our current valid window
            maxIslandSize = Math.max(maxIslandSize, currentWindowSize); // Update max size if the current one is bigger
        } // Closes the main for-loop

        return maxIslandSize; // After checking the whole array, return the largest size found
    } // Closes the solution method

    public static void main(String[] args) { // Simple main method to run all tests without JUnit

        // Let's generate a massive array for the large data test case
        var largeArray = new int[100000]; // Create an array of 100,000 elements
        Arrays.fill(largeArray, 1); // Fill the entire array with 1s (land)
        largeArray[50000] = 0; // Put exactly one ocean cell right in the middle

        var testCases = Stream.of( // Using Java Stream API to list and process all our scenarios
            new TestCase(new int[]{0, 1, 0, 1, 1, 1}, 1, 5), // The exact example provided in the problem description
            new TestCase(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0}, 2, 6), // Multiple separate islands test
            new TestCase(new int[]{0, 0, 0, 0}, 2, 2), // Edge case: all oceans, limited material
            new TestCase(new int[]{1, 1, 1, 1}, 0, 4), // Edge case: all land, no material needed
            new TestCase(new int[]{}, 1, 0), // Edge case: completely empty array
            new TestCase(largeArray, 1, 100000) // Large data case: Should instantly return full length by filling the 1 gap
        ); // Closes the Stream definition

        testCases.forEach(tc -> { // Iterate through every test case in our stream
            var result = findLargestIsland(tc.islands(), tc.material()); // Run our algorithm against the test case inputs
            var status = (result == tc.expected()) ? "PASS" : "FAIL"; // Check if our result matches the expected answer

            // Print the outcome in a clean, readable format
            System.out.printf("[%s] Expected: %d | Got: %d | Material: %d | Map Size: %d%n", // Format string for output
                    status, tc.expected(), result, tc.material(), tc.islands().length); // Inject variables into the format string
        }); // Closes the forEach loop
    } // Closes the main method

    // --- TESTING SETUP ---
    record TestCase(int[] islands, int material, int expected) {} // Java 16+ feature: creates an immutable data carrier for tests instantly
} // Closes the class