package com.interview.notes.code.year.y2025.july.codesignal.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class PackageDistribution {

    public static int solution(int[] centerCapacities, String[] dailyLog) {
        int n = centerCapacities.length;

        // Mutable capacities
        int[] currCapacity = Arrays.copyOf(centerCapacities, n);
        int[] processed = new int[n];
        boolean[] closed = new boolean[n];

        int currentCenter = 0; // index to assign packages

        for (String log : dailyLog) {
            if (log.equals("PACKAGE")) {
                // Allocate package
                int startCenter = currentCenter;
                boolean allocated = false;

                while (!allocated) {
                    if (!closed[currentCenter] && currCapacity[currentCenter] > 0) {
                        processed[currentCenter]++;
                        currCapacity[currentCenter]--;
                        allocated = true;
                    }
                    currentCenter = (currentCenter + 1) % n;

                    // If full cycle completed, reset capacities for open centers
                    if (currentCenter == 0) {
                        for (int i = 0; i < n; i++) {
                            if (!closed[i]) {
                                currCapacity[i] = centerCapacities[i];
                            }
                        }
                    }
                }

            } else if (log.startsWith("CLOSURE")) {
                // Closure operation: parse center index
                String[] parts = log.split(" ");
                int closedCenter = Integer.parseInt(parts[1]);
                closed[closedCenter] = true;
            }
        }

        // Find max processed count and highest index
        int maxCount = IntStream.of(processed).max().orElse(-1);
        for (int i = n - 1; i >= 0; i--) {
            if (processed[i] == maxCount) {
                return i;
            }
        }
        return -1; // should never reach here if at least one center is open
    }

    // Helper method to check and print test results
    public static void runTest(int[] centerCapacities, String[] dailyLog, int expected, String testName) {
        int result = solution(centerCapacities, dailyLog);
        String passFail = (result == expected) ? "PASS" : "FAIL";
        System.out.printf("%s: Expected=%d, Got=%d -> %s%n", testName, expected, result, passFail);
    }

    // Main method to run all tests
    public static void main(String[] args) {
        // Provided example test case
        int[] capacities1 = {1, 2, 1, 2, 1};
        String[] dailyLog1 = {
                "PACKAGE", "PACKAGE", "CLOSURE 2", "PACKAGE",
                "CLOSURE 3", "PACKAGE", "PACKAGE"
        };
        runTest(capacities1, dailyLog1, 1, "Example Test Case");

        // Additional test case: all centers open, simple allocation
        int[] capacities2 = {2, 2};
        String[] dailyLog2 = {"PACKAGE", "PACKAGE", "PACKAGE", "PACKAGE"};
        // Both centers get 2 packages, highest index = 1
        runTest(capacities2, dailyLog2, 1, "All Open Equal Capacity");

        // Additional test case: closure on all but one
        int[] capacities3 = {3, 3, 3};
        String[] dailyLog3 = {
                "CLOSURE 0", "CLOSURE 1", "PACKAGE", "PACKAGE", "PACKAGE"
        };
        // Only center 2 open and processes all 3 packages
        runTest(capacities3, dailyLog3, 2, "Only One Center Open");

        // Additional test case: large input simulation
        int n = 100;
        int[] capacitiesLarge = new int[n];
        Arrays.fill(capacitiesLarge, 5);
        List<String> dailyLogLargeList = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            dailyLogLargeList.add("PACKAGE");
            if (i % 200 == 0) {
                dailyLogLargeList.add("CLOSURE " + (i / 200));
            }
        }
        String[] dailyLogLarge = dailyLogLargeList.toArray(new String[0]);
        // We don't know expected result here, just run to test performance
        int resultLarge = solution(capacitiesLarge, dailyLogLarge);
        System.out.println("Large Test Case Result: " + resultLarge + " (Manual check)");

        // Add more tests if needed
    }
}
