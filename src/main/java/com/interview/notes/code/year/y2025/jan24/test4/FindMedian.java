package com.interview.notes.code.year.y2025.jan24.test4;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedian {
    // Max-heap for the lower half of the numbers
    private static PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
    // Min-heap for the upper half of the numbers
    private static PriorityQueue<Integer> upperHalf = new PriorityQueue<>();

    /**
     * Adds a number to our data structure.
     * We balance both heaps so their sizes are either equal
     * or differ by exactly one.
     */
    private static void addNumber(int num) {
        // If lower half is empty or the new number is smaller than/equal to the top of lowerHalf
        if (lowerHalf.isEmpty() || num <= lowerHalf.peek()) {
            lowerHalf.offer(num);
        } else {
            // Otherwise, it belongs in the upper half
            upperHalf.offer(num);
        }

        // Balance heaps so that each heap differs in size by at most one.
        if (lowerHalf.size() > upperHalf.size() + 1) {
            upperHalf.offer(lowerHalf.poll());
        } else if (upperHalf.size() > lowerHalf.size()) {
            lowerHalf.offer(upperHalf.poll());
        }
    }

    /**
     * Returns the median of all elements read so far.
     */
    private static double getMedian() {
        // If both heaps have the same size, median is the average of both top elements.
        if (lowerHalf.size() == upperHalf.size()) {
            if (lowerHalf.isEmpty() && upperHalf.isEmpty()) {
                // No elements, handle as needed (return 0 or throw an exception)
                return 0.0; 
            }
            return (lowerHalf.peek() + upperHalf.peek()) / 2.0;
        } else {
            // If they differ by one, median is the top of the bigger heap.
            return (double) lowerHalf.peek();
        }
    }

    /**
     * Main method to test our logic with simple pass/fail checks.
     * No JUnit used, only printing results.
     */
    public static void main(String[] args) {
        boolean allTestsPassed = true;

        // Reset heaps before every test
        lowerHalf.clear();
        upperHalf.clear();
        
        // Test 1: Single element
        addNumber(10);
        double median = getMedian();
        boolean passTest1 = (median == 10.0);
        printTestResult("Test 1 - Single element", passTest1);
        allTestsPassed = allTestsPassed && passTest1;

        // Clear data for next test
        lowerHalf.clear();
        upperHalf.clear();

        // Test 2: Odd number of elements
        int[] test2Data = {5, 2, 8};
        for (int num : test2Data) {
            addNumber(num);
        }
        double resultTest2 = getMedian(); 
        // Sorted test2Data => {2, 5, 8}; middle = 5
        boolean passTest2 = (resultTest2 == 5.0);
        printTestResult("Test 2 - Odd number of elements", passTest2);
        allTestsPassed = allTestsPassed && passTest2;

        // Clear data for next test
        lowerHalf.clear();
        upperHalf.clear();

        // Test 3: Even number of elements
        int[] test3Data = {5, 2, 8, 10};
        for (int num : test3Data) {
            addNumber(num);
        }
        double resultTest3 = getMedian(); 
        // Sorted test3Data => {2, 5, 8, 10}; median = (5 + 8)/2 = 6.5
        boolean passTest3 = (resultTest3 == 6.5);
        printTestResult("Test 3 - Even number of elements", passTest3);
        allTestsPassed = allTestsPassed && passTest3;

        // Clear data for next test
        lowerHalf.clear();
        upperHalf.clear();

        // Test 4: Negative and positive mix
        int[] test4Data = {-5, -2, 0, 1, 3};
        // Sorted => {-5, -2, 0, 1, 3}; median = 0
        for (int num : test4Data) {
            addNumber(num);
        }
        double resultTest4 = getMedian();
        boolean passTest4 = (resultTest4 == 0.0);
        printTestResult("Test 4 - Negative and positive mix", passTest4);
        allTestsPassed = allTestsPassed && passTest4;

        // Clear data for next test
        lowerHalf.clear();
        upperHalf.clear();

        // Test 5: Larger data set (basic check)
        int[] test5Data = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        // Sorted => {10, 20, 30, 40, 50, 60, 70, 80, 90, 100}
        // Median = (50 + 60) / 2 = 55
        for (int num : test5Data) {
            addNumber(num);
        }
        double resultTest5 = getMedian();
        boolean passTest5 = (resultTest5 == 55.0);
        printTestResult("Test 5 - Larger data set", passTest5);
        allTestsPassed = allTestsPassed && passTest5;

        // Final result
        if (allTestsPassed) {
            System.out.println("All tests passed!");
        } else {
            System.out.println("Some tests failed. Review results above.");
        }
    }

    /**
     * Utility method to print test results in a simple format.
     */
    private static void printTestResult(String testName, boolean passed) {
        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
    }
}
