package com.interview.notes.code.year.y2025.december.common.test1;

import java.util.*;

public class SubsetSumFinder {
    
    /**
     * Method 1: Dynamic Programming Approach
     * Checks if any subset of array elements sums to target
     * Time Complexity: O(n * target), Space Complexity: O(target)
     */
    public static boolean hasSubsetWithSum(int[] arr, int targetSum) {
        // Handle edge case: null or empty array
        if (arr == null || arr.length == 0) {
            // Empty array can only make sum 0
            return targetSum == 0;
        }
        
        // Handle edge case: target is 0 (empty subset always sums to 0)
        if (targetSum == 0) {
            return true;
        }
        
        // Handle negative target (cannot achieve with positive numbers)
        if (targetSum < 0) {
            return false;
        }
        
        // Create a set to store all possible sums we can make
        // Using Set for O(1) lookup and automatic duplicate handling
        Set<Integer> possibleSums = new HashSet<>();
        
        // Add 0 to set (we can always make sum 0 by choosing nothing)
        possibleSums.add(0);
        
        // Process each element in the array
        for (int i = 0; i < arr.length; i++) {
            // Get current element
            int currentNum = arr[i];
            
            // Create temporary set to store new sums
            // We need temp set because we cannot modify set while iterating
            Set<Integer> newSums = new HashSet<>();
            
            // For each existing sum, create new sum by adding current number
            for (Integer existingSum : possibleSums) {
                // Calculate new sum by adding current element
                int newSum = existingSum + currentNum;
                
                // Check if we found our target
                if (newSum == targetSum) {
                    // Found! Return true immediately
                    return true;
                }
                
                // Only add if sum is less than or equal to target
                // Optimization: no point tracking sums larger than target
                if (newSum <= targetSum) {
                    newSums.add(newSum);
                }
            }
            
            // Add all new sums to our main set
            possibleSums.addAll(newSums);
        }
        
        // Check if target sum exists in possible sums
        return possibleSums.contains(targetSum);
    }
    
    /**
     * Method 2: Find and return the actual subset elements
     * Returns list of elements that sum to target, or empty list if not found
     */
    public static List<Integer> findSubsetWithSum(int[] arr, int targetSum) {
        // List to store result elements
        List<Integer> result = new ArrayList<>();
        
        // Handle edge cases
        if (arr == null || arr.length == 0) {
            return result;
        }
        
        // Call recursive helper to find subset
        boolean found = findSubsetHelper(arr, targetSum, 0, result);
        
        // If not found, clear the result list
        if (!found) {
            result.clear();
        }
        
        return result;
    }
    
    /**
     * Recursive helper method to find subset
     * Uses backtracking approach to try all combinations
     */
    private static boolean findSubsetHelper(int[] arr, int remainingSum, int index, List<Integer> current) {
        // Base case: if remaining sum is 0, we found valid subset
        if (remainingSum == 0) {
            return true;
        }
        
        // Base case: if we processed all elements or sum became negative
        if (index >= arr.length || remainingSum < 0) {
            return false;
        }
        
        // Choice 1: Include current element in subset
        current.add(arr[index]);
        
        // Recursively try to find remaining sum with rest of elements
        if (findSubsetHelper(arr, remainingSum - arr[index], index + 1, current)) {
            // Found valid subset including current element
            return true;
        }
        
        // Choice 2: Exclude current element (backtrack)
        current.remove(current.size() - 1);
        
        // Try without current element
        // Found valid subset excluding current element
        return findSubsetHelper(arr, remainingSum, index + 1, current);
        
        // No valid subset found from this path
    }
    
    /**
     * Method 3: Find ALL subsets that sum to target
     * Returns list of all possible combinations
     */
    public static List<List<Integer>> findAllSubsetsWithSum(int[] arr, int targetSum) {
        // List to store all valid subsets
        List<List<Integer>> allSubsets = new ArrayList<>();
        
        // Current subset being built
        List<Integer> currentSubset = new ArrayList<>();
        
        // Call helper to find all subsets
        findAllSubsetsHelper(arr, targetSum, 0, currentSubset, allSubsets);
        
        return allSubsets;
    }
    
