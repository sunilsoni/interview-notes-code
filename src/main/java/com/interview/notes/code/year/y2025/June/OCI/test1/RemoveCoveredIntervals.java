package com.interview.notes.code.year.y2025.June.OCI.test1;

import java.util.*;
import java.util.stream.Collectors;

public class RemoveCoveredIntervals {

    // Method to remove covered intervals
    public static List<int[]> removeCoveredIntervals(List<int[]> intervals) {
        // Sort intervals based on start ascending, and end descending
        intervals.sort((a, b) -> a[0] != b[0] ? Integer.compare(a[0], b[0]) : Integer.compare(b[1], a[1]));

        List<int[]> result = new ArrayList<>();
        int[] current = intervals.get(0);
        result.add(current);

        for (int i = 1; i < intervals.size(); i++) {
            int[] interval = intervals.get(i);

            // Check if current interval completely covers the next interval
            if (current[0] <= interval[0] && current[1] >= interval[1]) {
                continue; // Skip interval if covered
            } else {
                current = interval; // Update current interval
                result.add(current); // Add to result
            }
        }

        return result;
    }

    // Simple method to print intervals
    public static void printIntervals(List<int[]> intervals) {
        intervals.forEach(interval -> System.out.print("[" + interval[0] + "," + interval[1] + "] "));
        System.out.println();
    }

    public static void main(String[] args) {
        // Test Case 1 (Provided Example)
        List<int[]> intervals1 = Arrays.asList(new int[]{1, 4}, new int[]{3, 6}, new int[]{2, 8});
        List<int[]> result1 = removeCoveredIntervals(intervals1);
        printIntervals(result1); // Expected: [1,4] [2,8]

        // Additional Test Case 2 (Edge case: all intervals covered)
        List<int[]> intervals2 = Arrays.asList(new int[]{1, 10}, new int[]{2, 5}, new int[]{3, 7});
        List<int[]> result2 = removeCoveredIntervals(intervals2);
        printIntervals(result2); // Expected: [1,10]

        // Additional Test Case 3 (No intervals covered)
        List<int[]> intervals3 = Arrays.asList(new int[]{1, 2}, new int[]{3, 4}, new int[]{5, 6});
        List<int[]> result3 = removeCoveredIntervals(intervals3);
        printIntervals(result3); // Expected: [1,2] [3,4] [5,6]

        // Additional Test Case 4 (Large dataset)
        List<int[]> intervals4 = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            intervals4.add(new int[]{i, i + 10000});
        }
        List<int[]> result4 = removeCoveredIntervals(intervals4);
        System.out.println("Size of result4: " + result4.size()); // Expected: 1
    }
}
