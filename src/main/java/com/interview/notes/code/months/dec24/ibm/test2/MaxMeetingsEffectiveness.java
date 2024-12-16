package com.interview.notes.code.months.dec24.ibm.test2;

import java.util.*;

public class MaxMeetingsEffectiveness {

    /*
     * Complete the 'maxMeetings' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY effectiveness as parameter.
     */
    public static int maxMeetings(List<Integer> effectiveness) {
        // Write your code here

        // Max-heap to keep track of included negative effectiveness meetings
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        // Separate non-negative and negative meetings
        List<Integer> negativeMeetings = new ArrayList<>();
        List<Integer> nonNegativeMeetings = new ArrayList<>();

        for (int val : effectiveness) {
            if (val < 0) {
                negativeMeetings.add(val);
            } else {
                nonNegativeMeetings.add(val);
            }
        }

        // Sort negative meetings in increasing order (less negative first)
        Collections.sort(negativeMeetings);

        long cumulativeSum = 0;
        int totalMeetings = 0;

        // Include non-negative meetings (positive and zero effectiveness)
        // Sort non-negative meetings in decreasing order to include higher positive values first
        Collections.sort(nonNegativeMeetings, Collections.reverseOrder());

        for (int val : nonNegativeMeetings) {
            if (cumulativeSum + val > 0) {
                cumulativeSum += val;
                totalMeetings++;
            } else {
                // Cannot include this meeting as cumulative sum will not be positive
                continue;
            }
        }

        // Include negative meetings
        for (int val : negativeMeetings) {
            if (cumulativeSum + val > 0) {
                cumulativeSum += val;
                maxHeap.offer(val); // Keep track of negative meetings included
                totalMeetings++;
            } else if (!maxHeap.isEmpty() && maxHeap.peek() < val) {
                // Try to replace a more negative meeting included earlier
                int removed = maxHeap.poll();
                cumulativeSum -= removed; // Remove negative meeting
                cumulativeSum += val; // Add current negative meeting
                maxHeap.offer(val);
                // totalMeetings remains same
            } else {
                // Cannot include this meeting
                continue;
            }
        }

        return totalMeetings;
    }

    // Main method for testing
    public static void main(String[] args) {
        testMaxMeetings();
        testLargeInput();
    }

    // Method to test the maxMeetings function with various test cases
    private static void testMaxMeetings() {
        boolean allPassed = true;

        // Test Case 1
        List<Integer> effectiveness1 = Arrays.asList(1, -20, 3, -2);
        int expected1 = 3;
        int result1 = maxMeetings(effectiveness1);
        if (result1 == expected1) {
            System.out.println("Test Case 1: PASS");
        } else {
            System.out.println("Test Case 1: FAIL (Expected " + expected1 + ", Got " + result1 + ")");
            allPassed = false;
        }

        // Sample Case 0
        List<Integer> effectiveness2 = Arrays.asList(-3, 0, 2, 1);
        int expected2 = 3;
        int result2 = maxMeetings(effectiveness2);
        if (result2 == expected2) {
            System.out.println("Sample Case 0: PASS");
        } else {
            System.out.println("Sample Case 0: FAIL (Expected " + expected2 + ", Got " + result2 + ")");
            allPassed = false;
        }

        // Sample Case 1
        List<Integer> effectiveness3 = Arrays.asList(-3, 0, -2);
        int expected3 = 0;
        int result3 = maxMeetings(effectiveness3);
        if (result3 == expected3) {
            System.out.println("Sample Case 1: PASS");
        } else {
            System.out.println("Sample Case 1: FAIL (Expected " + expected3 + ", Got " + result3 + ")");
            allPassed = false;
        }

        // Test Case 4: All positive effectiveness
        List<Integer> effectiveness4 = Arrays.asList(1, 2, 3, 4, 5);
        int expected4 = 5;
        int result4 = maxMeetings(effectiveness4);
        if (result4 == expected4) {
            System.out.println("Test Case 4: PASS");
        } else {
            System.out.println("Test Case 4: FAIL (Expected " + expected4 + ", Got " + result4 + ")");
            allPassed = false;
        }

        // Test Case 5: All negative effectiveness
        List<Integer> effectiveness5 = Arrays.asList(-1, -2, -3, -4);
        int expected5 = 0;
        int result5 = maxMeetings(effectiveness5);
        if (result5 == expected5) {
            System.out.println("Test Case 5: PASS");
        } else {
            System.out.println("Test Case 5: FAIL (Expected " + expected5 + ", Got " + result5 + ")");
            allPassed = false;
        }

        // Test Case 6: Mixed effectiveness
        List<Integer> effectiveness6 = Arrays.asList(5, -2, 3, -1, 2, -3);
        int expected6 = 6;
        int result6 = maxMeetings(effectiveness6);
        if (result6 == expected6) {
            System.out.println("Test Case 6: PASS");
        } else {
            System.out.println("Test Case 6: FAIL (Expected " + expected6 + ", Got " + result6 + ")");
            allPassed = false;
        }

        // Test Case 7: Zero effectiveness meetings
        List<Integer> effectiveness7 = Arrays.asList(0, 0, 0, 0);
        int expected7 = 0; // Cannot include zero meetings as cumulative sum won't be positive
        int result7 = maxMeetings(effectiveness7);
        if (result7 == expected7) {
            System.out.println("Test Case 7: PASS");
        } else {
            System.out.println("Test Case 7: FAIL (Expected " + expected7 + ", Got " + result7 + ")");
            allPassed = false;
        }

        // Final result
        if (allPassed) {
            System.out.println("All test cases passed.");
        } else {
            System.out.println("Some test cases failed.");
        }
    }

    // Method to test the maxMeetings function with large input
    private static void testLargeInput() {
        int n = 100000;
        List<Integer> effectiveness = new ArrayList<>(n);
        Random random = new Random(0); // Use a seed for reproducibility

        // Generate a large input with random effectiveness values between -1000 and 1000
        for (int i = 0; i < n; i++) {
            effectiveness.add(random.nextInt(2001) - 1000); // Values from -1000 to 1000
        }

        System.out.println("Large Test Case: Running...");

        long startTime = System.currentTimeMillis();

        int result = maxMeetings(effectiveness);

        long endTime = System.currentTimeMillis();

        System.out.println("Large Test Case: Completed in " + (endTime - startTime) + " ms");
        System.out.println("Maximum Number of Meetings: " + result);
        // Since expected value is difficult to compute manually, we verify that the code runs efficiently
    }
}