    /**
     * Helper method to find all subsets recursively
     */
    private static void findAllSubsetsHelper(int[] arr, int remainingSum, int index, 
                                              List<Integer> current, List<List<Integer>> allSubsets) {
        // Base case: found valid subset
        if (remainingSum == 0) {
            // Add copy of current subset to results
            allSubsets.add(new ArrayList<>(current));
            return;
        }
        
        // Base case: invalid path
        if (index >= arr.length || remainingSum < 0) {
            return;
        }
        
        // Choice 1: Include current element
        current.add(arr[index]);
        findAllSubsetsHelper(arr, remainingSum - arr[index], index + 1, current, allSubsets);
        
        // Choice 2: Exclude current element (backtrack)
        current.remove(current.size() - 1);
        findAllSubsetsHelper(arr, remainingSum, index + 1, current, allSubsets);
    }
    
    /**
     * Test helper method to verify expected vs actual result
     */
    public static void runTestCase(int testNumber, int[] arr, int target, boolean expected) {
        // Print test case header
        System.out.println("===========================================");
        System.out.println("Test Case " + testNumber + ":");
        
        // Print array (truncate if too large)
        if (arr.length <= 20) {
            System.out.println("  Array: " + Arrays.toString(arr));
        } else {
            System.out.println("  Array: [" + arr.length + " elements] (too large to display)");
        }
        
        // Print target and expected result
        System.out.println("  Target Sum: " + target);
        System.out.println("  Expected: " + expected);
        
        // Run the subset sum method
        boolean actualResult = hasSubsetWithSum(arr, target);
        
        // Print actual result
        System.out.println("  Actual: " + actualResult);
        
        // If found, show the actual subset elements
        if (actualResult && arr.length <= 20) {
            List<Integer> subset = findSubsetWithSum(arr, target);
            System.out.println("  Subset Found: " + subset);
            
            // Verify the sum
            int sum = 0;
            for (int num : subset) {
                sum = sum + num;
            }
            System.out.println("  Verification: " + subset + " = " + sum);
        }
        
        // Compare and print PASS or FAIL
        if (actualResult == expected) {
            System.out.println("  Result: *** PASS ***");
        } else {
            System.out.println("  Result: *** FAIL ***");
        }
        System.out.println();
    }
    
