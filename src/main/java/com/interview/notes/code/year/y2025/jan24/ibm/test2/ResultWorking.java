package com.interview.notes.code.year.y2025.jan24.ibm.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultWorking {
    public static void main(String[] args) {
        // Run test cases
        runTests();
    }

    public static List<Integer> getOptimalPriority(List<Integer> priority) {
        if (priority == null || priority.size() <= 1) {
            return priority;
        }

        // Create a copy to work with
        List<Integer> result = new ArrayList<>(priority);
        boolean improved = true;

        while (improved) {
            improved = false;
            for (int i = 0; i < result.size() - 1; i++) {
                // Check if adjacent pair can be swapped (one odd, one even)
                if (canSwap(result.get(i), result.get(i + 1))) {
                    // Check if swapping gives lexicographically smaller sequence
                    if (result.get(i) > result.get(i + 1)) {
                        // Swap elements
                        int temp = result.get(i);
                        result.set(i, result.get(i + 1));
                        result.set(i + 1, temp);
                        improved = true;
                    }
                }
            }
        }
        return result;
    }

    private static boolean canSwap(int a, int b) {
        // One number should be odd and other even for valid swap
        return (a % 2 == 0 && b % 2 == 1) || (a % 2 == 1 && b % 2 == 0);
    }

    private static void runTests() {
        // Test Case 1: Sample case [0,7,0,9]
        test(Arrays.asList(0, 7, 0, 9), Arrays.asList(0, 0, 7, 9), "Test 1");

        // Test Case 2: Sample case [9,4,8,6,3]
        test(Arrays.asList(9, 4, 8, 6, 3), Arrays.asList(4, 8, 6, 9, 3), "Test 2");

        // Test Case 3: Edge case - single element
        test(Arrays.asList(5), Arrays.asList(5), "Test 3");

        // Test Case 4: Edge case - already optimal
        test(Arrays.asList(2, 3, 4, 5), Arrays.asList(2, 3, 4, 5), "Test 4");

        // Test Case 5: Large case with repeating numbers
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(i % 10);
        }
        test(largeInput, null, "Test 5 (Large Input)");
    }

    private static void test(List<Integer> input, List<Integer> expected, String testName) {
        try {
            List<Integer> result = getOptimalPriority(input);
            if (expected == null || result.equals(expected)) {
                System.out.println(testName + ": PASS");
            } else {
                System.out.println(testName + ": FAIL");
                System.out.println("Expected: " + expected);
                System.out.println("Got: " + result);
            }
        } catch (Exception e) {
            System.out.println(testName + ": FAIL (Exception: " + e.getMessage() + ")");
        }
    }
}
