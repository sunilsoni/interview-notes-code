package com.interview.notes.code.year.y2025.april.common.test4;

import java.util.HashMap;
import java.util.Map;

public class PairSum {
    public static void findPairs(int[] arr, int target) {
        // Input validation
        if (arr == null || arr.length < 2) {
            System.out.println("Invalid input");
            return;
        }

        // Store number and its index
        Map<Integer, Integer> map = new HashMap<>();

        // Find pairs
        for (int i = 0; i < arr.length; i++) {
            int complement = target - arr[i];
            if (map.containsKey(complement)) {
                System.out.println("(" + map.get(complement) + "," + i + ")");
            }
            map.put(arr[i], i);
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Given example
        System.out.println("Test Case 1:");
        int[] arr1 = {1, 3, 5, 6, 8, 2, 4, 7};
        int target1 = 9;
        findPairs(arr1, target1);

        // Test Case 2: Empty array
        System.out.println("\nTest Case 2 (Empty array):");
        int[] arr2 = {};
        findPairs(arr2, target1);

        // Test Case 3: No pairs sum to target
        System.out.println("\nTest Case 3 (No pairs):");
        int[] arr3 = {1, 2, 3};
        findPairs(arr3, 10);

        // Test Case 4: Duplicate numbers
        System.out.println("\nTest Case 4 (Duplicates):");
        int[] arr4 = {1, 4, 4, 5, 8};
        findPairs(arr4, 9);

        // Test Case 5: Large array
        System.out.println("\nTest Case 5 (Large array):");
        int[] arr5 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr5[i] = i;
        }
        findPairs(arr5, 9);
    }
}
