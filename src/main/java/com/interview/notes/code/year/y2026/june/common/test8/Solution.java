package com.interview.notes.code.year.y2026.june.common.test8;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Solution { // Main class wrapper

    public static int calculateMaximumConsecutiveSum(int k, List<List<Integer>> segment) { // Required signature
        List<Segment> normalSegs = new ArrayList<>(); // List to store original left-to-right segments
        List<Segment> mirroredSegs = new ArrayList<>(); // List to store mirrored segments to simulate right-aligned windows

        for (List<Integer> list : segment) { // Loop over all input segments provided by the test case
            long start = list.get(0); // Extract start bag index
            long end = list.get(1); // Extract end bag index
            long val = list.get(2); // Extract money value per bag
            normalSegs.add(new Segment(start, end, val)); // Add standard segment for left-aligned window check
            mirroredSegs.add(new Segment(-end, -start, val)); // Add mirrored segment (reversed coordinates) for right-aligned check
        } // End of iteration

        normalSegs.sort(Comparator.comparingLong(Segment::start)); // Sort standard segments chronologically by start bag
        mirroredSegs.sort(Comparator.comparingLong(Segment::start)); // Sort mirrored segments chronologically by new start

        long maxNormal = getMaxSum(normalSegs, k); // Calculate max possible sum assuming window starts at a segment boundary
        long maxMirrored = getMaxSum(mirroredSegs, k); // Calculate max possible sum assuming window ends at a segment boundary

        long overallMax = Math.max(maxNormal, maxMirrored); // Find the absolute highest sum between both strategies
        return (int) (overallMax % 1_000_000_007); // Modulo requirement applied only at the very end to prevent math logic errors
    } // End of main function

    private static long getMaxSum(List<Segment> segs, long k) { // Helper function to run sliding window calculation
        int n = segs.size(); // Determine total number of segments for loop boundaries
        long[] prefix = new long[n + 1]; // Prefix sum array to instantly calculate sum of multiple full segments
        for (int i = 0; i < n; i++) { // Loop through all segments to build our prefix array
            long bagsCount = segs.get(i).end() - segs.get(i).start() + 1; // Calculate exactly how many bags are in this segment
            long totalMoney = bagsCount * segs.get(i).value(); // Calculate total money enclosed within this entire segment
            prefix[i + 1] = prefix[i] + totalMoney; // Store cumulative total in prefix array (1-based index to avoid out-of-bounds)
        } // End of prefix sum generation loop

        long maxSum = 0; // Track the best window sum discovered so far
        for (int i = 0; i < n; i++) { // Test placing the start of our window at the beginning of each segment 'i'
            long windowStart = segs.get(i).start(); // Start position is exactly the start of segment 'i'
            long windowEnd = windowStart + k - 1; // Calculate the farthest bag index this window will reach

            int left = i; // Left boundary for binary search, starting from current segment
            int right = n - 1; // Right boundary for binary search, ending at the final segment
            int bestJ = i; // Track the index of the furthest segment that starts within our window

            while (left <= right) { // Binary search loop to quickly locate our bounding segment
                int mid = left + (right - left) / 2; // Find midpoint safely to prevent integer overflow
                if (segs.get(mid).start() <= windowEnd) { // If this segment starts inside our window
                    bestJ = mid; // It is a valid candidate to include, so save its index
                    left = mid + 1; // Try to see if an even further segment also fits
                } else { // If this segment starts completely after our window ends
                    right = mid - 1; // Narrow search space to earlier segments
                } // End of conditional check
            } // End of binary search

            long fullSegmentsSum = prefix[bestJ] - prefix[i]; // $O(1)$ calculation of all fully engulfed segments using prefix array
            long overlapStart = segs.get(bestJ).start(); // Start index of the final (potentially partially overlapping) segment
            long overlapEnd = Math.min(segs.get(bestJ).end(), windowEnd); // Calculate where the overlap cuts off
            long overlapMoney = (overlapEnd - overlapStart + 1) * segs.get(bestJ).value(); // Multiply overlapping bags by their value

            long currentSum = fullSegmentsSum + overlapMoney; // Combine full segments sum with partial overlap remainder
            maxSum = Math.max(maxSum, currentSum); // If this window is better than previous attempts, save it
        } // End of window loop

        return maxSum; // Return the most lucrative continuous window found
    } // End of helper method

    public static void main(String[] args) { // Main method for basic test runs (No JUnit needed)
        // Test 1: Sample 0 from Problem Description
        List<List<Integer>> seg1 = Arrays.asList( // Creating segment data list
            Arrays.asList(1, 9, 5), // Segment: bags 1-9, $5 per bag
            Arrays.asList(10, 20, 5) // Segment: bags 10-20, $5 per bag
        ); // End data creation
        int res1 = calculateMaximumConsecutiveSum(3, seg1); // Run method with window size 3
        System.out.println("Test 1 | Expected: 15 | Actual: " + res1 + " | " + (res1 == 15 ? "PASS" : "FAIL")); // Print validation

        // Test 2: Sample 1 from Problem Description
        List<List<Integer>> seg2 = Arrays.asList( // Creating segment data list
            Arrays.asList(1, 1, 10), // Bag 1 = $10
            Arrays.asList(2, 2, 20), // Bag 2 = $20
            Arrays.asList(3, 3, 30) // Bag 3 = $30
        ); // End data creation
        int res2 = calculateMaximumConsecutiveSum(1, seg2); // Run method with window size 1
        System.out.println("Test 2 | Expected: 30 | Actual: " + res2 + " | " + (res2 == 30 ? "PASS" : "FAIL")); // Print validation

        // Test 3: Large Data / Bounds Testing
        List<List<Integer>> seg3 = List.of( // Creating massive data input
                Arrays.asList(1, 1000000000, 1000000) // 1 Billion bags holding 1 Million each
        ); // End data creation
        long hugeSum = 1000000000L * 1000000L; // Compute raw total sum (10^15)
        int expected3 = (int) (hugeSum % 1000000007); // Apply required modulo logically
        int res3 = calculateMaximumConsecutiveSum(1000000000, seg3); // Execute method on huge data
        System.out.println("Test 3 | Expected: " + expected3 + " | Actual: " + res3 + " | " + (res3 == expected3 ? "PASS" : "FAIL")); // Check safety against integer overflow limits
    } // End of main

    // Record feature (Java 14+) to hold segment data concisely without boilerplate getters/setters
    record Segment(long start, long end, long value) {} // Data class for bag segments
} // End of class wrapper