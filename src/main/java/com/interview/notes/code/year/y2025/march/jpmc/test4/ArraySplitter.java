package com.interview.notes.code.year.y2025.march.jpmc.test4;

import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class ArraySplitter {
    
    /**
     * Determines the number of ways to split an array into two non-empty subarrays
     * such that the sum of elements in the left subarray is greater than the sum of 
     * elements in the right subarray.
     * 
     * @param arr The input array of integers
     * @return The number of valid splits
     */
    public static int splitIntoTwo(List<Integer> arr) {
        // Calculate the total sum of the array
        int totalSum = arr.stream().mapToInt(Integer::intValue).sum();
        
        int validSplits = 0;  // Counter for valid splits
        int leftSum = 0;      // Running sum of elements in the left subarray
        
        // Iterate through the array, stopping before the last element
        // (since right subarray must be non-empty)
        for (int i = 0; i < arr.size() - 1; i++) {
            leftSum += arr.get(i);             // Add current element to left sum
            int rightSum = totalSum - leftSum; // Calculate right sum
            
            // Check if this split satisfies our condition
            if (leftSum > rightSum) {
                validSplits++;
            }
        }
        
        return validSplits;
    }
    
    /**
     * Main method to test the solution with various test cases
     */
    public static void main(String[] args) {
        // Test case from the problem statement
        List<Integer> test1 = Arrays.asList(10, 4, -8, 7);
        int expected1 = 2;
        int result1 = splitIntoTwo(test1);
        System.out.println("Test 1: " + (result1 == expected1 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected1 + ", Got: " + result1 + ")");
        
        // Sample case 0
        List<Integer> test2 = Arrays.asList(10, -5, 6);
        int expected2 = 1;
        int result2 = splitIntoTwo(test2);
        System.out.println("Test 2: " + (result2 == expected2 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected2 + ", Got: " + result2 + ")");
        
        // Sample case 1
        List<Integer> test3 = Arrays.asList(-3, -2, 10, 20, -30);
        int expected3 = 2;
        int result3 = splitIntoTwo(test3);
        System.out.println("Test 3: " + (result3 == expected3 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected3 + ", Got: " + result3 + ")");
        
        // Edge case: minimum array size (2)
        List<Integer> test4 = Arrays.asList(5, 3);
        int expected4 = 1;
        int result4 = splitIntoTwo(test4);
        System.out.println("Test 4: " + (result4 == expected4 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected4 + ", Got: " + result4 + ")");
        
        // Edge case: all negative numbers
        List<Integer> test5 = Arrays.asList(-10, -5, -3, -8);
        int expected5 = 1;
        int result5 = splitIntoTwo(test5);
        System.out.println("Test 5: " + (result5 == expected5 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected5 + ", Got: " + result5 + ")");
        
        // Large array test
        List<Integer> largeArray = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeArray.add(i % 2 == 0 ? 10 : -5);  // Alternating 10 and -5
        }
        long startTime = System.currentTimeMillis();
        int largeResult = splitIntoTwo(largeArray);
        long endTime = System.currentTimeMillis();
        System.out.println("Large Test: Processed array of size " + largeArray.size() + 
                           " in " + (endTime - startTime) + "ms with " + largeResult + " valid splits");
        
        // Test with array having equal sums on both sides
        List<Integer> test6 = Arrays.asList(5, 5, 5, 5, 10);
        int expected6 = 0;
        int result6 = splitIntoTwo(test6);
        System.out.println("Test 6: " + (result6 == expected6 ? "PASS" : "FAIL") + 
                           " (Expected: " + expected6 + ", Got: " + result6 + ")");
    }
}