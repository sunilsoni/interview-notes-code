package com.interview.notes.code.year.y2025.may.amazon.test7;

import java.util.*;
import java.util.stream.*;

public class AWSServerSelection {

    // Function to solve the problem
    public static int getMaxServers(List<Integer> powers) {
        // Count the frequency of each power
        Map<Integer, Long> freq = powers.stream()
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        // Find the max subset where all powers are same or differ by 1
        int max = 0;
        for (int k : freq.keySet()) {
            long count = freq.get(k);
            long next = freq.getOrDefault(k + 1, 0L);
            long prev = freq.getOrDefault(k - 1, 0L);
            max = (int) Math.max(max, Math.max(count + next, count + prev));
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
        // Sample test cases from screenshots

        // Test 1
        testCase(Arrays.asList(2, 2, 3, 2, 1, 2, 2), 7, 1); // All can be rearranged (output: 7)

        // Test 2
        testCase(Arrays.asList(3, 7, 5, 1, 5), 2, 2); // [5, 5] or [1, 1] (output: 2)

        // Test 3
        testCase(Arrays.asList(4, 3, 5, 1, 2, 2, 1), 5, 3); // [3, 1, 2, 2, 1] can be rearranged (output: 5)

        // Edge test: all same value
        testCase(Arrays.asList(5, 5, 5, 5, 5), 5, 4); // All same (output: 5)

        // Edge test: all values diff by >1
        testCase(Arrays.asList(1, 3, 5, 7, 9), 1, 5); // Only one can be chosen

        // Edge test: large data (stress test)
        List<Integer> bigTest = IntStream.range(0, 200_000).mapToObj(i -> 100).collect(Collectors.toList());
        testCase(bigTest, 200_000, 6);

        // Edge test: alternating
        testCase(Arrays.asList(1,2,1,2,1,2,1,2), 8, 7);

        // Custom: [1, 2, 2, 2, 3, 3]
        testCase(Arrays.asList(1, 2, 2, 2, 3, 3), 5, 8);
    }
}
