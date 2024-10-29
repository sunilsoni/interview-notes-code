package com.interview.notes.code.months.oct24.amz.test24;

/*
We have a set of metrics where we need to smooth-out the data using a sliding window that generates the sum of the values in that window.
So given the initial metrics dataset and the window length, return the result by applying the sum on overlapping intervals of the sliding window.
For example:
sum: ([1, 3, 2, 2, 6, 5], 3) => [6, 7, 10, 13] max: ([1, 3, 2, 2, 6, 5], 3) => [3, 3, 6, 6]
 */
public class SlidingWindowMetrics {
    
    public static int[] calculateSlidingSum(int[] data, int windowSize) {
        if (data == null || data.length == 0 || windowSize <= 0 || windowSize > data.length) {
            return new int[0];
        }

        int resultSize = data.length - windowSize + 1;
        int[] result = new int[resultSize];
        
        int windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += data[i];
        }
        result[0] = windowSum;
        
        for (int i = 1; i < resultSize; i++) {
            windowSum = windowSum - data[i-1] + data[i+windowSize-1];
            result[i] = windowSum;
        }
        
        return result;
    }

    public static int[] calculateSlidingMax(int[] data, int windowSize) {
        if (data == null || data.length == 0 || windowSize <= 0 || windowSize > data.length) {
            return new int[0];
        }

        int resultSize = data.length - windowSize + 1;
        int[] result = new int[resultSize];
        
        // For each window
        for (int i = 0; i <= data.length - windowSize; i++) {
            int max = data[i];
            // Find maximum in current window
            for (int j = 0; j < windowSize; j++) {
                max = Math.max(max, data[i + j]);
            }
            result[i] = max;
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Test both sum and max operations
        System.out.println("Testing Sliding Sum:");
        testSlidingSum();
        
        System.out.println("\nTesting Sliding Max:");
        testSlidingMax();
        
        // Test large data
        testLargeData();
    }

    private static void testSlidingSum() {
        runTest("Sum", new int[]{1, 3, 2, 2, 6, 5}, 3, new int[]{6, 7, 10, 13}, "Basic Test Case");
        runTest("Sum", new int[]{1}, 1, new int[]{1}, "Single Element");
        runTest("Sum", new int[]{}, 0, new int[]{}, "Empty Array with Zero Window");
        runTest("Sum", new int[]{1, 2, 3}, 0, new int[]{}, "Non-Empty Array with Zero Window");
        runTest("Sum", new int[]{1, 2}, 3, new int[]{}, "Window Size Larger Than Array");
    }

    private static void testSlidingMax() {
        runTest("Max", new int[]{1, 3, 2, 2, 6, 5}, 3, new int[]{3, 3, 6, 6}, "Basic Test Case");
        runTest("Max", new int[]{1}, 1, new int[]{1}, "Single Element");
        runTest("Max", new int[]{}, 0, new int[]{}, "Empty Array with Zero Window");
        runTest("Max", new int[]{1, 2, 3}, 0, new int[]{}, "Non-Empty Array with Zero Window");
        runTest("Max", new int[]{1, 2}, 3, new int[]{}, "Window Size Larger Than Array");
        runTest("Max", new int[]{5, 4, 3, 2, 1}, 3, new int[]{5, 4, 3}, "Decreasing Sequence");
        runTest("Max", new int[]{1, 2, 3, 4, 5}, 3, new int[]{3, 4, 5}, "Increasing Sequence");
    }

    private static void testLargeData() {
        int[] largeInput = new int[1000000];
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = i % 10;
        }
        
        System.out.println("\nLarge Data Tests:");
        
        // Test Sum with large data
        long startTime = System.currentTimeMillis();
        int[] sumResult = calculateSlidingSum(largeInput, 1000);
        long sumTime = System.currentTimeMillis() - startTime;
        
        // Test Max with large data
        startTime = System.currentTimeMillis();
        int[] maxResult = calculateSlidingMax(largeInput, 1000);
        long maxTime = System.currentTimeMillis() - startTime;
        
        System.out.println("Sum execution time: " + sumTime + "ms");
        System.out.println("Max execution time: " + maxTime + "ms");
        System.out.println("Results size: " + sumResult.length);
    }

    private static void runTest(String operation, int[] input, int windowSize, int[] expected, String testName) {
        int[] result;
        if (operation.equals("Sum")) {
            result = calculateSlidingSum(input, windowSize);
        } else {
            result = calculateSlidingMax(input, windowSize);
        }
        
        boolean passed = arrayEquals(result, expected);
        
        System.out.println(operation + " - " + testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Input: " + arrayToString(input));
            System.out.println("Window Size: " + windowSize);
            System.out.println("Expected: " + arrayToString(expected));
            System.out.println("Got: " + arrayToString(result));
            System.out.println();
        }
    }

    private static boolean arrayEquals(int[] arr1, int[] arr2) {
        if (arr1.length != arr2.length) return false;
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) return false;
        }
        return true;
    }

    private static String arrayToString(int[] arr) {
        if (arr == null) return "null";
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
            if (i < arr.length - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}