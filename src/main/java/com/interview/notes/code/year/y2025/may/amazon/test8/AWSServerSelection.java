package com.interview.notes.code.year.y2025.may.amazon.test8;

import java.util.*;
import java.util.stream.*;

public class AWSServerSelection {

    public static int getMaxServers(List<Integer> powers) {
        // Count the frequency of each power
        Map<Integer, Long> freq = powers.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        int max = 0;
        // For each possible pair of adjacent powers
        for (int k : freq.keySet()) {
            long count = freq.get(k);
            // Single number group
            max = (int) Math.max(max, count);
            // Adjacent number group (k and k+1)
            if (freq.containsKey(k + 1)) {
                long countNext = freq.get(k + 1);
                // If difference ≤ 1, can alternate all; else 2*min(counts)
                long groupSize = 0;
                if (Math.abs(count - countNext) <= 1) {
                    groupSize = count + countNext;
                } else {
                    groupSize = 2 * Math.min(count, countNext) + 1;
                }
                max = (int) Math.max(max, groupSize);
            }
        }
        return max;
    }

    // Helper to test a single case and print PASS/FAIL
    public static void testCase(List<Integer> powers, int expected, int idx) {
        int result = getMaxServers(powers);
        if (result == expected) {
            System.out.println("Test " + idx + ": PASS (got=" + result + ", expected=" + expected + ")");
        } else {
            System.out.println("Test " + idx + ": FAIL (got=" + result + ", expected=" + expected + ")");
        }
    }

    public static void main(String[] args) {
        // Provided sample test cases

        // Test 1
        testCase(Arrays.asList(2, 2, 3, 2, 1, 2, 2), 7, 1); // Output: 7

        // Test 2
        testCase(Arrays.asList(3, 7, 5, 1, 5), 2, 2); // Output: 2

        // Test 3
        testCase(Arrays.asList(4, 3, 5, 1, 2, 2, 1), 5, 3); // Output: 5

        // Edge test: all same value
        testCase(Arrays.asList(5, 5, 5, 5, 5), 5, 4); // Output: 5

        // Edge test: all values diff by >1
        testCase(Arrays.asList(1, 3, 5, 7, 9), 1, 5); // Output: 1

        // Edge test: large data (stress test)
        List<Integer> bigTest = IntStream.range(0, 200_000).mapToObj(i -> 100).collect(Collectors.toList());
        testCase(bigTest, 200_000, 6);

        // Edge test: alternating
        testCase(Arrays.asList(1,2,1,2,1,2,1,2), 8, 7);

        // Custom: [1, 2, 2, 2, 3, 3]
        testCase(Arrays.asList(1, 2, 2, 2, 3, 3), 5, 8);
    }
}
