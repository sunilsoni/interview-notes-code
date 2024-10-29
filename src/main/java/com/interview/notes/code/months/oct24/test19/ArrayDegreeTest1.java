package com.interview.notes.code.months.oct24.test19;

import java.util.*;

public class ArrayDegreeTest1 {
    public static void main(String[] args) {
        testSolution();
    }

    private static void testSolution() {
        // Test case 1
        List<Integer> test1 = Arrays.asList(4, 1, 1, 2, 2, 1, 3, 3);
        List<Integer> expected1 = Arrays.asList(1, 1, 2, 2, 1);
        runTest("Test 1", test1, expected1);

        // Test case 2
        List<Integer> test2 = Arrays.asList(5, 1, 2, 2, 3, 1);
        List<Integer> expected2 = Arrays.asList(2, 2);
        runTest("Test 2", test2, expected2);

        // Test case 3 - Large input
        List<Integer> test3 = generateLargeInput(1000000, 100);
        runTest("Large Input Test", test3, null);

        // Test case 4 - All same elements
        List<Integer> test4 = Arrays.asList(1, 1, 1, 1, 1);
        List<Integer> expected4 = Arrays.asList(1);
        runTest("All Same Elements", test4, expected4);

        // Test case 5 - Single element
        List<Integer> test5 = Arrays.asList(1);
        List<Integer> expected5 = Arrays.asList(1);
        runTest("Single Element", test5, expected5);
    }

    public static List<Integer> solve(List<Integer> a) {
        if (a == null || a.isEmpty()) return new ArrayList<>();

        Map<Integer, Integer> freqMap = new HashMap<>();
        Map<Integer, Integer> firstOccurrence = new HashMap<>();
        Map<Integer, Integer> lastOccurrence = new HashMap<>();

        // Record frequencies and positions
        int maxFreq = 0;
        for (int i = 0; i < a.size(); i++) {
            int num = a.get(i);
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
            if (!firstOccurrence.containsKey(num)) {
                firstOccurrence.put(num, i);
            }
            lastOccurrence.put(num, i);
            maxFreq = Math.max(maxFreq, freqMap.get(num));
        }

        // If all elements are same, return just one element
        if (freqMap.size() == 1) {
            return Arrays.asList(a.get(0));
        }

        // Find all elements with max frequency
        List<Integer> maxFreqElements = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() == maxFreq) {
                maxFreqElements.add(entry.getKey());
            }
        }

        // Find smallest subarray length
        int minLen = Integer.MAX_VALUE;
        List<Integer> result = null;

        for (int num : maxFreqElements) {
            int start = firstOccurrence.get(num);
            int end = lastOccurrence.get(num);
            int len = end - start + 1;

            if (len < minLen) {
                minLen = len;
                result = a.subList(start, end + 1);
            }
        }

        return result;
    }

    private static void runTest(String testName, List<Integer> input, List<Integer> expected) {
        try {
            long startTime = System.currentTimeMillis();
            List<Integer> result = solve(input);
            long endTime = System.currentTimeMillis();

            System.out.println(testName + ":");
            System.out.println("Input size: " + input.size());
            System.out.println("Time taken: " + (endTime - startTime) + "ms");

            if (expected != null) {
                boolean passed = result.equals(expected);
                System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
                if (!passed) {
                    System.out.println("Expected: " + expected);
                    System.out.println("Got: " + result);
                }
            } else {
                System.out.println("Result size: " + result.size());
                System.out.println("Status: PASS (Performance Test)");
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(testName + " FAILED with exception: " + e.getMessage());
            e.printStackTrace();
            System.out.println();
        }
    }

    private static List<Integer> generateLargeInput(int size, int maxValue) {
        List<Integer> input = new ArrayList<>(size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            input.add(random.nextInt(maxValue) + 1);
        }
        return input;
    }
}