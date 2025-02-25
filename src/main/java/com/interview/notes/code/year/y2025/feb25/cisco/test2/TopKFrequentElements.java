package com.interview.notes.code.year.y2025.feb25.cisco.test2;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TopKFrequentElements {
    
    public static int[] topKFrequent(int[] nums, int k) {
        // Count frequency of each element using a HashMap
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }
        
        // Sort entries by frequency (descending) and get top k elements
        return frequencyMap.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .limit(k)
                .mapToInt(Map.Entry::getKey)
                .toArray();
    }
    
    // Helper method to check if arrays contain the same elements (ignoring order)
    private static boolean areArraysEquivalent(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        
        int[] sorted1 = Arrays.copyOf(arr1, arr1.length);
        int[] sorted2 = Arrays.copyOf(arr2, arr2.length);
        Arrays.sort(sorted1);
        Arrays.sort(sorted2);
        
        return Arrays.equals(sorted1, sorted2);
    }
    
    public static void main(String[] args) {
        // Test case 1
        int[] nums1 = {1, 2, 2, 3, 3, 3};
        int k1 = 2;
        int[] expected1 = {2, 3};
        int[] result1 = topKFrequent(nums1, k1);
        System.out.println("Test case 1: " + 
                          (areArraysEquivalent(result1, expected1) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + Arrays.toString(expected1) + 
                          ", Got: " + Arrays.toString(result1));
        
        // Test case 2
        int[] nums2 = {7, 7};
        int k2 = 1;
        int[] expected2 = {7};
        int[] result2 = topKFrequent(nums2, k2);
        System.out.println("Test case 2: " + 
                          (areArraysEquivalent(result2, expected2) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + Arrays.toString(expected2) + 
                          ", Got: " + Arrays.toString(result2));
        
        // Edge case: empty array
        int[] nums3 = {};
        int k3 = 0;
        int[] expected3 = {};
        int[] result3 = topKFrequent(nums3, k3);
        System.out.println("Edge case - empty array: " + 
                          (areArraysEquivalent(result3, expected3) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + Arrays.toString(expected3) + 
                          ", Got: " + Arrays.toString(result3));
        
        // Edge case: k equals the number of distinct elements
        int[] nums4 = {1, 2, 3, 4};
        int k4 = 4;
        int[] expected4 = {1, 2, 3, 4};
        int[] result4 = topKFrequent(nums4, k4);
        System.out.println("Edge case - k equals distinct elements: " + 
                          (areArraysEquivalent(result4, expected4) ? "PASS" : "FAIL"));
        System.out.println("Expected: " + Arrays.toString(expected4) + 
                          ", Got: " + Arrays.toString(result4));
        
        // Edge case: elements with equal frequencies
        int[] nums5 = {1, 1, 2, 2, 3, 3};
        int k5 = 2;
        // Any two of {1, 2, 3} would be correct
        int[] result5 = topKFrequent(nums5, k5);
        boolean validResult5 = result5.length == 2 && 
                              (result5[0] == 1 || result5[0] == 2 || result5[0] == 3) &&
                              (result5[1] == 1 || result5[1] == 2 || result5[1] == 3) &&
                              result5[0] != result5[1];
        System.out.println("Edge case - equal frequencies: " + 
                          (validResult5 ? "PASS" : "FAIL"));
        System.out.println("Got: " + Arrays.toString(result5));
        
        // Large data test
        int[] largeArray = new int[100000];
        Random random = new Random(42); // Fixed seed for reproducibility
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = random.nextInt(1000); // Random numbers between 0-999
        }
        
        long startTime = System.currentTimeMillis();
        int[] largeResult = topKFrequent(largeArray, 10);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Large data test (100,000 elements): " + 
                          (largeResult.length == 10 ? "PASS" : "FAIL") + 
                          " (Execution time: " + (endTime - startTime) + "ms)");
        
        // Print the top 10 most frequent elements in the large array
        System.out.println("Top 10 most frequent elements in large array: " + 
                          Arrays.toString(largeResult));
    }
}