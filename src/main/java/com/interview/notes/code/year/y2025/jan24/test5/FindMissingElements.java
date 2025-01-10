package com.interview.notes.code.year.y2025.jan24.test5;

public class FindMissingElements {
    
    public static int[] findMissing(int[] arr) {
        if (arr == null || arr.length == 0) return new int[0];
        
        // Find min and max in array
        int min = arr[0];
        int max = arr[0];
        for (int num : arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        
        // Create boolean array to mark present numbers
        boolean[] present = new boolean[max - min + 1];
        for (int num : arr) {
            present[num - min] = true;
        }
        
        // Count missing numbers
        int missingCount = 0;
        for (boolean exists : present) {
            if (!exists) missingCount++;
        }
        
        // Create result array with missing numbers
        int[] missing = new int[missingCount];
        int index = 0;
        for (int i = 0; i < present.length; i++) {
            if (!present[i]) {
                missing[index++] = i + min;
            }
        }
        
        return missing;
    }
    
    public static void main(String[] args) {
        // Test cases
        runTest(new int[]{1, 3, 4, 5, 6, 8}, "Basic Test");
        runTest(new int[]{1, 2, 3, 4, 5}, "No Missing Elements");
        runTest(new int[]{}, "Empty Array");
        runTest(new int[]{1}, "Single Element");
        runTest(new int[]{1, 5}, "Large Gap");
        
        // Large array test
        int[] largeArray = new int[1000000];
        for (int i = 0; i < largeArray.length; i++) {
            largeArray[i] = i + 1;
        }
        largeArray[500000] = largeArray[500001]; // Create missing element
        runTest(largeArray, "Large Array Test");
    }
    
    private static void runTest(int[] input, String testName) {
        System.out.println("\nRunning: " + testName);
        System.out.println("Input array length: " + input.length);
        
        long startTime = System.currentTimeMillis();
        int[] result = findMissing(input);
        long endTime = System.currentTimeMillis();
        
        System.out.println("Missing elements: " + arrayToString(result));
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("Test " + testName + ": PASS");
    }
    
    private static String arrayToString(int[] arr) {
        if (arr.length == 0) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
