package com.interview.notes.code.year.y2025.august.common.test3;

import java.util.*;

public class MissingNumbers {
    private static final int THRESHOLD = 10000; // Threshold to switch algorithms

    public static List<Integer> findMissingNumbers(List<Integer> nums) {
        if (nums == null || nums.isEmpty()) {
            return new ArrayList<>();
        }

        int maxNum = Collections.max(nums);
        int size = nums.size();

        // Choose algorithm based on data characteristics
        if (maxNum - size > THRESHOLD) {
            return findMissingNumbersSparse(nums, maxNum);
        } else {
            return findMissingNumbersDense(nums, maxNum);
        }
    }

    // For dense sequences (when max number is close to size)
    private static List<Integer> findMissingNumbersDense(List<Integer> nums, int maxNum) {
        boolean[] present = new boolean[maxNum + 1];
        List<Integer> missing = new ArrayList<>();

        // Mark present numbers
        for (int num : nums) {
            present[num] = true;
        }

        // Collect missing numbers
        for (int i = 0; i <= maxNum; i++) {
            if (!present[i]) {
                missing.add(i);
            }
        }
        return missing;
    }

    // For sparse sequences (when max number is much larger than size)
    private static List<Integer> findMissingNumbersSparse(List<Integer> nums, int maxNum) {
        Set<Integer> numSet = new HashSet<>(nums);
        List<Integer> missing = new ArrayList<>();
        
        // Track ranges of missing numbers
        int start = 0;
        int prev = -1;

        for (int current : new TreeSet<>(nums)) {
            // Fill gap between prev and current
            for (int i = prev + 1; i < current; i++) {
                missing.add(i);
            }
            prev = current;
        }

        return missing;
    }

    public static void main(String[] args) {
        // Test with dense data
        testDenseData();
        
        // Test with sparse data
        testSparseData();
    }

    private static void testDenseData() {
        System.out.println("Testing dense data:");
        List<Integer> denseInput = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            if (i != 500 && i != 800) { // Leave two numbers missing
                denseInput.add(i);
            }
        }
        
        long startTime = System.currentTimeMillis();
        List<Integer> result = findMissingNumbers(denseInput);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Missing numbers: " + result);
    }

    private static void testSparseData() {
        System.out.println("\nTesting sparse data:");
        List<Integer> sparseInput = Arrays.asList(0, 1000000, 2000000, 5000000);
        
        long startTime = System.currentTimeMillis();
        List<Integer> result = findMissingNumbers(sparseInput);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("First few missing numbers: " + 
            result.subList(0, Math.min(10, result.size())) + "...");
        System.out.println("Total missing numbers: " + result.size());
    }
}
