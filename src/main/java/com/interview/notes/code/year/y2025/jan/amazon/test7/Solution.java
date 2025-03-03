package com.interview.notes.code.year.y2025.jan.amazon.test7;

import java.util.*;

public class Solution {

    public static int getMaximumEvents(List<Integer> payload) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        // Count frequencies
        for (int num : payload) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        // Unique elements sorted
        List<Integer> uniqueAsc = new ArrayList<>(freqMap.keySet());
        Collections.sort(uniqueAsc);

        List<Integer> uniqueDesc = new ArrayList<>(uniqueAsc);
        Collections.reverse(uniqueDesc);

        // Sequences A, B, C
        List<Integer> sequenceA = new ArrayList<>();
        List<Integer> sequenceB = new ArrayList<>();
        List<Integer> sequenceC = new ArrayList<>();

        Map<Integer, Integer> freqMapCopy = new HashMap<>(freqMap);

        // Build Sequence A (First Increasing Sequence)
        int prev = Integer.MIN_VALUE;
        for (int num : uniqueAsc) {
            if (freqMapCopy.get(num) > 0 && num > prev) {
                sequenceA.add(num);
                freqMapCopy.put(num, freqMapCopy.get(num) - 1);
                prev = num;
            }
        }

        // Build Sequence B (Decreasing Sequence)
        prev = Integer.MAX_VALUE;
        for (int num : uniqueDesc) {
            if (freqMapCopy.get(num) > 0 && num < prev) {
                sequenceB.add(num);
                freqMapCopy.put(num, freqMapCopy.get(num) - 1);
                prev = num;
            }
        }

        // Build Sequence C (Second Increasing Sequence)
        prev = Integer.MIN_VALUE;
        for (int num : uniqueAsc) {
            while (freqMapCopy.get(num) > 0 && num > prev) {
                sequenceC.add(num);
                freqMapCopy.put(num, freqMapCopy.get(num) - 1);
                prev = num;
            }
        }

        int totalEvents = sequenceA.size() + sequenceB.size() + sequenceC.size();

        // Return total number of events selected
        return totalEvents;
    }

    public static void main(String[] args) {
        // Testing with provided and new test cases
        testGetMaximumEvents();
    }

    // Method to test the function with sample and additional inputs
    public static void testGetMaximumEvents() {
        List<TestCase> tests = new ArrayList<>();

        // Test Case 1 (Sample Case 1)
        tests.add(new TestCase(
                Arrays.asList(1, 100),
                2
        ));

        // Test Case 0 (Sample Case 0)
        tests.add(new TestCase(
                Arrays.asList(5, 5, 2, 1, 3, 4, 5),
                6 // Adjusted to match sample output
        ));

        // Additional Test Case
        tests.add(new TestCase(
                Arrays.asList(1, 3, 5, 4, 2, 6, 8, 7, 9),
                9
        ));

        // New Test Case with duplicates
        tests.add(new TestCase(
                Arrays.asList(1, 2, 2, 2, 3, 4, 5, 5, 6),
                8 // Expected total events
        ));

        // New Test Case with large input
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 1; i <= 200000; i++) {
            largeInput.add(i);
        }
        tests.add(new TestCase(
                largeInput,
                200000
        ));

        int testCaseNumber = 1;
        for (TestCase test : tests) {
            int expected = test.expectedOutput;
            int actual = getMaximumEvents(test.payload);

            String result = (actual == expected) ? "PASS" : "FAIL";
            System.out.println("Test Case " + testCaseNumber + ": " + result);
            System.out.println("Expected Output: " + expected + ", Actual Output: " + actual + "\n");
            testCaseNumber++;
        }
    }

    // Helper class to store test cases
    static class TestCase {
        List<Integer> payload;
        int expectedOutput;

        TestCase(List<Integer> payload, int expectedOutput) {
            this.payload = payload;
            this.expectedOutput = expectedOutput;
        }
    }
}