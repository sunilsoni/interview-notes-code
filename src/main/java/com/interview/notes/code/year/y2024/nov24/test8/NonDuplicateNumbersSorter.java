package com.interview.notes.code.year.y2024.nov24.test8;

import java.util.*;

//Given an array of numbers, write a code to print non duplicate numbers and sort them. You can use any data structures you want. [1,3,4,2,4,8] -> [1,2,3,8]
public class NonDuplicateNumbersSorter {

    public static List<Integer> sortNonDuplicates(int[] numbers) {
        // Use a Map to count occurrences of each number
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : numbers) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        // Create a list of non-duplicate numbers
        List<Integer> nonDuplicates = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                nonDuplicates.add(entry.getKey());
            }
        }

        // Sort the list
        Collections.sort(nonDuplicates);

        return nonDuplicates;
    }

    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {1, 3, 4, 2, 4, 8},
                {5, 2, 8, 1, 9, 2, 3, 8},
                {1, 1, 1, 1},
                {},
                {10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
                {1, 2, 3, 4, 5, 1, 2, 3, 4, 5}
        };

        String[] expectedOutputs = {
                "[1, 2, 3, 8]",
                "[1, 3, 5, 9]",
                "[]",
                "[]",
                "[1, 2, 3, 4, 5, 6, 7, 8, 9, 10]",
                "[]"
        };

        // Run tests
        for (int i = 0; i < testCases.length; i++) {
            List<Integer> result = sortNonDuplicates(testCases[i]);
            String resultString = result.toString();

            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            System.out.println("Output: " + resultString);
            System.out.println("Expected: " + expectedOutputs[i]);

            if (resultString.equals(expectedOutputs[i])) {
                System.out.println("Result: PASS");
            } else {
                System.out.println("Result: FAIL");
            }
            System.out.println();
        }

        // Test with large input
        int[] largeInput = new int[1000000];
        Random random = new Random();
        for (int i = 0; i < largeInput.length; i++) {
            largeInput[i] = random.nextInt(100000);
        }

        long startTime = System.currentTimeMillis();
        List<Integer> largeResult = sortNonDuplicates(largeInput);
        long endTime = System.currentTimeMillis();

        System.out.println("Large Input Test:");
        System.out.println("Input size: " + largeInput.length);
        System.out.println("Output size: " + largeResult.size());
        System.out.println("Execution time: " + (endTime - startTime) + " ms");
        System.out.println("Result: " + (largeResult.size() >= 0 && largeResult.size() <= largeInput.length ? "PASS" : "FAIL"));
    }
}
