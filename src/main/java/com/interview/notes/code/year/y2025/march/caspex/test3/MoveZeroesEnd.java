package com.interview.notes.code.year.y2025.march.caspex.test3;

import java.util.*;
import java.util.stream.*;

public class MoveZeroesEnd {
    
    /**
     * This method moves all 0's to the end of the given list in-place
     * while preserving the order of non-zero elements.
     *
     * @param nums The list of integers to process.
     * @return The same list (in-place modified) with zeroes moved to the end.
     */
    public static List<Integer> solve(List<Integer> nums) {
        // 1) Create a pointer to track where the next non-zero element should go.
        int insertPos = 0;  
        
        // 2) Loop through the list
        for (int i = 0; i < nums.size(); i++) {
            // 3) If element is not zero, place it at 'insertPos' and increment 'insertPos'.
            if (nums.get(i) != 0) {
                nums.set(insertPos, nums.get(i));
                insertPos++;
            }
        }
        
        // 4) After we have moved all non-zero elements, fill the rest with zeros.
        while (insertPos < nums.size()) {
            nums.set(insertPos, 0);
            insertPos++;
        }
        
        // 5) Return the modified list (we have done an in-place operation).
        return nums;
    }
    
    /**
     * Main method to run test cases and show PASS/FAIL status.
     */
    public static void main(String[] args) {
        // We will store test results here:
        Map<String, Boolean> testResults = new LinkedHashMap<>();

        // Test Case 1
        // Input: 0 1 0 3 12
        // Expected output: 1 3 12 0 0
        {
            List<Integer> nums = new ArrayList<>(Arrays.asList(0, 1, 0, 3, 12));
            List<Integer> result = solve(nums);
            List<Integer> expected = Arrays.asList(1, 3, 12, 0, 0);
            testResults.put("Test Case 1", result.equals(expected));
        }

        // Test Case 2
        // Input: 0
        // Expected output: 0
        {
            List<Integer> nums = new ArrayList<>(Arrays.asList(0));
            List<Integer> result = solve(nums);
            List<Integer> expected = Arrays.asList(0);
            testResults.put("Test Case 2", result.equals(expected));
        }

        // Test Case 3 - all non-zero
        // Input: 1 2 3
        // Expected output: 1 2 3 (unchanged)
        {
            List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3));
            List<Integer> result = solve(nums);
            List<Integer> expected = Arrays.asList(1, 2, 3);
            testResults.put("Test Case 3 (All Non-zero)", result.equals(expected));
        }

        // Test Case 4 - all zeros
        // Input: 0 0 0
        // Expected output: 0 0 0 (unchanged)
        {
            List<Integer> nums = new ArrayList<>(Arrays.asList(0, 0, 0));
            List<Integer> result = solve(nums);
            List<Integer> expected = Arrays.asList(0, 0, 0);
            testResults.put("Test Case 4 (All Zero)", result.equals(expected));
        }

        // Test Case 5 - Mixed large data
        // We'll quickly create a big list with random zeros and non-zeros, then
        // just check that the count of zeros remains the same and the relative order of non-zeros is correct.
        {
            List<Integer> largeData = new ArrayList<>();
            // For illustration, let's create 100 elements, but you can do a million or more for stress tests.
            for(int i = 0; i < 100; i++){
                // Randomly put zero or a value i
                if(i % 10 == 0) {
                    largeData.add(0);
                } else {
                    largeData.add(i);
                }
            }
            // Make a copy of the original data to check final arrangement
            List<Integer> originalCopy = new ArrayList<>(largeData);
            
            solve(largeData); // in-place modification
            
            // Validation 1: The number of zeros should be the same
            long originalZeroCount = originalCopy.stream().filter(x -> x == 0).count();
            long newZeroCount = largeData.stream().filter(x -> x == 0).count();
            boolean sameZeroCount = (originalZeroCount == newZeroCount);

            // Validation 2: The relative order of non-zero elements is kept
            // Extract the non-zero elements from original and final results and compare
            List<Integer> originalNonZero = originalCopy.stream().filter(x -> x != 0).collect(Collectors.toList());
            List<Integer> newNonZero = largeData.stream().filter(x -> x != 0).collect(Collectors.toList());
            boolean sameOrder = (originalNonZero.equals(newNonZero));

            testResults.put("Test Case 5 (Large Data)", sameZeroCount && sameOrder);
        }

        // Print test results
        for (Map.Entry<String, Boolean> entry : testResults.entrySet()) {
            System.out.println(entry.getKey() + " : " + (entry.getValue() ? "PASS" : "FAIL"));
        }
    }
}
