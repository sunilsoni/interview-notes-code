package com.interview.notes.code.year.y2025.march.meta.test4;

import java.util.Arrays;

/*
 Given an array of integers (0-to 9) • rearrange the positions of the array elements to represent a number which is the second largest possible.
Ex:
Input = [1,2,3,4,5]
Output = [5,4, 3,1,2]
 */
/**
 * Solution for finding the second largest number that can be formed
 * by rearranging an array of integers (0-9).
 */
public class SecondLargestNumber {
    
    /**
     * Rearranges the input array to form the second largest possible number.
     * 
     * @param nums Array of integers (digits 0-9)
     * @return Array rearranged to represent the second largest possible number
     */
    public static int[] findSecondLargest(int[] nums) {
        // Handle edge cases
        if (nums == null || nums.length <= 1) {
            return nums; // Cannot form a second largest with 0 or 1 digits
        }
        
        // Create a copy of the input array to avoid modifying the original
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i];
        }
        
        // Sort the array in descending order (largest possible number)
        Arrays.sort(result);
        reverse(result);
        
        // To get the second largest, we need to find the first pair of digits 
        // we can swap to make the number smaller
        for (int i = result.length - 1; i > 0; i--) {
            // If we find two different digits, swap them to get second largest
            if (result[i] != result[i-1]) {
                // Swap to create second largest
                int temp = result[i];
                result[i] = result[i-1];
                result[i-1] = temp;
                return result;
            }
        }
        
        // All digits are the same, so there's only one possible arrangement
        return result;
    }
    
    /**
     * Helper method to reverse an array (convert ascending to descending).
     * 
     * @param arr Array to be reversed in-place
     */
    private static void reverse(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
            left++;
            right--;
        }
    }
    
    /**
     * Main method to test the solution with various test cases.
     */
    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{1, 2, 3, 4, 5}, new int[]{5, 4, 3, 1, 2});
        runTest(new int[]{9, 8, 7, 6, 5}, new int[]{9, 7, 8, 6, 5});
        runTest(new int[]{5, 5, 5, 5, 5}, new int[]{5, 5, 5, 5, 5});
        runTest(new int[]{9, 9, 8, 7}, new int[]{9, 8, 9, 7});
        runTest(new int[]{0}, new int[]{0});
        runTest(new int[]{}, new int[]{});
        runTest(new int[]{4, 2, 8, 7, 5}, new int[]{8, 7, 5, 2, 4});
        runTest(new int[]{9, 0}, new int[]{0, 9});
        
        // Additional test cases with repeated digits
        runTest(new int[]{3, 3, 3, 2, 1}, new int[]{3, 3, 2, 3, 1});
        runTest(new int[]{1, 1, 2, 2}, new int[]{2, 1, 2, 1});
        
        // Large input test
        int[] largeInput = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeInput[i] = i % 10; // Repeating digits 0-9
        }
        int[] result = findSecondLargest(largeInput);
        System.out.println("Large input test completed. First 20 elements of result:");
        for (int i = 0; i < 20; i++) {
            System.out.print(result[i] + " ");
        }
        System.out.println("...");
    }
    
    /**
     * Utility method to run a test case and print results.
     * 
     * @param input Input array
     * @param expected Expected output array
     */
    private static void runTest(int[] input, int[] expected) {
        int[] result = findSecondLargest(input);
        boolean pass = arraysEqual(result, expected);
        
        System.out.println("Input: " + arrayToString(input));
        System.out.println("Output: " + arrayToString(result));
        System.out.println("Expected: " + arrayToString(expected));
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        System.out.println();
    }
    
    /**
     * Utility method to check if two arrays are equal.
     * Java 7 compatible replacement for Arrays.equals().
     */
    private static boolean arraysEqual(int[] arr1, int[] arr2) {
        if (arr1 == arr2) return true;
        if (arr1 == null || arr2 == null) return false;
        if (arr1.length != arr2.length) return false;
        
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }
    
    /**
     * Utility method to convert an array to string.
     * Java 7 compatible replacement for Arrays.toString().
     */
    private static String arrayToString(int[] arr) {
        if (arr == null) return "null";
        if (arr.length == 0) return "[]";
        
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
