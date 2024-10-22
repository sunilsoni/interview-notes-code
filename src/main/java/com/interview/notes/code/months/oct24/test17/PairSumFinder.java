package com.interview.notes.code.months.oct24.test17;

import java.util.HashSet;

public class PairSumFinder {
    // Method to find if a pair exists
    public static boolean findPair(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (set.contains(complement)) {
                System.out.println("Pair found (" + complement + ", " + num + ")");
                return true;
            }
            set.add(num);
        }
        System.out.println("Pair not found");
        return false;
    }

    // Test method to validate multiple cases
    public static void runTests() {
        // Test Case 1
        int[] nums1 = {8, 7, 2, 5, 3, 1};
        int target1 = 10;
        System.out.println("Test Case 1: " + (findPair(nums1, target1) ? "PASS" : "FAIL"));

        // Test Case 2
        int[] nums2 = {5, 2, 6, 8, 1, 9};
        int target2 = 12;
        System.out.println("Test Case 2: " + (findPair(nums2, target2) ? "PASS" : "FAIL"));

        // Additional Test Case - No pair
        int[] nums3 = {1, 2, 3, 4};
        int target3 = 10;
        System.out.println("Test Case 3: " + (findPair(nums3, target3) ? "PASS" : "FAIL"));

        // Edge Test Case - Empty Array
        int[] nums4 = {};
        int target4 = 5;
        System.out.println("Test Case 4: " + (findPair(nums4, target4) ? "PASS" : "FAIL"));

        // Edge Test Case - Large Dataset
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i + 1;
        }
        int target5 = 1999999;
        System.out.println("Test Case 5 (Large Dataset): " + (findPair(largeArray, target5) ? "PASS" : "FAIL"));
    }

    // Main method
    public static void main(String[] args) {
        runTests();
    }
}
