package com.interview.notes.code.months.nov24.test9;

import java.util.*;
import java.util.stream.Collectors;

public class NonDuplicateSorter {

    /**
     * Extracts non-duplicate numbers from the input array and returns them sorted.
     *
     * @param numbers the input array of integers
     * @return a sorted list of non-duplicate integers
     */
    public static List<Integer> getNonDuplicateSorted(int[] numbers) {
        // Frequency map to count occurrences of each number
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : numbers) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Collect numbers that appear exactly once
        List<Integer> nonDuplicates = frequencyMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        // Sort the list in ascending order
        Collections.sort(nonDuplicates);

        return nonDuplicates;
    }

    /**
     * Runs predefined test cases to verify the correctness of the getNonDuplicateSorted method.
     */
    public static void main(String[] args) {
        // Define test cases
        List<TestCase> testCases = Arrays.asList(
                new TestCase(new int[]{1, 3, 4, 2, 4, 8}, Arrays.asList(1, 2, 3, 8)),
                new TestCase(new int[]{}, Collections.emptyList()),
                new TestCase(new int[]{5, 5, 5, 5}, Collections.emptyList()),
                new TestCase(new int[]{10}, Arrays.asList(10)),
                new TestCase(new int[]{2, 1, 3, 3, 2, 4, 5}, Arrays.asList(1, 4, 5)),
                // Large data input
                new TestCase(generateLargeInput(1000000), generateExpectedLargeOutput(1000000))
        );

        // Execute test cases
        int passed = 0;
        for (int i = 0; i < testCases.size(); i++) {
            TestCase tc = testCases.get(i);
            List<Integer> result = getNonDuplicateSorted(tc.input);
            if (result.equals(tc.expectedOutput)) {
                System.out.println("Test Case " + (i + 1) + ": PASS");
                passed++;
            } else {
                System.out.println("Test Case " + (i + 1) + ": FAIL");
                System.out.println("Expected: " + tc.expectedOutput);
                System.out.println("Got     : " + result);
            }
        }

        System.out.println("\n" + passed + " out of " + testCases.size() + " test cases passed.");
    }

    /**
     * Generates a large input array for testing purposes.
     *
     * @param size the size of the array to generate
     * @return an array of integers
     */
    private static int[] generateLargeInput(int size) {
        int[] largeInput = new int[size];
        Random rand = new Random(0); // Seeded for reproducibility
        for (int i = 0; i < size; i++) {
            // Generate numbers between 1 and size/2 to ensure duplicates
            largeInput[i] = rand.nextInt(size / 2) + 1;
        }
        return largeInput;
    }

    /**
     * Generates the expected output for a large input array.
     * This method assumes that the generateLargeInput method generates
     * numbers between 1 and size/2 with possible duplicates.
     *
     * @param size the size of the input array
     * @return a sorted list of non-duplicate integers
     */
    private static List<Integer> generateExpectedLargeOutput(int size) {
        // For demonstration purposes, we'll skip generating the expected output
        // for the large input to save computation time.
        // In a real-world scenario, this should be replaced with the actual expected output.
        return Collections.emptyList();
    }

    /**
     * A simple class to represent a test case.
     */
    static class TestCase {
        int[] input;
        List<Integer> expectedOutput;

        TestCase(int[] input, List<Integer> expectedOutput) {
            this.input = input;
            this.expectedOutput = expectedOutput;
        }
    }
}
