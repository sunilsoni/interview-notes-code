package com.interview.notes.code.months.oct24.test18;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class DuplicateRemover {

    public static int[] removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        // Use a Set to keep track of unique elements
        Set<Integer> uniqueSet = new LinkedHashSet<>();

        // Add all elements to the set
        for (int num : nums) {
            uniqueSet.add(num);
        }

        // Convert set back to array
        return uniqueSet.stream().mapToInt(Integer::intValue).toArray();
    }

    public static void main(String[] args) {
        testRemoveDuplicates();
    }

    public static void testRemoveDuplicates() {
        // Test case 1
        int[] input1 = {1, 1, 2, 2, 1, 1, 2, 2};
        int[] expected1 = {1, 2};
        testCase(input1, expected1, "Test Case 1");

        // Test case 2: Empty array
        int[] input2 = {};
        int[] expected2 = {};
        testCase(input2, expected2, "Test Case 2 (Empty Array)");

        // Test case 3: Array with all unique elements
        int[] input3 = {1, 2, 3, 4, 5};
        int[] expected3 = {1, 2, 3, 4, 5};
        testCase(input3, expected3, "Test Case 3 (All Unique)");

        // Test case 4: Array with all duplicate elements
        int[] input4 = {1, 1, 1, 1, 1};
        int[] expected4 = {1};
        testCase(input4, expected4, "Test Case 4 (All Duplicates)");

        // Test case 5: Large array
        int[] input5 = new int[100000];
        for (int i = 0; i < 100000; i++) {
            input5[i] = i % 1000; // This will create some duplicates
        }
        int[] expected5 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            expected5[i] = i;
        }
        testCase(input5, expected5, "Test Case 5 (Large Array)");
    }

    private static void testCase(int[] input, int[] expected, String testName) {
        int[] result = removeDuplicates(input);
        boolean passed = Arrays.equals(result, expected);
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Expected: " + Arrays.toString(expected));
            System.out.println("  Got: " + Arrays.toString(result));
        }
    }
}
