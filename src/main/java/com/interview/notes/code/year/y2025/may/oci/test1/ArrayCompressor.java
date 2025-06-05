package com.interview.notes.code.year.y2025.may.oci.test1;

import java.util.*;

public class ArrayCompressor {
    
    /**
     * Finds minimum possible length of array after merging adjacent elements
     * whose product is less than or equal to k
     */
    public static int getMinLength(List<Integer> a, int k) {
        // Create a new ArrayList to avoid modifying the input
        List<Integer> arr = new ArrayList<>(a);
        
        // Flag to track if any merging happened in current pass
        boolean merged;
        
        do {
            merged = false;
            // Iterate through array looking for mergeable pairs
            for (int i = 0; i < arr.size() - 1; i++) {
                // Check if current pair's product is within limit k
                long product = (long) arr.get(i) * arr.get(i + 1);
                if (product <= k) {
                    // Merge elements by replacing first with product
                    arr.set(i, (int) product);
                    // Remove the second element
                    arr.remove(i + 1);
                    merged = true;
                    // Break to restart from beginning with new array
                    break;
                }
            }
        } while (merged); // Continue while merges are possible
        
        return arr.size();
    }

    public static void main(String[] args) {
        // Test Case 1: Basic case
        test(Arrays.asList(2, 3, 3, 7, 3, 5), 20, 3);
        
        // Test Case 2: All elements can be merged
        test(Arrays.asList(1, 2, 1, 3, 6, 1), 6, 2);
        
        // Test Case 3: Limited merging possible
        test(Arrays.asList(1, 3, 2, 5, 4), 6, 3);
        
        // Test Case 4: Edge case - single element
        test(Arrays.asList(5), 10, 1);
        
        // Test Case 5: No merging possible
        test(Arrays.asList(10, 20, 30), 5, 3);
        
        // Test Case 6: Large numbers
        test(Arrays.asList(999999999, 999999999), 1000000000, 2);
    }
    
    /**
     * Helper method to test and validate results
     */
    private static void test(List<Integer> input, int k, int expected) {
        int result = getMinLength(input, k);
        System.out.printf("Input: %s, k=%d\n", input, k);
        System.out.printf("Expected: %d, Got: %d\n", expected, result);
        System.out.println(result == expected ? "PASS" : "FAIL");
        System.out.println("------------------------");
    }
}
