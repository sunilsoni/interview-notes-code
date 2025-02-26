package com.interview.notes.code.year.y2025.feb25.common.test10;

import java.util.Arrays;

public class EvenNumberSquares {
    
    // Main solution method
    public static int[] findEvenSquares(int[] numbers) {
        return Arrays.stream(numbers)
                    .filter(n -> n % 2 == 0)    // keep only even numbers
                    .map(n -> n * n)            // square them
                    .toArray();
    }
    
    // Test method
    public static void testCase(int[] input, int[] expected) {
        int[] result = findEvenSquares(input);
        System.out.println("Input: " + Arrays.toString(input));
        System.out.println("Result: " + Arrays.toString(result));
        System.out.println("Expected: " + Arrays.toString(expected));
        
        boolean passed = Arrays.equals(result, expected);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
        System.out.println("------------------------");
    }
    
    public static void main(String[] args) {
        // Test Case 1: Basic case
        testCase(
            new int[]{1, 2, 3, 4}, 
            new int[]{4, 16}
        );
        
        // Test Case 2: Empty array
        testCase(
            new int[]{}, 
            new int[]{}
        );
        
        // Test Case 3: No even numbers
        testCase(
            new int[]{1, 3, 5, 7}, 
            new int[]{}
        );
        
        // Test Case 4: All even numbers
        testCase(
            new int[]{2, 4, 6, 8}, 
            new int[]{4, 16, 36, 64}
        );
        
        // Test Case 5: Large numbers
        testCase(
            new int[]{1000, 2000, 3000}, 
            new int[]{1000000, 4000000, 9000000}
        );
    }
}
