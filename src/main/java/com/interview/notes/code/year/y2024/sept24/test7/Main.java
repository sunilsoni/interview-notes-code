package com.interview.notes.code.year.y2024.sept24.test7;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        // Test cases
        int[][] testCases = {
                {2, 3, 2, 2, 3, 4},
                {1, 1, 1, 1},
                {5, 4, 3, 2, 1},
                {}
        };

        for (int i = 0; i < testCases.length; i++) {
            Map<Integer, Long> result = countElements(testCases[i]);
            System.out.println("Test Case " + (i + 1) + ":");
            System.out.println("Input: " + Arrays.toString(testCases[i]));
            System.out.println("Output: " + result);
            System.out.println();
        }
    }

    /**
     * Counts the occurrences of each element in the given array using Java 8 streams.
     *
     * @param arr The input integer array
     * @return A Map with elements as keys and their counts as values
     */
    public static Map<Integer, Long> countElements(int[] arr) {
        return Arrays.stream(arr).boxed().collect(Collectors.groupingBy(i -> i, Collectors.counting()));
    }
}
