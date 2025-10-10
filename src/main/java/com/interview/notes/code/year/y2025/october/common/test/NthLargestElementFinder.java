package com.interview.notes.code.year.y2025.october.common.test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

    public class NthLargestElementFinder {
    
    public static void main(String[] args) {
        // Test cases to verify different scenarios
        
        // Test Case 1: Regular array with more than 3 elements
        int[] arr1 = {10, 5, 15, 20, 8};
        testFindNthLargest(arr1, 3, "Regular Array Test");
        
        // Test Case 2: Array with duplicate elements
        int[] arr2 = {10, 10, 15, 15, 20, 20};
        testFindNthLargest(arr2, 3, "Array with Duplicates");
        
        // Test Case 3: Array with exactly 3 elements
        int[] arr3 = {1, 2, 3};
        testFindNthLargest(arr3, 3, "Three Element Array");
        
        // Test Case 4: Array with less than 3 elements
        int[] arr4 = {1, 2};
        testFindNthLargest(arr4, 3, "Small Array Test");
        
        // Test Case 5: Large array test
        int[] arr5 = generateLargeArray(1000000);
        testFindNthLargest(arr5, 3, "Large Array Test");
        
        // Test Case 6: Finding 5th largest
        int[] arr6 = {1, 2, 3, 4, 5, 6, 7, 8};
        testFindNthLargest(arr6, 5, "5th Largest Element Test");
    }

    // Method to find nth largest element using Stream API
    public static Integer findNthLargest(int[] arr, int n) {
        // Check if array is null or empty
        if (arr == null || arr.length == 0) {
            return null;
        }
        
        // Check if array length is less than n
        if (arr.length < n) {
            return null;
        }

        // Convert array to stream, sort in descending order, skip n-1 elements
        // and find first element (nth largest)
        return Arrays.stream(arr)          // Convert array to stream
                    .boxed()               // Convert int to Integer
                    .distinct()            // Remove duplicates
                    .sorted(Comparator.reverseOrder()) // Sort in descending order
                    .skip(n - 1)           // Skip n-1 elements
                    .findFirst()           // Get the nth largest
                    .orElse(null);         // Handle case if not found
    }

    // Alternative implementation using PriorityQueue (more efficient for large arrays)
    public static Integer findNthLargestUsingPQ(int[] arr, int n) {
        if (arr == null || arr.length == 0 || arr.length < n) {
            return null;
        }

        // Create min heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // Process each element in array
        for (int num : arr) {
            // Add element to priority queue
            pq.offer(num);
            
            // If size exceeds n, remove smallest element
            if (pq.size() > n) {
                pq.poll();
            }
        }
        
        // Return nth largest (or null if not found)
        return pq.size() == n ? pq.peek() : null;
    }

    // Helper method to test the implementations
    private static void testFindNthLargest(int[] arr, int n, String testName) {
        System.out.println("\nTest Case: " + testName);
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Finding " + n + "th largest element");
        
        // Test both implementations
        long startTime = System.nanoTime();
        Integer resultStream = findNthLargest(arr, n);
        long streamTime = System.nanoTime() - startTime;
        
        startTime = System.nanoTime();
        Integer resultPQ = findNthLargestUsingPQ(arr, n);
        long pqTime = System.nanoTime() - startTime;
        
        // Print results
        System.out.println("Stream API Result: " + resultStream + " (Time: " + streamTime/1000000 + "ms)");
        System.out.println("PriorityQueue Result: " + resultPQ + " (Time: " + pqTime/1000000 + "ms)");
        
        // Verify results match
        if (resultStream != null && resultPQ != null && !resultStream.equals(resultPQ)) {
            System.out.println("WARNING: Results don't match!");
        }
    }

    // Helper method to generate large test array
    private static int[] generateLargeArray(int size) {
        Random rand = new Random();
        return rand.ints(size).toArray();
    }
}
