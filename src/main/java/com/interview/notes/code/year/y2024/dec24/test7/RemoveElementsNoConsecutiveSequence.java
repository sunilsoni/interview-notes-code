package com.interview.notes.code.year.y2024.dec24.test7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveElementsNoConsecutiveSequence {

    /*
     * Implement method/function with name 'solve' below.
     * The function accepts following as parameters.
     * 1. ar is of type List<Integer>.
     * return int.
     */
    public static int solve(List<Integer> ar) {
        int n = ar.size();
        if (n < 3) {
            return 0; // No need to remove elements if the array has less than 3 elements
        }

        int res = 0; // Number of elements to remove
        List<Integer> indices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            indices.add(i);
        }

        List<Integer> newIndices = new ArrayList<>();

        for (int idx : indices) {
            newIndices.add(idx);
            int size = newIndices.size();
            if (size >= 3) {
                int idx1 = newIndices.get(size - 3);
                int idx2 = newIndices.get(size - 2);
                int idx3 = newIndices.get(size - 1);

                int a = ar.get(idx1);
                int b = ar.get(idx2);
                int c = ar.get(idx3);

                if ((a < b && b < c) || (a > b && b > c)) {
                    // Remove the middle element
                    newIndices.remove(size - 2);
                    res++;
                }
            }
        }
        return res; // Return the number of elements to remove
    }

    // Main method for testing
    public static void main(String[] args) {
        testSolve();
        testLargeInput();
    }

    // Method to test the solve function with various test cases
    private static void testSolve() {
        // Test Case 1
        List<Integer> ar1 = Arrays.asList(1, 2, 4, 1, 2);
        int result1 = solve(ar1);
        System.out.println("Test Case 1 Output:");
        System.out.println(result1); // Expected Output: 1

        // Test Case 2
        List<Integer> ar2 = Arrays.asList(1, 2, 3, 5);
        int result2 = solve(ar2);
        System.out.println("Test Case 2 Output:");
        System.out.println(result2); // Expected Output: 2

        // Test Case 3: No removal needed
        List<Integer> ar3 = Arrays.asList(5, 1, 3, 2);
        int result3 = solve(ar3);
        System.out.println("Test Case 3 Output:");
        System.out.println(result3); // Expected Output: 0

        // Test Case 4: All increasing
        List<Integer> ar4 = Arrays.asList(1, 2, 3, 4, 5);
        int result4 = solve(ar4);
        System.out.println("Test Case 4 Output:");
        System.out.println(result4); // Expected Output: 3

        // Test Case 5: All decreasing
        List<Integer> ar5 = Arrays.asList(5, 4, 3, 2, 1);
        int result5 = solve(ar5);
        System.out.println("Test Case 5 Output:");
        System.out.println(result5); // Expected Output: 3

        // Test Case 6: Alternating increase and decrease
        List<Integer> ar6 = Arrays.asList(1, 3, 2, 4, 3, 5);
        int result6 = solve(ar6);
        System.out.println("Test Case 6 Output:");
        System.out.println(result6); // Expected Output: 0

        // Test Case 7: Equal elements (not possible due to distinct integers constraint)
        // Skipped since elements are distinct

        // Test Case 8: Minimum size array
        List<Integer> ar8 = Arrays.asList(1, 2);
        int result8 = solve(ar8);
        System.out.println("Test Case 8 Output:");
        System.out.println(result8); // Expected Output: 0

        // Test Case 9: Array with size 3 needing removal
        List<Integer> ar9 = Arrays.asList(3, 2, 1);
        int result9 = solve(ar9);
        System.out.println("Test Case 9 Output:");
        System.out.println(result9); // Expected Output: 1

        // Test Case 10: Larger array with mixed sequences
        List<Integer> ar10 = Arrays.asList(10, 9, 8, 7, 8, 9, 10, 11);
        int result10 = solve(ar10);
        System.out.println("Test Case 10 Output:");
        System.out.println(result10); // Expected Output: 2
    }

    // Method to test the solve function with large input
    private static void testLargeInput() {
        int n = 10000;
        List<Integer> ar = new ArrayList<>(n);

        // Generate a sequence that alternates between increasing and decreasing
        for (int i = 0; i < n; i++) {
            ar.add(i % 2 == 0 ? i : -i);
        }

        // Record start time
        long startTime = System.currentTimeMillis();

        int result = solve(ar);

        // Record end time
        long endTime = System.currentTimeMillis();

        System.out.println("Large Test Case Output:");
        System.out.println("Number of elements to remove: " + result + " (Time taken: " + (endTime - startTime) + " ms)");
        // Expected Output: 0 (since the sequence doesn't have three increasing or decreasing elements consecutively)
    }
}