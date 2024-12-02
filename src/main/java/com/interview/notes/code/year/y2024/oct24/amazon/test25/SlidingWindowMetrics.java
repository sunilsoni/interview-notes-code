package com.interview.notes.code.year.y2024.oct24.amazon.test25;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMetrics {

    public static int[] calculateSlidingMax(int[] data, int windowSize) {
        if (data == null || data.length == 0 || windowSize <= 0 || windowSize > data.length) {
            return new int[0];
        }

        int resultSize = data.length - windowSize + 1;
        int[] result = new int[resultSize];

        // Deque will store indices of potential maximum values
        Deque<Integer> deque = new ArrayDeque<>();

        // Process first window
        for (int i = 0; i < windowSize; i++) {
            // Remove smaller elements from back
            while (!deque.isEmpty() && data[deque.peekLast()] <= data[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        // First window's maximum
        result[0] = data[deque.peekFirst()];

        // Process rest of the elements
        for (int i = windowSize; i < data.length; i++) {
            // Remove elements outside current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - windowSize) {
                deque.pollFirst();
            }

            // Remove smaller elements from back
            while (!deque.isEmpty() && data[deque.peekLast()] <= data[i]) {
                deque.pollLast();
            }

            deque.offerLast(i);
            result[i - windowSize + 1] = data[deque.peekFirst()];
        }

        return result;
    }

    // Enhanced main method with performance comparison
    public static void main(String[] args) {
        testCorrectness();
        comparePerformance();
    }

    private static void testCorrectness() {
        System.out.println("Testing Correctness:");
        runTest(new int[]{1, 3, 2, 2, 6, 5}, 3, new int[]{3, 3, 6, 6}, "Basic Test Case");
        runTest(new int[]{8, 5, 3, 1, 4, 7}, 3, new int[]{8, 5, 4, 7}, "Mixed Sequence");
        runTest(new int[]{1}, 1, new int[]{1}, "Single Element");
        runTest(new int[]{5, 4, 3, 2, 1}, 3, new int[]{5, 4, 3}, "Decreasing Sequence");
        runTest(new int[]{1, 2, 3, 4, 5}, 3, new int[]{3, 4, 5}, "Increasing Sequence");
    }

    private static void comparePerformance() {
        System.out.println("\nPerformance Comparison:");

        // Test with different sizes
        int[] sizes = {1000, 10000, 100000, 1000000};
        int[] windows = {10, 100, 1000};

        for (int size : sizes) {
            System.out.println("\nArray size: " + size);
            int[] data = generateTestData(size);

            for (int window : windows) {
                if (window > size) continue;

                System.out.println("Window size: " + window);

                // Test optimized version
                long startTime = System.nanoTime();
                int[] result = calculateSlidingMax(data, window);
                long endTime = System.nanoTime();
                double optimizedTime = (endTime - startTime) / 1_000_000.0;

                // Test naive version
                startTime = System.nanoTime();
                int[] naiveResult = calculateSlidingMaxNaive(data, window);
                endTime = System.nanoTime();
                double naiveTime = (endTime - startTime) / 1_000_000.0;

                System.out.printf("Optimized: %.2f ms, Naive: %.2f ms, Speedup: %.2fx%n",
                        optimizedTime, naiveTime, naiveTime / optimizedTime);

                // Verify results match
                boolean matches = arrayEquals(result, naiveResult);
                System.out.println("Results match: " + matches);
            }
        }
    }

    // Naive implementation for comparison
    private static int[] calculateSlidingMaxNaive(int[] data, int windowSize) {
        if (data == null || data.length == 0 || windowSize <= 0 || windowSize > data.length) {
            return new int[0];
        }

        int resultSize = data.length - windowSize + 1;
        int[] result = new int[resultSize];

        for (int i = 0; i <= data.length - windowSize; i++) {
            int max = data[i];
            for (int j = 0; j < windowSize; j++) {
                max = Math.max(max, data[i + j]);
            }
            result[i] = max;
        }

        return result;
    }

    private static int[] generateTestData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = (int) (Math.random() * 1000);
        }
        return data;
    }

    private static void runTest(int[] input, int windowSize, int[] expected, String testName) {
        int[] result = calculateSlidingMax(input, windowSize);
        boolean passed = arrayEquals(result, expected);

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Input: " + arrayToString(input));
            System.out.println("Expected: " + arrayToString(expected));
            System.out.println("Got: " + arrayToString(result));
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