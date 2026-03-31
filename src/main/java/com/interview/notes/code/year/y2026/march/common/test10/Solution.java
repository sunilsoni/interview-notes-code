package com.interview.notes.code.year.y2026.march.common.test10;

import java.util.Arrays; // Needed to use Arrays.equals for comparing our expected and actual array results.

public class Solution { // The main class container for our robot logic and test runner.

    public static Integer[] walk(String path) { // Method accepts the movement string and returns the final X/Y coordinates.
        int[] pos = {0, 0}; // Use a primitive 2D array to track X (index 0) and Y (index 1), allowing state updates inside the stream.
        
        if (path == null) return new Integer[]{0, 0}; // Guard clause: if input is null, immediately return the starting position (0,0).
        
        path.chars().forEach(c -> { // Convert string to an IntStream of characters and process each one sequentially.
            switch (c) { // Use Java 21 enhanced switch expression for clean, break-free routing of commands.
                case 'U' -> pos[1]++; // If the character is 'U' (Up), increment the Y-axis coordinate.
                case 'D' -> pos[1]--; // If the character is 'D' (Down), decrement the Y-axis coordinate.
                case 'R' -> pos[0]++; // If the character is 'R' (Right), increment the X-axis coordinate.
                case 'L' -> pos[0]--; // If the character is 'L' (Left), decrement the X-axis coordinate.
                // We omit a default case because all non-matching (invalid) characters should simply be ignored.
            } // End of switch block.
        }); // End of stream iteration.
        
        return new Integer[]{pos[0], pos[1]}; // Box the primitive integer results into the required Object-based Integer array and return.
    } // End of the walk logic method.

    public static boolean checkEqual(Integer[] a, Integer[] b) { // Helper validation method to compare two arrays.
        return Arrays.equals(a, b); // Utilizes the standard Java library to perform a deep value check between arrays.
    } // End of validation method.

    public static boolean doTestsPass() { // The central test suite runner method.
        boolean res = true; // Initialize a flag to track the overall passing state of all test cases combined.
        
        String test1 = "UUU"; // Define test case 1: simple continuous movement on one axis.
        Integer[] result1 = walk(test1); // Execute the walk method with test 1 data.
        res &= checkEqual(result1, new Integer[]{0, 3}); // Assert robot moved 3 units up; use bitwise AND to compound test results.
        
        String test2 = "ULDR"; // Define test case 2: a circular path that should return to origin.
        Integer[] result2 = walk(test2); // Execute the walk method with test 2 data.
        res &= checkEqual(result2, new Integer[]{0, 0}); // Assert robot is back at (0,0).
        
        String test3 = "ULLLDUDUURLRLR"; // Define test case 3: a complex, overlapping set of movements.
        Integer[] result3 = walk(test3); // Execute the walk method with test 3 data.
        res &= checkEqual(result3, new Integer[]{-2, 2}); // Assert robot ends at calculated position (-2, 2).
        
        String test4 = "UP LEFT 2xDOWN DOWN RIGHT RIGHT UP UP"; // Define test case 4: input contaminated with invalid words and numbers.
        Integer[] result4 = walk(test4); // Execute the walk method with test 4 data.
        res &= checkEqual(result4, new Integer[]{1, 1}); // Assert robot successfully ignored invalid chars and landed at (1, 1).

        String test5 = "U".repeat(500000) + "R".repeat(500000) + "D".repeat(500000) + "L".repeat(500000); // Define test case 5: Very large data input (2 million commands).
        Integer[] result5 = walk(test5); // Execute the walk method on the massive string.
        res &= checkEqual(result5, new Integer[]{0, 0}); // Assert the highly scaled square path successfully returns to origin without timing out.

        return res; // Return the final compounded boolean result of all tests.
    } // End of test runner method.

    public static void main(String[] args) { // Main execution entry point for the application.
        if (doTestsPass()) { // Invoke the test runner and evaluate if all assertions held true.
            System.out.println("All tests pass"); // Output success confirmation to the console.
        } else { // Fallback branch if any test failed.
            System.out.println("There are test failures"); // Output failure warning to the console.
        } // End of conditional check.
    } // End of main method.
} // End of Solution class.