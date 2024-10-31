package com.interview.notes.code.months.oct24.amazon.test26;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AmazonBooksSolution {

    public static List<List<Integer>> buyVolumes(List<Integer> volumes) {
        int n = volumes.size();
        List<List<Integer>> result = new ArrayList<>(n);
        boolean[] purchased = new boolean[n + 1];
        int nextToPurchase = 1;

        for (int day = 0; day < n; day++) {
            int currentVolume = volumes.get(day);
            List<Integer> todayPurchases = new ArrayList<>();

            // Check if we can purchase the current volume and any subsequent ones
            while (nextToPurchase <= n && (purchased[nextToPurchase - 1] || nextToPurchase == currentVolume)) {
                purchased[nextToPurchase - 1] = true;
                todayPurchases.add(nextToPurchase);
                nextToPurchase++;
            }

            if (todayPurchases.isEmpty()) {
                result.add(Collections.singletonList(-1));
            } else {
                result.add(todayPurchases);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample Case 0
        testCases.add(new TestCase(
                Arrays.asList(1, 4, 3, 2, 5),
                Arrays.asList(
                        Collections.singletonList(1),
                        Collections.singletonList(-1),
                        Collections.singletonList(-1),
                        Arrays.asList(2, 3, 4),
                        Collections.singletonList(5)
                )
        ));

        // Sample Case 1
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 3),
                Arrays.asList(
                        Collections.singletonList(1),
                        Collections.singletonList(2),
                        Collections.singletonList(3)
                )
        ));

        // Additional Test Case: Reverse order
        testCases.add(new TestCase(
                Arrays.asList(5, 4, 3, 2, 1),
                Arrays.asList(
                        Collections.singletonList(-1),
                        Collections.singletonList(-1),
                        Collections.singletonList(-1),
                        Collections.singletonList(-1),
                        Arrays.asList(1, 2, 3, 4, 5)
                )
        ));

        // Large Test Case
        List<Integer> largeInput = new ArrayList<>();
        List<List<Integer>> largeExpected = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            largeInput.add(i);
            largeExpected.add(Collections.singletonList(i));
        }
        testCases.add(new TestCase(largeInput, largeExpected));

        // Run test cases
        for (int i = 0; i < testCases.size(); i++) {
            TestCase testCase = testCases.get(i);
            List<List<Integer>> result = buyVolumes(testCase.input);
            boolean passed = result.equals(testCase.expected);
            System.out.println("Test Case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            if (!passed) {
                System.out.println("Expected: " + testCase.expected);
                System.out.println("Actual: " + result);
            }
        }
    }

    static class TestCase {
        List<Integer> input;
        List<List<Integer>> expected;

        TestCase(List<Integer> input, List<List<Integer>> expected) {
            this.input = input;
            this.expected = expected;
        }
    }
}
