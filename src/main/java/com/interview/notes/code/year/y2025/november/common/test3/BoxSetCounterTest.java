package com.interview.notes.code.year.y2025.november.common.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Outcome {

    public static int solve(List<Integer> a) {
        if (a == null || a.isEmpty()) {
            return 0;
        }

        List<Integer> sortedDistinctBoxes = a.stream()
                                             .distinct()
                                             .sorted()
                                             .collect(Collectors.toList());

        if (sortedDistinctBoxes.isEmpty()) {
            return 0;
        }

        int setCounter = 1;

        for (int i = 1; i < sortedDistinctBoxes.size(); i++) {
            if (sortedDistinctBoxes.get(i) != sortedDistinctBoxes.get(i - 1) + 1) {
                setCounter++;
            }
        }

        return setCounter;
    }
}

public class BoxSetCounterTest {

    public static void main(String[] args) {
        int testNumber = 1;
        int passCount = 0;
        int failCount = 0;

        List<TestCase> testCases = new ArrayList<>();

        testCases.add(new TestCase(Arrays.asList(3, 4, 5, 8, 9, 11), 3, "Problem Example 1"));
        testCases.add(new TestCase(Arrays.asList(1, 2, 3, 5, 6, 8), 3, "Problem Example 2"));
        testCases.add(new TestCase(Arrays.asList(2, 1, 11, 3), 2, "Problem Example 3 (Unsorted)"));
        testCases.add(new TestCase(new ArrayList<>(), 0, "Edge Case: Empty List"));
        testCases.add(new TestCase(Collections.singletonList(100), 1, "Edge Case: Single Box"));
        testCases.add(new TestCase(Arrays.asList(1, 2, 3, 4, 5), 1, "All Consecutive"));
        testCases.add(new TestCase(Arrays.asList(10, 20, 30, 40, 50), 5, "None Consecutive"));
        testCases.add(new TestCase(Arrays.asList(1, 1, 1, 2, 3, 3, 5, 6, 6), 3, "With Duplicates"));
        testCases.add(new TestCase(Arrays.asList(5, 4, 3, 2, 1), 1, "Reversed Consecutive"));

        List<Integer> largeDataSet = new ArrayList<>();
        IntStream.range(0, 50000).forEach(i -> {
            largeDataSet.add(i);
            largeDataSet.add(i + 100000); 
        });
        testCases.add(new TestCase(largeDataSet, 2, "Large Data: Two distinct large sets"));

        List<Integer> largeConsecutiveSet = new ArrayList<>();
        IntStream.range(0, 100000).forEach(largeConsecutiveSet::add);
        testCases.add(new TestCase(largeConsecutiveSet, 1, "Large Data: One large consecutive set"));

        for (TestCase tc : testCases) {
            try {
                int actual = Outcome.solve(tc.input);
                if (actual == tc.expected) {
                    System.out.println("Test " + testNumber + ": PASS | " + tc.name);
                    passCount++;
                } else {
                    System.out.println("Test " + testNumber + ": FAIL | " + tc.name + " | Expected: " + tc.expected + ", Actual: " + actual);
                    failCount++;
                }
            } catch (Exception e) {
                System.out.println("Test " + testNumber + ": ERROR | " + tc.name + " | Exception: " + e.getMessage());
                failCount++;
            }
            testNumber++;
        }

        System.out.println("\n--- Test Summary ---");
        System.out.println("Total Tests: " + (passCount + failCount));
        System.out.println("Passed: " + passCount);
        System.out.println("Failed: " + failCount);
    }

    static class TestCase {
        List<Integer> input;
        int expected;
        String name;

        TestCase(List<Integer> input, int expected, String name) {
            this.input = input;
            this.expected = expected;
            this.name = name;
        }
    }
}