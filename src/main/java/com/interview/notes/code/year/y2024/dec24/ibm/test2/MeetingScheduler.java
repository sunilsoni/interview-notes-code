package com.interview.notes.code.year.y2024.dec24.ibm.test2;

import java.util.*;

public class MeetingScheduler {
    public static int maxMeetings(List<Integer> effectiveness) {
        int n = effectiveness.size();
        List<Integer> sorted = new ArrayList<>(effectiveness);
        Collections.sort(sorted, Collections.reverseOrder());

        int maxMeetings = 0;
        long currentSum = 0;
        Set<Integer> used = new HashSet<>();

        // Try to maximize positive sum by picking largest numbers first
        for (int i = 0; i < n; i++) {
            int value = sorted.get(i);
            if (value >= 0 || (currentSum + value > 0)) {
                currentSum += value;
                used.add(value);
                maxMeetings++;
            }
        }

        // Verify if this arrangement is actually possible
        for (int val : effectiveness) {
            if (!used.contains(val)) {
                return maxMeetings;
            }
            used.remove(val);
            currentSum = 0;

            // Validate running sum stays positive
            for (int usedVal : used) {
                currentSum += usedVal;
                if (currentSum <= 0) {
                    return maxMeetings - 1;
                }
            }
        }

        return maxMeetings;
    }

    public static void main(String[] args) {
        // Test cases
        runTestCase(1, Arrays.asList(1, -20, 3, -2), 3);
        runTestCase(2, Arrays.asList(-3, 0, 2, 1), 3);
        runTestCase(3, Arrays.asList(-3, 0, -2), 0);

        // Edge cases
        runTestCase(4, List.of(1), 1);
        runTestCase(5, List.of(-1), 0);
        runTestCase(6, List.of(0), 1);

        // Large numbers
        runTestCase(7, Arrays.asList(1000000000, -999999999), 2);

        // Longer sequence
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(i % 2 == 0 ? 1 : -1);
        }
        runTestCase(8, largeInput, 50000);

        // Zero sum sequence
        runTestCase(9, Arrays.asList(1, -1, 1, -1), 2);

        // Complex case
        runTestCase(10, Arrays.asList(5, -3, 2, -4, 1, -2, 3, -1), 6);
    }

    private static void runTestCase(int testNumber, List<Integer> input, int expected) {
        long startTime = System.nanoTime();
        int result = maxMeetings(input);
        long endTime = System.nanoTime();

        boolean passed = result == expected;
        System.out.printf("Test Case %d: %s%n", testNumber, passed ? "PASS" : "FAIL");
        if (!passed) {
            System.out.printf("Expected: %d, Got: %d%n", expected, result);
        }

        // Print execution time for large inputs
        if (input.size() > 1000) {
            System.out.printf("Execution time: %.2f ms%n", (endTime - startTime) / 1_000_000.0);
        }
    }
}
