package com.interview.notes.code.year.y2025.november.linkedin.test3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class RotatedArraySearch {

    /**
     * Searches for a target value in a rotated sorted array
     *
     * @param targetValue The value we're searching for in the array
     * @param arr         The rotated sorted array to search in
     * @return true if target is found, false otherwise
     */
    public static boolean isInList(float targetValue, float[] arr) {
        // Handle null or empty array - target cannot exist in empty array
        if (arr == null || arr.length == 0) {
            return false; // No elements to search, so target doesn't exist
        }

        // Handle single element array - just check if it matches target
        if (arr.length == 1) {
            return arr[0] == targetValue; // Compare the only element with target
        }

        // Initialize binary search boundaries
        int left = 0; // Start of search range (inclusive)
        int right = arr.length - 1; // End of search range (inclusive)

        // Continue searching while we have a valid search range
        while (left <= right) {
            // Calculate middle index to divide array in half
            int mid = left + (right - left) / 2; // Prevents integer overflow for large arrays

            // Check if we found the target at middle position
            if (arr[mid] == targetValue) {
                return true; // Target found at index mid
            }

            // Determine which half of the array is properly sorted
            // This is key to searching in rotated array

            // Check if left half is sorted (from left to mid)
            if (arr[left] <= arr[mid]) {
                // Left half is sorted in ascending order

                // Check if target lies within the sorted left half's range
                if (targetValue >= arr[left] && targetValue < arr[mid]) {
                    // Target is in left half, so discard right half
                    right = mid - 1; // Move right boundary to mid-1
                } else {
                    // Target is not in left half, so must be in right half
                    left = mid + 1; // Move left boundary to mid+1
                }
            }
            // Otherwise, right half must be sorted (from mid to right)
            else {
                // Right half is sorted in ascending order

                // Check if target lies within the sorted right half's range
                if (targetValue > arr[mid] && targetValue <= arr[right]) {
                    // Target is in right half, so discard left half
                    left = mid + 1; // Move left boundary to mid+1
                } else {
                    // Target is not in right half, so must be in left half
                    right = mid - 1; // Move right boundary to mid-1
                }
            }
        }

        // Searched entire array and didn't find target
        return false; // Target doesn't exist in the array
    }

    /**
     * Alternative implementation using Java 8 Streams
     * Note: This is less efficient O(n) but demonstrates stream usage
     * Fixed version that handles float arrays properly
     */
    public static boolean isInListUsingStreams(float targetValue, float[] arr) {
        // Handle null array case
        if (arr == null) {
            return false; // Null array contains no elements
        }

        // Since Arrays.stream doesn't support float[], we use IntStream with indices
        return IntStream.range(0, arr.length) // Create stream of indices from 0 to array length
                .mapToDouble(i -> arr[i]) // Map each index to the float value at that index (auto-converted to double)
                .anyMatch(value -> Math.abs(value - targetValue) < 0.0001); // Check if any value equals target
        // Using small epsilon for floating-point comparison to handle precision issues
    }

    /**
     * Another stream approach using manual iteration
     * This approach is more straightforward for float arrays
     */
    public static boolean isInListUsingStreamsV2(float targetValue, float[] arr) {
        // Handle null array case
        if (arr == null) {
            return false; // Null array contains no elements
        }

        // Convert float array to Double array for stream processing
        // This approach creates a wrapper array but is cleaner
        Double[] doubleArray = new Double[arr.length]; // Create wrapper array
        for (int i = 0; i < arr.length; i++) {
            doubleArray[i] = (double) arr[i]; // Convert each float to Double wrapper
        }

        // Now we can use Arrays.stream with the wrapper array
        return Arrays.stream(doubleArray) // Create stream from Double array
                .anyMatch(value -> Math.abs(value - targetValue) < 0.0001); // Check for match with epsilon
    }

    /**
     * Most efficient stream approach for float arrays
     * Uses parallel processing for large arrays
     */
    public static boolean isInListUsingParallelStream(float targetValue, float[] arr) {
        // Handle null array case  
        if (arr == null) {
            return false; // Null array contains no elements
        }

        // Use parallel IntStream for better performance on large arrays
        return IntStream.range(0, arr.length) // Create index stream
                .parallel() // Enable parallel processing for large arrays
                .anyMatch(i -> Float.compare(arr[i], targetValue) == 0); // Use Float.compare for accurate comparison
    }

    /**
     * Helper method to find the rotation point (for educational purposes)
     * This shows where the array was rotated
     */
    private static int findRotationPoint(float[] arr) {
        // Handle edge cases
        if (arr == null || arr.length <= 1) {
            return 0; // No rotation in empty or single element array
        }

        int left = 0; // Start of search range
        int right = arr.length - 1; // End of search range

        // If array is not rotated at all (already sorted)
        if (arr[left] < arr[right]) {
            return 0; // No rotation, smallest element is at index 0
        }

        // Binary search for rotation point
        while (left < right) {
            int mid = left + (right - left) / 2; // Calculate middle index

            // Check if mid+1 is the rotation point
            if (mid < arr.length - 1 && arr[mid] > arr[mid + 1]) {
                return mid + 1; // Found rotation point where order breaks
            }

            // Decide which half to search
            if (arr[mid] > arr[right]) {
                // Rotation point is in right half
                left = mid + 1; // Move to right half
            } else {
                // Rotation point is in left half
                right = mid; // Move to left half
            }
        }

        return left; // Return the rotation point index
    }

    /**
     * Main method to test the implementation
     */
    public static void main(String[] args) {
        System.out.println("=== Rotated Array Search Tests ===\n");

        // Test counters for tracking results
        int testNumber = 1; // Current test number
        int passedTests = 0; // Count of passed tests
        int totalTests = 0; // Total number of tests

        // Test Case 1: Find "1" in rotated array
        totalTests++;
        float[] arr1 = {6, 7, 1, 2, 3, 4, 5}; // Rotated sorted array
        float target1 = 1;
        boolean expected1 = true; // 1 exists in the array
        boolean result1 = isInList(target1, arr1);
        System.out.println("Test " + testNumber++ + ": Find " + target1 + " in " + Arrays.toString(arr1));
        System.out.println("Expected: " + expected1 + ", Got: " + result1);
        if (result1 == expected1) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test Case 2: Find "4" in rotated array
        totalTests++;
        float[] arr2 = {6, 7, 1, 2, 3, 4, 5}; // Same rotated array
        float target2 = 4;
        boolean expected2 = true; // 4 exists in the array
        boolean result2 = isInList(target2, arr2);
        System.out.println("Test " + testNumber++ + ": Find " + target2 + " in " + Arrays.toString(arr2));
        System.out.println("Expected: " + expected2 + ", Got: " + result2);
        if (result2 == expected2) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test Case 3: Find non-existent element
        totalTests++;
        float[] arr3 = {6, 7, 1, 2, 3, 4, 5};
        float target3 = 8;
        boolean expected3 = false; // 8 doesn't exist in the array
        boolean result3 = isInList(target3, arr3);
        System.out.println("Test " + testNumber++ + ": Find " + target3 + " in " + Arrays.toString(arr3));
        System.out.println("Expected: " + expected3 + ", Got: " + result3);
        if (result3 == expected3) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test Case 4: Array with no rotation (already sorted)
        totalTests++;
        float[] arr4 = {1, 2, 3, 4, 5, 6, 7}; // Not rotated
        float target4 = 5;
        boolean expected4 = true;
        boolean result4 = isInList(target4, arr4);
        System.out.println("Test " + testNumber++ + ": Find " + target4 + " in sorted array " + Arrays.toString(arr4));
        System.out.println("Expected: " + expected4 + ", Got: " + result4);
        if (result4 == expected4) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test Case 5: Single element array
        totalTests++;
        float[] arr5 = {42};
        float target5 = 42;
        boolean expected5 = true;
        boolean result5 = isInList(target5, arr5);
        System.out.println("Test " + testNumber++ + ": Find " + target5 + " in single element array " + Arrays.toString(arr5));
        System.out.println("Expected: " + expected5 + ", Got: " + result5);
        if (result5 == expected5) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test Case 6: Empty array
        totalTests++;
        float[] arr6 = {};
        float target6 = 1;
        boolean expected6 = false; // Empty array contains nothing
        boolean result6 = isInList(target6, arr6);
        System.out.println("Test " + testNumber++ + ": Find " + target6 + " in empty array");
        System.out.println("Expected: " + expected6 + ", Got: " + result6);
        if (result6 == expected6) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test Case 7: Two element array
        totalTests++;
        float[] arr7 = {2, 1}; // Rotated two element array
        float target7 = 1;
        boolean expected7 = true;
        boolean result7 = isInList(target7, arr7);
        System.out.println("Test " + testNumber++ + ": Find " + target7 + " in " + Arrays.toString(arr7));
        System.out.println("Expected: " + expected7 + ", Got: " + result7);
        if (result7 == expected7) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test Case 8: Decimal/float values
        totalTests++;
        float[] arr8 = {6.5f, 7.2f, 1.1f, 2.3f, 3.4f, 4.5f, 5.6f}; // Rotated array with decimals
        float target8 = 3.4f;
        boolean expected8 = true;
        boolean result8 = isInList(target8, arr8);
        System.out.println("Test " + testNumber++ + ": Find " + target8 + " in " + Arrays.toString(arr8));
        System.out.println("Expected: " + expected8 + ", Got: " + result8);
        if (result8 == expected8) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test Case 9: Large rotated array
        totalTests++;
        // Create large rotated array: [50000...99999, 0...49999]
        float[] largeArr = new float[100000];
        for (int i = 0; i < 50000; i++) {
            largeArr[i] = i + 50000; // Second half of sorted array comes first
        }
        for (int i = 0; i < 50000; i++) {
            largeArr[i + 50000] = i; // First half of sorted array comes second
        }
        float targetLarge = 75000;
        boolean expectedLarge = true;
        long startTime = System.currentTimeMillis(); // Measure performance
        boolean resultLarge = isInList(targetLarge, largeArr);
        long endTime = System.currentTimeMillis();
        System.out.println("Test " + testNumber++ + ": Find " + targetLarge + " in large rotated array (100K elements)");
        System.out.println("Expected: " + expectedLarge + ", Got: " + resultLarge);
        System.out.println("Search time: " + (endTime - startTime) + " ms");
        if (resultLarge == expectedLarge) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test Case 10: Find boundary elements
        totalTests++;
        float[] arr10 = {6, 7, 1, 2, 3, 4, 5};
        float target10a = 7; // Element before rotation point
        float target10b = 1; // Element at rotation point
        boolean result10a = isInList(target10a, arr10);
        boolean result10b = isInList(target10b, arr10);
        System.out.println("Test " + testNumber++ + ": Find boundary elements in " + Arrays.toString(arr10));
        System.out.println("Find " + target10a + ": " + result10a + " (Expected: true)");
        System.out.println("Find " + target10b + ": " + result10b + " (Expected: true)");
        if (result10a && result10b) {
            System.out.println("✓ PASS\n");
            passedTests++;
        } else {
            System.out.println("✗ FAIL\n");
        }

        // Test all stream implementations
        System.out.println("=== Stream Implementation Tests ===");
        float[] streamTestArr = {6, 7, 1, 2, 3, 4, 5};
        float streamTarget = 4;

        // Test first stream method
        boolean streamResult1 = isInListUsingStreams(streamTarget, streamTestArr);
        System.out.println("Stream Method 1 - Find " + streamTarget + ": " + streamResult1);
        System.out.println("Expected: true - " + (streamResult1 ? "✓ PASS" : "✗ FAIL"));

        // Test second stream method
        boolean streamResult2 = isInListUsingStreamsV2(streamTarget, streamTestArr);
        System.out.println("Stream Method 2 - Find " + streamTarget + ": " + streamResult2);
        System.out.println("Expected: true - " + (streamResult2 ? "✓ PASS" : "✗ FAIL"));

        // Test parallel stream method
        boolean streamResult3 = isInListUsingParallelStream(streamTarget, streamTestArr);
        System.out.println("Parallel Stream - Find " + streamTarget + ": " + streamResult3);
        System.out.println("Expected: true - " + (streamResult3 ? "✓ PASS" : "✗ FAIL"));

        // Test negative case with streams
        float notFoundTarget = 10;
        boolean streamNotFound = isInListUsingStreams(notFoundTarget, streamTestArr);
        System.out.println("\nStream search for non-existent " + notFoundTarget + ": " + streamNotFound);
        System.out.println("Expected: false - " + (!streamNotFound ? "✓ PASS" : "✗ FAIL"));

        // Demonstrate finding rotation point
        System.out.println("\n=== Rotation Point Analysis ===");
        float[] demoArr = {6, 7, 1, 2, 3, 4, 5};
        int rotationPoint = findRotationPoint(demoArr);
        System.out.println("Array: " + Arrays.toString(demoArr));
        System.out.println("Rotation point index: " + rotationPoint);
        System.out.println("Smallest element: " + demoArr[rotationPoint]);

        // Final Summary
        System.out.println("\n=== Test Summary ===");
        System.out.println("Total Tests: " + totalTests);
        System.out.println("Passed: " + passedTests);
        System.out.println("Failed: " + (totalTests - passedTests));
        System.out.println("Success Rate: " + String.format("%.1f%%", (passedTests * 100.0 / totalTests)));

        // Performance comparison
        System.out.println("\n=== Performance Comparison ===");
        float[] perfArr = new float[10000];
        for (int i = 0; i < 5000; i++) {
            perfArr[i] = i + 5000; // Create rotated array
        }
        for (int i = 0; i < 5000; i++) {
            perfArr[i + 5000] = i;
        }

        // Binary search performance
        long start = System.nanoTime();
        boolean binaryResult = isInList(7500, perfArr);
        long binaryTime = System.nanoTime() - start;

        // Stream (linear) search performance  
        start = System.nanoTime();
        boolean streamPerf = isInListUsingStreams(7500, perfArr);
        long streamTime = System.nanoTime() - start;

        // Parallel stream performance
        start = System.nanoTime();
        boolean parallelResult = isInListUsingParallelStream(7500, perfArr);
        long parallelTime = System.nanoTime() - start;

        System.out.println("Binary Search Time: " + binaryTime + " nanoseconds");
        System.out.println("Stream Search Time: " + streamTime + " nanoseconds");
        System.out.println("Parallel Stream Time: " + parallelTime + " nanoseconds");
        System.out.println("Binary search is " + (streamTime / binaryTime) + "x faster than stream");
        System.out.println("Binary search is " + (parallelTime / binaryTime) + "x faster than parallel stream");
    }
}