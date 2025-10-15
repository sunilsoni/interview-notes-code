package com.interview.notes.code.year.y2025.october.microsoft.test3;

public class MaximumAverageSubarray {
    public static void main(String[] args) {
        // Test cases
        testMaxAverage(new int[]{1, 2, 3, 4, 5}, 3);
    }
    
    public static double findMaxAverage(int[] nums, int k) {
        // Input validation
        if (nums == null || nums.length < k) {
            return 0.0;
        }
        
        // Initialize sum and maxSum with first element
        double sum = nums[0];
        double maxSum = 0; // Declare maxSum variable
        
        // Calculate sum using single loop
        // This loop handles both initial window and sliding window
        for (int i = 1; i < nums.length; i++) {
            sum += nums[i];  // Add current element
            
            // When we have k elements, start comparing with maxSum
            if (i >= k) {
                sum -= nums[i - k];  // Remove element outside window
            }
            
            // Update maxSum when we have a complete window
            if (i >= k - 1) {
                maxSum = (i == k - 1) ? sum : Math.max(maxSum, sum);
            }
        }
        
        return maxSum / k;
    }

    private static void testMaxAverage(int[] nums, int k) {
        double result = findMaxAverage(nums, k);
        
        // Detailed output for visualization
        System.out.println("Input Array: " + java.util.Arrays.toString(nums));
        System.out.println("Window Size (k): " + k);
        System.out.println("Maximum Average: " + result);
        System.out.println("\nWindow by window calculation:");
        
        // Show each window's calculation
        for (int i = 0; i <= nums.length - k; i++) {
            double windowSum = 0;
            System.out.print("[");
            for (int j = 0; j < k; j++) {
                windowSum += nums[i + j];
                System.out.print(nums[i + j]);
                if (j < k - 1) System.out.print(",");
            }
            System.out.println("] = " + (windowSum/k));
        }
    }
}
