package com.interview.notes.code.months.oct24.amz.test23;

/*
We have a set of metrics where we need to smooth-out the data using a sliding window that generates the sum of the values in that window.
So given the initial metrics dataset and the window length, return the result by applying the sum on overlapping intervals of the sliding window.
For example:
sum: ([1, 3, 2, 2, 6, 5], 3) => [6, 7, 10, 13]
_max: ([1, 3, 2, 2, 6, 5], 3) = [3, 3, 6, 6]
 */
public class SlidingWindowMetrics {
    
    public static int[] calculateSlidingSum(int[] data, int windowSize) {
        // Validate input: return empty array if data is null, window size is non-positive, or window size is greater than data length
        if (data == null || windowSize <= 0 || windowSize > data.length) {
            return new int[0];
        }

        int resultSize = data.length - windowSize + 1;
        int[] result = new int[resultSize];
        
        // Calculate initial window sum
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += data[i]; // Sum the first 'windowSize' elements
        }
        result[0] = windowSum;
        
        // Slide window and update sum
        for (int i = 1; i < resultSize; i++) {
            // Update the window sum by subtracting the element that is no longer in the window
            // and adding the new element that is now in the window
            windowSum = windowSum - data[i - 1] + data[i + windowSize - 1];
            result[i] = windowSum;
        }
        
        return result; // Return the array of sliding window sums
    }

    public static void main(String[] args) {
        // Test cases to verify the functionality of calculateSlidingSum method
        runTest(new int[]{1, 3, 2, 2, 6, 5}, 3, new int[]{6, 7, 10, 13}, "Basic Test Case");
        runTest(new int[]{1}, 1, new int[]{1}, "Single Element");
        runTest(new int[]{}, 3, new int[]{}, "Empty Array");
        runTest(new int[]{1, 2, 3}, 4, new int[]{}, "Window Size Larger Than Array");
        
        // Large data test case
        int[] largeInput = new int[1000000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = i % 10; // Populate the array with repeating numbers from 0 to 9
        }
        long startTime = System.currentTimeMillis();
        int[] largeResult = calculateSlidingSum(largeInput, 1000);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Data Test (1M elements, window=1000):");
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        System.out.println("Result size: " + largeResult.length);
        
        // Verify first few elements of large result
        boolean largeTestPass = largeResult.length == 999001 && 
                              largeResult[0] == 4500 &&
                              largeResult[1] == 4500;
        System.out.println("Large Data Test: " + (largeTestPass ? "PASS" : "FAIL"));
    }

    // Helper method to run a test case and verify the result
    private static void runTest(int[] input, int windowSize, int[] expected, String testName) {
        int[] result = calculateSlidingSum(input, windowSize);
        boolean passed = arrayEquals(result, expected);
        
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            // Print expected and actual results if the test fails
            System.out.println("Expected: " + arrayToString(expected));
            System.out.println("Got: " + arrayToString(result));
        }
    }

    // Helper method to compare two integer arrays for equality
    private static boolean arrayEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false; // Arrays are not equal if their lengths differ
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false; // Arrays are not equal if any element differs
        }
        return true; // Arrays are equal
    }

    // Helper method to convert an integer array to a string representation
    private static String arrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", "); // Add comma between elements
        }
        sb.append("]");
        return sb.toString();
    }
}
