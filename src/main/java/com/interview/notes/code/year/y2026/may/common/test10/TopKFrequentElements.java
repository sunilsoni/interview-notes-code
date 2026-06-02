package com.interview.notes.code.year.y2026.may.common.test10;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TopKFrequentElements {

    // Main logic method to find top K frequent elements
    public static int[] topKFrequent(int[] nums, int k) {
        return Arrays.stream(nums) // Convert the primitive int array into a sequential IntStream
            .boxed() // Box each primitive 'int' into an 'Integer' object so we can use Collections
            .collect(Collectors.groupingBy(num -> num, Collectors.counting())) // Group numbers as map keys and count their occurrences as map values
            .entrySet().stream() // Get the set of key-value pairs from the map and turn it into a new Stream
            .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed()) // Sort the stream entries by their frequency values in descending (highest first) order
            .limit(k) // Keep only the first 'k' elements from the sorted stream
            .mapToInt(Map.Entry::getKey) // Extract the original number (the key) from the map entry and convert it back to a primitive int
            .toArray(); // Collect the final processed numbers into a standard integer array and return it
    }

    // Main execution method to run tests
    public static void main(String[] args) {
        // Run Test 1: The example provided in your image screenshot
        runTest(new int[]{1, 1, 1, 2, 2, 3}, 2, new int[]{1, 2}, "Test 1: Standard Example");
        
        // Run Test 2: Edge case with only a single element
        runTest(new int[]{1}, 1, new int[]{1}, "Test 2: Single Element");
        
        // Run Test 3: Edge case dealing with negative numbers
        runTest(new int[]{-1, -1}, 1, new int[]{-1}, "Test 3: Negative Numbers");

        // Prepare Test 4: Creating a large dataset to ensure the code handles high volume without crashing
        int[] largeData = new int[100000]; // Initialize an array with 100,000 slots
        Arrays.fill(largeData, 0, 50000, 7); // Fill the first 50,000 slots with the number '7' (Highest frequency)
        Arrays.fill(largeData, 50000, 80000, 3); // Fill the next 30,000 slots with the number '3' (Second highest frequency)
        Arrays.fill(largeData, 80000, 100000, 9); // Fill the remaining 20,000 slots with the number '9' (Lowest frequency)
        
        // Run Test 4: Execute the large dataset test asking for top 2 frequent items (should be 7 and 3)
        runTest(largeData, 2, new int[]{7, 3}, "Test 4: Large Dataset (100k elements)");
    }

    // Helper method to execute a test, compare results, and print PASS/FAIL status
    private static void runTest(int[] input, int k, int[] expected, String testName) {
        var result = topKFrequent(input, k); // Call our main logic method using Java's local variable type inference (var) to save code words
        
        Arrays.sort(result); // Sort the result array because the order of elements in the output doesn't strictly matter
        Arrays.sort(expected); // Sort the expected array so we can do a direct 1-to-1 comparison
        
        var isPass = Arrays.equals(result, expected); // Check if our computed result perfectly matches the expected correct answer
        
        System.out.println(testName + " -> " + (isPass ? "PASS" : "FAIL")); // Print the test name followed by PASS if true, or FAIL if false
    }
}