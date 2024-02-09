package com.interview.notes.code.months.jan24.test8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Given an• list-of• numbers, return the list with numbers rearranged in order of number of occurrences in• descending order. •If multiple numbers occur the same number of times, the order of these numbers in • the • input list-should-be maintained.|
 */
class RearrangedNumbers {

    public static void main(final String[] args) {
        runTests();
    }

    public static void runTests() {
        // Test cases and expected results
        int[] testCase1 = {2, 5, 2, 6, 8, 5, 8};
        int[] expectedResponse = {8, 8, 2, 2, 5, 5, 6};
        int[] actualResponse = new RearrangedNumbers().sortByOccurrence(testCase1);

        // Check if the test case passes
        if (!Arrays.equals(actualResponse, expectedResponse)) {
            System.out.println("Test Case Failed.");
            return;
        }

        System.out.println("Test Case Passed.");
    }

    public int[] sortByOccurrence(final int[] reqArray) {
        // HashMap to store the frequency of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        // LinkedHashMap to maintain the insertion order
        Map<Integer, Integer> sortedMap = new LinkedHashMap<>();

        // Counting occurrences of each number
        for (int number : reqArray) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        // Sort the array by frequency and maintain the insertion order
        Arrays.stream(reqArray)
                .boxed()
                .sorted((n1, n2) -> {
                    int freqCompare = frequencyMap.get(n2).compareTo(frequencyMap.get(n1));
                    if (freqCompare == 0)
                        return Arrays.asList(reqArray).indexOf(n1) - Arrays.asList(reqArray).indexOf(n2);
                    else
                        return freqCompare;
                })
                .forEach(n -> sortedMap.put(n, frequencyMap.get(n)));

        // Prepare the result array based on the sorted map
        int[] result = new int[reqArray.length];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : sortedMap.entrySet()) {
            int key = entry.getKey();
            int count = entry.getValue();
            for (int j = 0; j < count; j++, i++) {
                result[i] = key;
            }
        }

        return result;
    }
}