    /**
     * Main method to run all test cases
     */
    public static void main(String[] args) {
        System.out.println("SUBSET SUM FINDER (ANY POSITION) - TEST RESULTS");
        System.out.println("================================================\n");
        
        // ============ ORIGINAL PROBLEM TEST ============
        // Test Case 1: Original problem - [8,10,15,7,6,2] with target 17
        // Possible subsets: 8+7+2=17, 10+7=17, 15+2=17
        int[] arr1 = {8, 10, 15, 7, 6, 2};
        runTestCase(1, arr1, 17, true);
        
        // Show ALL subsets that sum to 17
        System.out.println("  All subsets summing to 17:");
        List<List<Integer>> allSubsets = findAllSubsetsWithSum(arr1, 17);
        for (List<Integer> subset : allSubsets) {
            int sum = 0;
            for (int n : subset) sum += n;
            System.out.println("    " + subset + " = " + sum);
        }
        System.out.println();
        
        // ============ BASIC TEST CASES ============
        
        // Test Case 2: Simple case - target equals single element
        int[] arr2 = {3, 5, 7, 9};
        runTestCase(2, arr2, 7, true);
        
        // Test Case 3: Sum of all elements
        // 3+5+7+9 = 24
        int[] arr3 = {3, 5, 7, 9};
        runTestCase(3, arr3, 24, true);
        
        // Test Case 4: Target not achievable
        // Array [1, 2, 3], target 7 (max sum is 6)
        int[] arr4 = {1, 2, 3};
        runTestCase(4, arr4, 7, false);
        
        // Test Case 5: Target is zero (empty subset)
        int[] arr5 = {5, 10, 15};
        runTestCase(5, arr5, 0, true);
        
        // Test Case 6: Empty array with non-zero target
        int[] arr6 = {};
        runTestCase(6, arr6, 10, false);
        
        // Test Case 7: Single element equals target
        int[] arr7 = {17};
        runTestCase(7, arr7, 17, true);
        
        // Test Case 8: Single element not equal to target
        int[] arr8 = {5};
        runTestCase(8, arr8, 17, false);
        
        // Test Case 9: Multiple ways to achieve target
        // 1+4=5, 2+3=5, 5=5
        int[] arr9 = {1, 2, 3, 4, 5};
        runTestCase(9, arr9, 5, true);
        
        // Show all subsets for test case 9
        System.out.println("  All subsets summing to 5:");
        List<List<Integer>> allSubsets9 = findAllSubsetsWithSum(arr9, 5);
        for (List<Integer> subset : allSubsets9) {
            System.out.println("    " + subset);
        }
        System.out.println();
        
        // Test Case 10: Two elements needed
        int[] arr10 = {10, 20, 30, 40};
        runTestCase(10, arr10, 50, true);  // 10+40 or 20+30
        
        // ============ EDGE CASES ============
        System.out.println("\n========== EDGE CASES ==========\n");
        
        // Test Case 11: All same elements
        int[] arr11 = {5, 5, 5, 5};
        runTestCase(11, arr11, 15, true);  // 5+5+5
        
        // Test Case 12: Negative target (impossible with positive numbers)
        int[] arr12 = {1, 2, 3};
        runTestCase(12, arr12, -5, false);
        
        // Test Case 13: Large single element
        int[] arr13 = {100, 200, 300};
        runTestCase(13, arr13, 17, false);
        
        // Test Case 14: Exact match with multiple elements
        int[] arr14 = {1, 5, 11, 5};
        runTestCase(14, arr14, 11, true);  // Single element 11
        
        // ============ LARGE DATA TEST CASES ============
        System.out.println("\n========== LARGE DATA TEST CASES ==========\n");
        
        // Test Case 15: Medium sized array
        int[] arr15 = new int[50];
        for (int i = 0; i < 50; i++) {
            arr15[i] = i + 1;  // 1 to 50
        }
        // Target 100 can be achieved: 1+2+3+...+13+9 = 100 or many other ways
        long startTime15 = System.currentTimeMillis();
        runTestCase(15, arr15, 100, true);
        long endTime15 = System.currentTimeMillis();
        System.out.println("  Execution Time: " + (endTime15 - startTime15) + " ms\n");
        
        // Test Case 16: Large array with small target
        int[] arr16 = new int[1000];
        for (int i = 0; i < 1000; i++) {
            arr16[i] = 1;  // All 1s
        }
        long startTime16 = System.currentTimeMillis();
        runTestCase(16, arr16, 17, true);  // Sum of any 17 elements
        long endTime16 = System.currentTimeMillis();
        System.out.println("  Execution Time: " + (endTime16 - startTime16) + " ms\n");
        
        // Test Case 17: Large array - target impossible
        int[] arr17 = new int[100];
        for (int i = 0; i < 100; i++) {
            arr17[i] = 10;  // All 10s, so only multiples of 10 possible
        }
        long startTime17 = System.currentTimeMillis();
        runTestCase(17, arr17, 17, false);  // 17 is not multiple of 10
        long endTime17 = System.currentTimeMillis();
        System.out.println("  Execution Time: " + (endTime17 - startTime17) + " ms\n");
        
        // Test Case 18: Performance test with larger target
        int[] arr18 = new int[100];
        for (int i = 0; i < 100; i++) {
            arr18[i] = i + 1;
        }
        long startTime18 = System.currentTimeMillis();
        runTestCase(18, arr18, 500, true);
        long endTime18 = System.currentTimeMillis();
        System.out.println("  Execution Time: " + (endTime18 - startTime18) + " ms\n");
        
        // ============ SUMMARY ============
        System.out.println("===========================================");
        System.out.println("ALL TEST CASES COMPLETED!");
        System.out.println("===========================================");
    }
}