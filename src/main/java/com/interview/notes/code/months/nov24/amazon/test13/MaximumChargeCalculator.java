package com.interview.notes.code.months.nov24.amazon.test13;

import java.util.*;

public class MaximumChargeCalculator {
    public static long getMaximumCharge(List<Integer> charge) {
        long maxCharge = Long.MIN_VALUE;
        int n = charge.size();

        for (int i = 0; i < n; i++) {
            int currentCharge = charge.get(i);
            if (currentCharge > maxCharge) {
                maxCharge = currentCharge;
            }

            if (currentCharge > 0) {
                long sum = currentCharge;
                int j = i + 1;
                while (j < n) {
                    int nextCharge = charge.get(j);
                    if (nextCharge < 0) {
                        sum += nextCharge;
                        j++;
                    } else if (nextCharge > 0 && j - i > 1) {
                        // Non-adjacent positive charge found
                        sum += nextCharge;
                        if (sum > maxCharge) {
                            maxCharge = sum;
                        }
                        break;
                    } else {
                        // Adjacent positive charge; cannot merge
                        break;
                    }
                }
                if (sum > maxCharge) {
                    maxCharge = sum;
                }
            }
        }

        return maxCharge;
    }

    // Main method for testing
    public static void main(String[] args) {
        testGetMaximumCharge();
    }

    public static void testGetMaximumCharge() {
        List<TestCase> testCases = new ArrayList<>();

        // Provided Test Case 1
        testCases.add(new TestCase(
                Arrays.asList(-2, 4, 9, 1, -1),
                9
        ));

        // Provided Test Case 2
        testCases.add(new TestCase(
                Arrays.asList(-1, 3, 2),
                3
        ));

        // Additional Test Case
        testCases.add(new TestCase(
                Arrays.asList(-3, 1, 4, -1, 5, -9),
                9
        ));

        // Edge Case: All negative charges
        testCases.add(new TestCase(
                Arrays.asList(-5, -2, -8, -1),
                -1
        ));

        // Large Data Test Case
        List<Integer> largeTestCase = new ArrayList<>();
        for (int i = 0; i < 200000; i++) {
            largeTestCase.add(i % 2 == 0 ? -i : i);
        }
        testCases.add(new TestCase(largeTestCase, 199999));

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            long result = getMaximumCharge(tc.charge);
            if (result == tc.expected) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expected + ", Got: " + result);
            }
        }
    }
}

class TestCase {
    List<Integer> charge;
    long expected;

    TestCase(List<Integer> charge, long expected) {
        this.charge = charge;
        this.expected = expected;
    }
}
