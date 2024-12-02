package com.interview.notes.code.year.y2024.oct24.test16;

import java.util.HashSet;
import java.util.Set;

public class PairSum {

    public static String findPair(int[] nums, int target) {
        Set<Integer> complementSet = new HashSet<>();

        for (int num : nums) {
            int complement = target - num;
            if (complementSet.contains(complement)) {
                return String.format("Pair found (%d, %d)", complement, num);
            }
            complementSet.add(num);
        }

        return "Pair not found";
    }

    public static void main(String[] args) {
        runTests();
    }

    public static void runTests() {
        // Test case 1
        int[] nums1 = {8, 7, 2, 5, 3, 1};
        int target1 = 10;
        String expected1 = "Pair found (8, 2)";
        testCase(nums1, target1, expected1);

        // Test case 2
        int[] nums2 = {5, 2, 6, 8, 1, 9};
        int target2 = 12;
        String expected2 = "Pair not found";
        testCase(nums2, target2, expected2);

        // Test case 3: Empty array
        int[] nums3 = {};
        int target3 = 5;
        String expected3 = "Pair not found";
        testCase(nums3, target3, expected3);

        // Test case 4: Large array
        int[] nums4 = new int[1000000];
        for (int i = 0; i < nums4.length; i++) {
            nums4[i] = i;
        }
        int target4 = 999999;
        String expected4 = "Pair found (0, 999999)";
        testCase(nums4, target4, expected4);

        // Test case 5: Duplicate numbers
        int[] nums5 = {1, 2, 3, 4, 4, 5};
        int target5 = 8;
        String expected5 = "Pair found (3, 5)";
        testCase(nums5, target5, expected5);
    }

    public static void testCase(int[] nums, int target, String expected) {
        String result = findPair(nums, target);
        boolean passed = result.equals(expected);
        System.out.println("Test case: " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("  Expected: " + expected);
            System.out.println("  Actual:   " + result);
        }
    }
}
