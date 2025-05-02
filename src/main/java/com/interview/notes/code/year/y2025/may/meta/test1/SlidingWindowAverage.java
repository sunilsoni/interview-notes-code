package com.interview.notes.code.year.y2025.may.meta.test1;

import java.util.Arrays;

public class SlidingWindowAverage {
    public static double[] calculateAveragesSlidingWindow(int[] arr, int windowSize) {
        // Step 1: Input validation
        if (arr == null || arr.length == 0 || windowSize <= 0 || windowSize > arr.length) {
            return new double[0];
        }

        int resultSize = arr.length - windowSize + 1;
        double[] result = new double[resultSize];
        
        // Step 2: Calculate initial window sum
        double windowSum = 0;
        for (int i = 0; i < windowSize; i++) {
            windowSum += arr[i];
        }
        result[0] = windowSum / windowSize;

        // Step 3: Slide the window and update sum
        for (int i = 1; i < resultSize; i++) {
            // Subtract the element leaving the window
            windowSum = windowSum - arr[i-1];
            // Add the new element entering the window
            windowSum = windowSum + arr[i+windowSize-1];
            // Calculate average
            result[i] = windowSum / windowSize;
        }

        return result;
    }

    public static void main(String[] args) {
        // Example test cases
        runTestCase(new int[]{1, 2, 3, 4, 5}, 3, "Basic Test");
        runTestCase(new int[]{2, 4, 6, 8, 10}, 2, "Even Numbers Test");
        runTestCase(new int[]{1, 1, 1, 1, 1}, 3, "Same Numbers Test");
    }

    private static void runTestCase(int[] arr, int windowSize, String testName) {
        System.out.println("\n" + testName);
        System.out.println("Input Array: " + Arrays.toString(arr));
        System.out.println("Window Size: " + windowSize);
        double[] result = calculateAveragesSlidingWindow(arr, windowSize);
        System.out.println("Result: " + Arrays.toString(result));
        
        // Print detailed calculation steps
        printCalculationSteps(arr, windowSize);
    }

    private static void printCalculationSteps(int[] arr, int windowSize) {
        System.out.println("\nDetailed Calculation Steps:");
        double windowSum = 0;
        
        // First window calculation
        System.out.print("Initial Window: [");
        for (int i = 0; i < windowSize; i++) {
            windowSum += arr[i];
            System.out.print(arr[i]);
            if (i < windowSize - 1) System.out.print(", ");
        }
        System.out.println("]");
        System.out.printf("Initial Sum: %.1f, Average: %.2f\n", 
                         windowSum, windowSum/windowSize);

        // Sliding window calculations
        for (int i = 1; i <= arr.length - windowSize; i++) {
            System.out.printf("\nSlide %d:\n", i);
            System.out.printf("Remove: %d, Add: %d\n", arr[i-1], arr[i+windowSize-1]);
            windowSum = windowSum - arr[i-1] + arr[i+windowSize-1];
            System.out.print("Current Window: [");
            for (int j = i; j < i + windowSize; j++) {
                System.out.print(arr[j]);
                if (j < i + windowSize - 1) System.out.print(", ");
            }
            System.out.println("]");
            System.out.printf("Current Sum: %.1f, Average: %.2f\n", 
                             windowSum, windowSum/windowSize);
        }
    }
}
