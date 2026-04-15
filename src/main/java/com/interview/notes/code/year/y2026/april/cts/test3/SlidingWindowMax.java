package com.interview.notes.code.year.y2026.april.cts.test3;

import java.util.ArrayDeque; // Importing ArrayDeque to build our sliding window
import java.util.Arrays; // Importing Arrays for formatting output and comparing arrays during testing
import java.util.Deque; // Importing the Deque interface for our variable declaration

public class SlidingWindowMax { // Main class declaration for the sliding window problem

    public static int[] maxSlidingWindow(int[] nums, int k) { // Method signature accepting the array and window size
        // We use 'var' (Java 10+) to keep code concise; if the array is empty, we return an empty array
        if (nums == null || nums.length == 0 || k <= 0) return new int[0]; // Safety check to prevent NullPointer or out-of-bounds exceptions

        var result = new int[nums.length - k + 1]; // The number of windows is always array length minus window size plus one
        Deque<Integer> window = new ArrayDeque<>(); // We store indices (not values) so we can easily check if they've fallen out of the window

        for (int i = 0; i < nums.length; i++) { // Loop through every single element in the input array one by one
            
            // 1. Remove indices that are left behind the current sliding window
            // We use Java 21's getFirst() and removeFirst() from the SequencedCollection interface
            while (!window.isEmpty() && window.getFirst() < i - k + 1) { // Check if the index at the front is outside our current bounds
                window.removeFirst(); // Drop the oldest index because the sliding window has passed it
            } // End of window bounds cleanup loop

            // 2. Remove smaller elements from the back of the queue
            // We use Java 21's getLast() and removeLast()
            while (!window.isEmpty() && nums[window.getLast()] < nums[i]) { // Check if the current number is bigger than numbers at the back of the queue
                window.removeLast(); // Drop smaller numbers because they can never be the maximum in this or future windows
            } // End of smaller element cleanup loop

            // 3. Add the current element's index to the back of the queue
            window.addLast(i); // We add the current index to process it as part of the window

            // 4. Record the maximum if we've processed enough elements to form a complete window
            if (i >= k - 1) { // Wait until our index 'i' has reached at least the first window size limit
                result[i - k + 1] = nums[window.getFirst()]; // The front of the queue ALWAYS holds the index of the largest value, so we save it
            } // End of result recording block
            
        } // End of the main array traversal loop

        return result; // Return the final array containing the max values for all windows
    } // End of the maxSlidingWindow method


    // Custom test method to replace JUnit, keeping things simple
    public static void runTest(String testName, int[] nums, int k, int[] expected) { // Helper method to compare expected vs actual outputs
        long startTime = System.nanoTime(); // Start the timer to check performance
        int[] result = maxSlidingWindow(nums, k); // Call our main logic method
        long endTime = System.nanoTime(); // Stop the timer

        boolean passed = Arrays.equals(result, expected); // Compare the arrays; if they match, the test passed
        
        if (passed) { // If the logic worked correctly
            System.out.println("PASS: " + testName + " (Time: " + (endTime - startTime) / 1_000_000 + "ms)"); // Print success and execution time
        } else { // If the logic failed
            System.out.println("FAIL: " + testName); // Print failure
            System.out.println("  Expected: " + Arrays.toString(expected)); // Show what we wanted
            System.out.println("  Got:      " + Arrays.toString(result)); // Show what we actually got
        } // End of pass/fail check
    } // End of runTest method

    public static void main(String[] args) { // Main method to execute the script
        // Test Case 1: The example provided in your screenshot
        runTest("Image Example Test", new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 4, new int[]{3, 5, 5, 6, 7}); // Passes the exact values from your image

        // Test Case 2: A simple sequential array
        runTest("Sequential Test", new int[]{1, 2, 3, 4, 5}, 2, new int[]{2, 3, 4, 5}); // Expected maxes for each pair

        // Test Case 3: Edge case where k equals 1 (max is just the element itself)
        runTest("Window Size 1", new int[]{9, 8, 7}, 1, new int[]{9, 8, 7}); // Should just return the exact same array

        // Test Case 4: Large data test to ensure we don't hit Time Limit Exceeded (O(N) verification)
        int largeSize = 100000; // Create a variable for a large array size
        int[] largeData = new int[largeSize]; // Initialize the large array
        int[] largeExpected = new int[largeSize - 2]; // Initialize the expected results array (k=3 means length-2)
        for (int i = 0; i < largeSize; i++) { // Loop to populate the massive array
            largeData[i] = i; // Just populate with increasing numbers
            if (i >= 2) largeExpected[i - 2] = i; // For an increasing array, the max is always the newest element
        } // End of large data generation loop
        runTest("Large Data Test (100k elements)", largeData, 3, largeExpected); // Run the large data case to prove O(N) efficiency
    } // End of main method
} // End of class