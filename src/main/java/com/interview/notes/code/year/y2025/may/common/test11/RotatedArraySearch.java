package com.interview.notes.code.year.y2025.may.common.test11;

public class RotatedArraySearch {
    
    public static int search(int[] nums, int target) {
        // Handle edge case of empty array
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        // Initialize pointers for binary search
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            // Calculate middle point
            int mid = left + (right - left) / 2;
            
            // If target found at mid, return index
            if (nums[mid] == target) {
                return mid;
            }
            
            // Check if left half is sorted
            if (nums[left] <= nums[mid]) {
                // Check if target lies in left half
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1;  // Search left half
                } else {
                    left = mid + 1;   // Search right half
                }
            }
            // Right half must be sorted
            else {
                // Check if target lies in right half
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1;   // Search right half
                } else {
                    right = mid - 1;  // Search left half
                }
            }
        }
        
        // Target not found
        return -1;
    }

    // Test method with various test cases
    public static void main(String[] args) {
        // Test Case 1: Regular rotated array
        int[] nums1 = {4, 5, 6, 7, 0, 1, 2};
        testSearch(nums1, 0, 4, "Regular rotated array - target exists");
        
        // Test Case 2: Target not present
        testSearch(nums1, 3, -1, "Regular rotated array - target doesn't exist");
        
        // Test Case 3: Single element array
        int[] nums2 = {1};
        testSearch(nums2, 1, 0, "Single element array - target exists");
        
        // Test Case 4: Empty array
        int[] nums3 = {};
        testSearch(nums3, 5, -1, "Empty array");
        
        // Test Case 5: Not rotated array
        int[] nums4 = {1, 2, 3, 4, 5};
        testSearch(nums4, 3, 2, "Not rotated array");
        
        // Test Case 6: Large array
        int[] nums5 = new int[1000000];
        for(int i = 0; i < nums5.length; i++) {
            nums5[i] = (i + 500000) % 1000000;  // Create large rotated array
        }
        testSearch(nums5, 999999, 499999, "Large array test");
    }
    
    // Helper method to run and validate test cases
    private static void testSearch(int[] nums, int target, int expected, String testName) {
        int result = search(nums, target);
        System.out.println(testName + ": " + 
            (result == expected ? "PASS" : "FAIL") +
            " (Expected: " + expected + ", Got: " + result + ")");
    }
}
