package com.interview.notes.code.year.y2025.march.caspex.test3;

import java.util.*;

public class TheOnlyWayIsUp {
    
    /**
     * Main solution method to find the minimum number of times B needs to be added
     * to make the array a rising sequence
     * 
     * @param B the value to add to elements
     * @param ar the input sequence
     * @return total number of times B is added
     */
    public static int solve(int B, List<Integer> ar) {
        // If array has less than 2 elements, it's already a rising sequence
        if (ar.size() < 2) {
            return 0;
        }
        
        int totalAdditions = 0;
        
        // We'll iterate through the array starting from the second element
        for (int i = 1; i < ar.size(); i++) {
            // Current element and previous element in the sequence
            int current = ar.get(i);
            int previous = ar.get(i-1);
            
            // If current element is not greater than previous, we need to add B
            if (current <= previous) {
                // Calculate how many times we need to add B to make current > previous
                int diff = previous - current;
                int additions = (diff / B) + 1; // Add 1 to make it strictly greater
                
                // Update the current element value after adding B multiple times
                current += additions * B;
                ar.set(i, current);
                
                // Keep track of total additions
                totalAdditions += additions;
            }
        }
        
        return totalAdditions;
    }
    
    /**
     * Alternative implementation using a greedy approach with a single pass
     * This avoids modifying the original array
     */
    public static int solveOptimized(int B, List<Integer> ar) {
        if (ar.size() < 2) {
            return 0;
        }
        
        int totalAdditions = 0;
        // Keep track of the minimum value the current element should exceed
        int minNextValue = ar.get(0);
        
        for (int i = 1; i < ar.size(); i++) {
            int current = ar.get(i);
            
            // If current element is not greater than the minimum required value
            if (current <= minNextValue) {
                // Calculate additions needed
                int diff = minNextValue - current;
                int additions = (diff / B) + 1;
                
                // Update current value after additions
                current += additions * B;
                totalAdditions += additions;
            }
            
            // Update minimum value for next element
            minNextValue = current;
        }
        
        return totalAdditions;
    }
    
    /**
     * Main method to test the solution
     */
    public static void main(String[] args) {
        // Test case 1
        int B1 = 2;
        List<Integer> ar1 = Arrays.asList(1, 3, 3, 2);
        int expected1 = 3;
        int result1 = solve(B1, new ArrayList<>(ar1));
        System.out.println("Test 1: " + (result1 == expected1 ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected1 + ", Got: " + result1);
        
        // Test case 2
        int B2 = 1;
        List<Integer> ar2 = Arrays.asList(1, 1);
        int expected2 = 1;
        int result2 = solve(B2, new ArrayList<>(ar2));
        System.out.println("Test 2: " + (result2 == expected2 ? "PASS" : "FAIL"));
        System.out.println("Expected: " + expected2 + ", Got: " + result2);
        
        // Edge case: Empty array
        int B3 = 5;
        List<Integer> ar3 = new ArrayList<>();
        int expected3 = 0;
        int result3 = solve(B3, new ArrayList<>(ar3));
        System.out.println("Edge case (Empty array): " + (result3 == expected3 ? "PASS" : "FAIL"));
        
        // Edge case: Single element
        int B4 = 5;
        List<Integer> ar4 = Arrays.asList(10);
        int expected4 = 0;
        int result4 = solve(B4, new ArrayList<>(ar4));
        System.out.println("Edge case (Single element): " + (result4 == expected4 ? "PASS" : "FAIL"));
        
        // Edge case: Already rising sequence
        int B5 = 5;
        List<Integer> ar5 = Arrays.asList(1, 2, 3, 4, 5);
        int expected5 = 0;
        int result5 = solve(B5, new ArrayList<>(ar5));
        System.out.println("Edge case (Already rising): " + (result5 == expected5 ? "PASS" : "FAIL"));
        
        // Test with large input
        testLargeInput();
        
        // Verify optimized solution gives same results
        System.out.println("\nVerifying optimized solution:");
        int optResult1 = solveOptimized(B1, ar1);
        System.out.println("Test 1 (Optimized): " + (optResult1 == expected1 ? "PASS" : "FAIL"));
        
        int optResult2 = solveOptimized(B2, ar2);
        System.out.println("Test 2 (Optimized): " + (optResult2 == expected2 ? "PASS" : "FAIL"));
    }
    
    /**
     * Test with a large input to verify performance
     */
    private static void testLargeInput() {
        // Create a large descending array - worst case scenario
        int size = 2000; // Maximum constraint
        int B = 1;
        List<Integer> largeArray = new ArrayList<>();
        
        // Fill with descending values
        for (int i = size; i > 0; i--) {
            largeArray.add(i);
        }
        
        long startTime = System.currentTimeMillis();
        int result = solve(B, largeArray);
        long endTime = System.currentTimeMillis();
        
        System.out.println("\nLarge input test:");
        System.out.println("Array size: " + size);
        System.out.println("Total additions: " + result);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        
        // Expected result for descending array 2000,1999,...,1 with B=1
        // Each element needs to be incremented to be greater than the previous
        int expected = (size * (size - 1)) / 2;
        System.out.println("Large test: " + (result == expected ? "PASS" : "FAIL"));
    }
}