package com.interview.notes.code.year.y2025.november.common.test4;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SecondHighestNumberOptimized {

    // ============ OPTIMIZED VERSION - O(n) TIME COMPLEXITY ============
    
    public static String findSecondHighestOptimized(int[] arr) {
        
        // Step 1: Use HashMap to store each number and its frequency
        // Key: number from array, Value: count of occurrences
        // This avoids sorting and runs in O(n) time
        Map<Integer, Long> frequencyMap = Arrays.stream(arr)
                .boxed()
                .collect(Collectors.groupingBy(num -> num, Collectors.counting()));
        
        // Step 2: If less than 2 unique numbers, no second highest exists
        // This handles edge case where array has only 1 unique value
        if (frequencyMap.size() < 2) {
            return "ERROR";
        }
        
        // Step 3: Find the highest number by comparing keys
        // stream() converts map keys to stream of numbers
        // max() finds the maximum using natural ordering
        // This is O(n) where n = number of unique elements
        int highest = frequencyMap.keySet().stream()
                .max(Integer::compareTo)
                .get();
        
        // Step 4: Find second highest by excluding the highest
        // filter() removes the highest number from consideration
        // max() finds the next maximum
        // This is still O(unique) not O(n log n)
        int secondHighest = frequencyMap.keySet().stream()
                .filter(num -> num != highest)
                .max(Integer::compareTo)
                .get();
        
        // Step 5: Get frequency of second highest from map
        // get() retrieves the count in O(1) time
        long frequency = frequencyMap.get(secondHighest);
        
        // Step 6: Return formatted result
        return secondHighest + "-" + frequency;
    }

    // ============ EVEN MORE OPTIMIZED - SINGLE PASS ============
    
    public static String findSecondHighestSinglePass(int[] arr) {
        
        // Step 1: Initialize variables to track highest and second highest
        // Track values and their frequencies separately
        // This avoids creating any intermediate collections
        int highest = Integer.MIN_VALUE;           // Tracks current max number
        int secondHighest = Integer.MIN_VALUE;     // Tracks current second max
        int highestCount = 0;                       // Frequency of highest
        int secondHighestCount = 0;                 // Frequency of second highest
        
        // Step 2: Single pass through array
        // Process each element only once - O(n)
        // No sorting, no HashMap creation upfront
        for (int num : arr) {
            
            // If current number is greater than highest
            if (num > highest) {
                // Push current highest to second highest
                secondHighest = highest;
                secondHighestCount = highestCount;
                // Update highest
                highest = num;
                highestCount = 1;
            }
            // If current number equals highest
            else if (num == highest) {
                // Increment highest count
                highestCount++;
            }
            // If current number is between highest and second highest
            else if (num > secondHighest) {
                // Update second highest only if it's different from highest
                secondHighest = num;
                secondHighestCount = 1;
            }
            // If current number equals second highest
            else if (num == secondHighest) {
                // Increment second highest count
                secondHighestCount++;
            }
            // Else: current number is less than second highest (ignore)
        }
        
        // Step 3: Validation check
        // If second highest was never updated from MIN_VALUE, no second value exists
        if (secondHighest == Integer.MIN_VALUE) {
            return "ERROR";
        }
        
        // Step 4: Return result
        return secondHighest + "-" + secondHighestCount;
    }

    // Main method with comparison tests
    public static void main(String[] args) {
        
        int passCount = 0;
        int failCount = 0;
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║        OPTIMIZED SECOND HIGHEST - TEST & PERFORMANCE       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // ============ BASIC TEST CASES ============
        
        int[] test1 = {8, 9, 8, 5, 4, 8, 3, 8};
        String result1a = findSecondHighestOptimized(test1);
        String result1b = findSecondHighestSinglePass(test1);
        boolean pass1 = result1a.equals("8-4") && result1b.equals("8-4");
        System.out.println("Test 1 - Basic Example: " + (pass1 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [8, 9, 8, 5, 4, 8, 3, 8]");
        System.out.println("  Optimized (HashMap): " + result1a);
        System.out.println("  Single Pass: " + result1b + "\n");
        passCount += pass1 ? 1 : 0;
        failCount += pass1 ? 0 : 1;
        
        int[] test2 = {1, 2, 3};
        String result2a = findSecondHighestOptimized(test2);
        String result2b = findSecondHighestSinglePass(test2);
        boolean pass2 = result2a.equals("2-1") && result2b.equals("2-1");
        System.out.println("Test 2 - Simple Sequence: " + (pass2 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [1, 2, 3]");
        System.out.println("  Optimized: " + result2a + " | Single Pass: " + result2b + "\n");
        passCount += pass2 ? 1 : 0;
        failCount += pass2 ? 0 : 1;
        
        int[] test3 = {5, 5, 5, 10, 10};
        String result3a = findSecondHighestOptimized(test3);
        String result3b = findSecondHighestSinglePass(test3);
        boolean pass3 = result3a.equals("5-3") && result3b.equals("5-3");
        System.out.println("Test 3 - Multiple Duplicates: " + (pass3 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [5, 5, 5, 10, 10]");
        System.out.println("  Optimized: " + result3a + " | Single Pass: " + result3b + "\n");
        passCount += pass3 ? 1 : 0;
        failCount += pass3 ? 0 : 1;
        
        int[] test4 = {5, 5, 5, 5};
        String result4a = findSecondHighestOptimized(test4);
        String result4b = findSecondHighestSinglePass(test4);
        boolean pass4 = result4a.equals("ERROR") && result4b.equals("ERROR");
        System.out.println("Test 4 - One Unique Number: " + (pass4 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: [5, 5, 5, 5]");
        System.out.println("  Optimized: " + result4a + " | Single Pass: " + result4b + "\n");
        passCount += pass4 ? 1 : 0;
        failCount += pass4 ? 0 : 1;
        
        // ============ PERFORMANCE TEST - LARGE DATA ============
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                 PERFORMANCE COMPARISON                     ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        // Test 5: 100K elements - performance benchmark
        int[] largeArray = new int[100000];
        for (int i = 0; i < 50000; i++) largeArray[i] = 999;
        for (int i = 50000; i < 80000; i++) largeArray[i] = 888;
        for (int i = 80000; i < 100000; i++) largeArray[i] = 777;
        
        // Test Optimized HashMap version
        long time1 = System.nanoTime();
        String result5a = findSecondHighestOptimized(largeArray);
        long duration1 = System.nanoTime() - time1;
        
        // Test Single Pass version
        long time2 = System.nanoTime();
        String result5b = findSecondHighestSinglePass(largeArray);
        long duration2 = System.nanoTime() - time2;
        
        boolean pass5 = result5a.equals("888-30000") && result5b.equals("888-30000");
        System.out.println("Test 5 - Large Array (100K): " + (pass5 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 100,000 elements");
        System.out.println("  Optimized (HashMap): " + result5a + " | Time: " + (duration1/1000.0) + " µs");
        System.out.println("  Single Pass:        " + result5b + " | Time: " + (duration2/1000.0) + " µs");
        System.out.println("  Speedup: " + String.format("%.2fx", (double)duration1/duration2) + "\n");
        passCount += pass5 ? 1 : 0;
        failCount += pass5 ? 0 : 1;
        
        // Test 6: 1 Million elements - stress test
        int[] hugeArray = new int[1000000];
        for (int i = 0; i < 500000; i++) hugeArray[i] = 5000;
        for (int i = 500000; i < 800000; i++) hugeArray[i] = 4000;
        for (int i = 800000; i < 1000000; i++) hugeArray[i] = 3000;
        
        long time3 = System.nanoTime();
        String result6a = findSecondHighestOptimized(hugeArray);
        long duration3 = System.nanoTime() - time3;
        
        long time4 = System.nanoTime();
        String result6b = findSecondHighestSinglePass(hugeArray);
        long duration4 = System.nanoTime() - time4;
        
        boolean pass6 = result6a.equals("4000-300000") && result6b.equals("4000-300000");
        System.out.println("Test 6 - Huge Array (1M): " + (pass6 ? "✓ PASS" : "✗ FAIL"));
        System.out.println("  Input: 1,000,000 elements");
        System.out.println("  Optimized (HashMap): " + result6a + " | Time: " + (duration3/1000.0) + " µs");
        System.out.println("  Single Pass:        " + result6b + " | Time: " + (duration4/1000.0) + " µs");
        System.out.println("  Speedup: " + String.format("%.2fx", (double)duration3/duration4) + "\n");
        passCount += pass6 ? 1 : 0;
        failCount += pass6 ? 0 : 1;
        
        // ============ COMPLEXITY COMPARISON ============
        
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║             COMPLEXITY ANALYSIS & SUMMARY                 ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        System.out.println("Original Version (with sorted):   O(n log n) time");
        System.out.println("  - Boxes array to Integer[]");
        System.out.println("  - Calls distinct() - O(n)");
        System.out.println("  - Calls sorted() - O(n log n) ← BOTTLENECK");
        System.out.println("  - Finds second highest - O(1)");
        System.out.println("  - Counts frequency - O(n)");
        
        System.out.println("\nOptimized Version (HashMap):     O(n) time");
        System.out.println("  - Creates frequency map - O(n)");
        System.out.println("  - Finds highest - O(unique)");
        System.out.println("  - Finds second highest - O(unique)");
        System.out.println("  - Gets frequency - O(1)");
        System.out.println("  - Space: O(unique)");
        
        System.out.println("\nSingle Pass Version:             O(n) time");
        System.out.println("  - Single loop through array - O(n)");
        System.out.println("  - Track max/second max on fly");
        System.out.println("  - Space: O(1) ← MOST EFFICIENT");
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("Total Tests: " + (passCount + failCount) + " | " +
                          "Passed: " + passCount + " | Failed: " + failCount);
        if (failCount == 0) {
            System.out.println("✓ ALL TESTS PASSED - OPTIMIZED SOLUTIONS WORK");
        }
        System.out.println("═".repeat(60));
    }
}