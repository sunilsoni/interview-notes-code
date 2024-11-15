package com.interview.notes.code.months.nov24.test9;

public class ArrayRearranger {
    
    public static void rearrangeArray(int[] arr) {
        if (arr == null || arr.length <= 2) {
            return;
        }
        
        // Start from index 2 and process rest of the array
        for (int i = 2; i < arr.length; i++) {
            // Check if current and previous two elements are same
            if (arr[i] == arr[i-1] && arr[i-1] == arr[i-2]) {
                // Find next different element
                int j = i + 1;
                while (j < arr.length && arr[j] == arr[i]) {
                    j++;
                }
                
                // If we found a different element, swap
                if (j < arr.length) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    
    // Helper method to check if array has more than 2 consecutive repeated numbers
    private static boolean isValidArrangement(int[] arr) {
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] == arr[i-1] && arr[i-1] == arr[i-2]) {
                return false;
            }
        }
        return true;
    }
    
    // Helper method to print array
    private static void printArray(int[] arr) {
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
            if (i < arr.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
    
    public static void runTest(int[] input, String testName) {
        System.out.println("\nRunning test: " + testName);
        System.out.print("Input:  ");
        printArray(input);
        
        int[] testArray = input.clone();
        rearrangeArray(testArray);
        
        System.out.print("Output: ");
        printArray(testArray);
        
        boolean passed = isValidArrangement(testArray);
        System.out.println("Test " + (passed ? "PASSED" : "FAILED"));
    }
    
    public static void main(String[] args) {
        // Test Case 1: Given example
        runTest(new int[]{1,1,2,2,2,3,4,4,4,4,5}, "Basic Test");
        
        // Test Case 2: Empty array
        runTest(new int[]{}, "Empty Array");
        
        // Test Case 3: Small array
        runTest(new int[]{1,1}, "Small Array");
        
        // Test Case 4: All same elements
        runTest(new int[]{2,2,2,2,2}, "All Same Elements");
        
        // Test Case 5: Already valid arrangement
        runTest(new int[]{1,1,2,2,3,3}, "Already Valid");
        
        // Test Case 6: Large array
        int[] largeArray = new int[1000];
        for (int i = 0; i < 1000; i++) {
            largeArray[i] = i / 3; // Creates groups of three same numbers
        }
        runTest(largeArray, "Large Array");
        
        // Test Case 7: Edge case with multiple repeated elements
        runTest(new int[]{1,1,1,2,2,2,3,3,3}, "Multiple Repeats");
    }
}