package com.interview.notes.code.months.oct24.wallmart.test2;

import java.util.*;

public class DiskSpaceAnalysis {

    /**
     * Determines the maximum of the minimum disk space values across all segments of length x.
     *
     * @param x     the length of each segment
     * @param space the list of available disk space on each computer
     * @return the maximum of the minimum values of disk space in each segment
     */
    public static int segment(int x, List<Integer> space) {
        if (space == null || space.size() == 0 || x <= 0 || x > space.size()) {
            throw new IllegalArgumentException("Invalid input parameters.");
        }

        int n = space.size();
        int maxOfMins = Integer.MIN_VALUE;
        Deque<Integer> deque = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // Remove indices from the front that are out of the current window
            while (!deque.isEmpty() && deque.peekFirst() <= i - x) {
                deque.pollFirst();
            }

            // Remove indices from the back whose corresponding values are greater than or equal to space[i]
            while (!deque.isEmpty() && space.get(deque.peekLast()) >= space.get(i)) {
                deque.pollLast();
            }

            // Add current index to the deque
            deque.offerLast(i);

            // Once the first window is completed, start recording minima
            if (i >= x - 1) {
                int currentMin = space.get(deque.peekFirst());
                if (currentMin > maxOfMins) {
                    maxOfMins = currentMin;
                }
            }
        }

        return maxOfMins;
    }

    /**
     * Represents a test case with input parameters and the expected output.
     */
    static class TestCase {
        int x;
        List<Integer> space;
        int expected;

        TestCase(int x, List<Integer> space, int expected) {
            this.x = x;
            this.space = space;
            this.expected = expected;
        }
    }

    /**
     * Executes all test cases and prints whether each test passes or fails.
     */
    public static void main(String[] args) {
        List<TestCase> testCases = new ArrayList<>();

        // Sample Test Case 1
        testCases.add(new TestCase(
                2,
                Arrays.asList(1, 1, 1),
                1
        ));

        // Sample Test Case 2
        testCases.add(new TestCase(
                3,
                Arrays.asList(2, 5, 4, 6, 8),
                4
        ));

        // Additional Test Case 3: All elements are the same
        testCases.add(new TestCase(
                1,
                Arrays.asList(7, 7, 7, 7, 7),
                7
        ));

        // Additional Test Case 4: x equals n
        testCases.add(new TestCase(
                5,
                Arrays.asList(10, 20, 30, 40, 50),
                10
        ));

        // Additional Test Case 5: x is 1 (max of all elements)
        testCases.add(new TestCase(
                1,
                Arrays.asList(3, 1, 4, 1, 5, 9, 2, 6, 5),
                9
        ));

        // Additional Test Case 6: Descending order
        testCases.add(new TestCase(
                3,
                Arrays.asList(9, 8, 7, 6, 5, 4, 3, 2, 1),
                3
        ));

        // Additional Test Case 7: Ascending order
        testCases.add(new TestCase(
                4,
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8),
                5
        ));

        // Additional Test Case 8: Large Input
        // Creating a list of 1,000,000 elements with varying values
        List<Integer> largeSpace = new ArrayList<>();
        for (int i = 1; i <= 1000000; i++) {
            largeSpace.add(i % 1000); // Values from 0 to 999
        }
        testCases.add(new TestCase(
                500000,
                largeSpace,
                0 // Since i % 1000 cycles, the minimum in any window of size 500,000 would be 0
        ));

        // Execute Test Cases
        int testNumber = 1;
        for (TestCase testCase : testCases) {
            try {
                long startTime = System.currentTimeMillis();
                int result = segment(testCase.x, testCase.space);
                long endTime = System.currentTimeMillis();
                boolean pass = result == testCase.expected;
                System.out.println("Test Case " + testNumber + ": " + (pass ? "PASS" : "FAIL"));
                if (!pass) {
                    System.out.println("  Expected: " + testCase.expected + ", Got: " + result);
                }
                System.out.println("  Time Taken: " + (endTime - startTime) + " ms");
            } catch (Exception e) {
                System.out.println("Test Case " + testNumber + ": FAIL");
                System.out.println("  Exception: " + e.getMessage());
            }
            testNumber++;
        }
    }
}
