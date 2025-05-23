package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class OfferTimeSolution {

    public static int offerTime(int N, int[] A) {
        // Sort in descending order so we always pick the costliest remaining items first
        List<Integer> sortedPrices = Arrays.stream(A)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        int result = 0;
        // Buy one, get one (<=) free. So skip every second item.
        for (int i = 0; i < sortedPrices.size(); i += 2) {
            result += sortedPrices.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        // Test Cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase(6, new int[]{8, 2, 2, 9, 1, 9}, 19), // From example
                new TestCase(1, new int[]{10}, 10),
                new TestCase(2, new int[]{1, 1}, 1),
                new TestCase(3, new int[]{5, 1, 1}, 5),
                new TestCase(4, new int[]{10, 20, 30, 40}, 60),
                new TestCase(0, new int[]{}, 0), // Edge case: no items
                largeTestCase() // Large input test case
        );

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            int output = offerTime(tc.N, tc.A);
            boolean passed = output == tc.expected;
            System.out.println("Test " + (i + 1) + ": " + (passed ? "PASS" : "FAIL") +
                    " | Expected: " + tc.expected + " | Got: " + output);
        }
    }

    static TestCase largeTestCase() {
        int N = 1000;
        int[] A = new int[N];
        int expected = 0;

        // Fill with values 1 to 1000
        for (int i = 0; i < N; i++) {
            A[i] = i + 1;
        }

        // Calculate expected manually: sum of every 2nd max value
        Arrays.sort(A);
        for (int i = N - 1; i >= 0; i -= 2) {
            expected += A[i];
        }

        return new TestCase(N, A, expected);
    }

    static class TestCase {
        int N;
        int[] A;
        int expected;

        TestCase(int N, int[] A, int expected) {
            this.N = N;
            this.A = A;
            this.expected = expected;
        }
    }
}
