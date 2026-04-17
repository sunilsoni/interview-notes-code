package com.interview.notes.code.year.y2026.april.LinkedIn.test1;

import java.util.stream.IntStream; // Import IntStream to enable declarative, loop-free processing

public class HIndexFinder { // Define the main class to hold our core logic and the test runner
    
    // Method to calculate the H-Index from a sorted array
    public static int findHIndex1(int[] citations) { // Accept the integer array of citations as the only parameter
        var totalPapers = citations.length; // Use modern Java 'var' to implicitly type the integer representing array length
        
        return IntStream.range(0, totalPapers) // Generate a stream of integers representing valid array indices (0 to n-1)
                .filter(i -> citations[i] >= totalPapers - i) // Keep only the first index where citations are >= the count of remaining papers
                .map(i -> totalPapers - i) // Transform that valid index directly into the H-index score (which is the remaining paper count)
                .findFirst() // Terminate the stream early and grab the first match, ensuring we get the highest possible H-index
                .orElse(0); // If the filter finds absolutely nothing (e.g., all 0 citations), default the H-index to 0
    }

    public static int findHIndex(int[] citations) {
        var totalPapers = citations.length;
        var left = 0;
        var right = totalPapers - 1;

        // Standard binary search loop
        while (left <= right) {
            var mid = left + (right - left) / 2; // Find the middle index safely
            var papersRemaining = totalPapers - mid; // Calculate how many papers exist from 'mid' to the end

            if (citations[mid] == papersRemaining) {
                // We found the exact perfect match
                return papersRemaining;
            } else if (citations[mid] < papersRemaining) {
                // Citations are too low, we must search the right half
                left = mid + 1;
            } else {
                // Citations are high enough, but let's check the left half to see if we can get a higher H-index
                right = mid - 1;
            }
        }

        // If no exact match is found, 'left' points to the first valid index
        return totalPapers - left;
    }

    // Custom testing method to avoid JUnit dependencies
    public static void main(String[] args) { // Main method serves as the entry point for our test execution
        
        // Basic and Edge Case Tests
        runTest(new int[]{0, 1, 3, 5, 6}, 3, "Example Case"); // Run the primary example provided in the problem description
        runTest(new int[]{0, 0, 0}, 0, "All Zeros Case"); // Test the edge case where a researcher has papers but absolutely no citations
        runTest(new int[]{1, 2, 100}, 2, "Mixed Low and High Case"); // Test an array where a single highly-cited paper skews the data
        runTest(new int[]{100, 200, 300}, 3, "All High Citations Case"); // Test where all citations heavily exceed the total number of papers
        
        // Large Data Test
        var largeData = new int[100000]; // Initialize a massively large array to simulate heavy data loads and test performance
        largeData[99999] = 1; // Assign just a single citation to the very last paper in the large dataset
        runTest(largeData, 1, "Large Data Edge Case"); // Verify the stream handles large arrays efficiently without memory issues
    }

    // Helper method to execute tests and print PASS/FAIL status
    private static void runTest(int[] input, int expected, String testName) { // Accept the test input, expected output, and a readable name
        var actual = findHIndex(input); // Execute our core solver logic and store the result
        var status = (actual == expected) ? "PASS" : "FAIL"; // Compare actual vs expected to determine boolean success, formatted as a String
        System.out.println("[" + status + "] " + testName + " -> Expected: " + expected + " | Actual: " + actual); // Print the final formatted test report to the console
    }
}