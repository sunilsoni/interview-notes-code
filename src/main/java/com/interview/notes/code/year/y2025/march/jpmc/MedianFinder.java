package com.interview.notes.code.year.y2025.march.jpmc;

import java.util.*;

public class MedianFinder {

    // Max-heap for lower half
    private PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
    
    // Min-heap for upper half
    private PriorityQueue<Integer> right = new PriorityQueue<>();

    // Append value method
    public void appendValue(int num) {
        left.offer(num); // Add to left heap
        right.offer(left.poll()); // Balance: Move largest from left to right

        if (right.size() > left.size()) { // Ensure left is not smaller
            left.offer(right.poll());
        }
    }

    // Get Median method
    public double getMedian() {
        if (left.isEmpty()) return 0.0; // Edge case: no numbers yet
        if (left.size() > right.size()) return left.peek(); // Odd size
        return (left.peek() + right.peek()) / 2.0; // Even size, average two middles
    }

    // Main Method to test cases
    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();

        // Define simple test cases
        int[] testValues = {10, 20, 30, 40, 50};
        double[] expectedMedians = {10, 15, 20, 25, 30};

        boolean allPassed = true;
        for (int i = 0; i < testValues.length; i++) {
            mf.appendValue(testValues[i]);
            double median = mf.getMedian();
            boolean pass = median == expectedMedians[i];
            System.out.printf("Test %d - Appended: %d, Expected Median: %.1f, Got: %.1f [%s]%n",
                    (i + 1), testValues[i], expectedMedians[i], median, pass ? "PASS" : "FAIL");
            if (!pass) allPassed = false;
        }

        // Large data input edge case
        MedianFinder largeTest = new MedianFinder();
        int largeInput = 1_000_000;
        for (int i = 1; i <= largeInput; i++) {
            largeTest.appendValue(i);
        }
        double largeMedian = largeTest.getMedian();
        System.out.printf("Large Input Test - Expected Median: %.1f, Got: %.1f [%s]%n",
                500000.5, largeMedian, largeMedian == 500000.5 ? "PASS" : "FAIL");

        System.out.println(allPassed ? "All tests PASSED!" : "Some tests FAILED.");
    }
}
