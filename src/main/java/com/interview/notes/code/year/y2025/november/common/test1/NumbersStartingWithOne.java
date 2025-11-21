package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Class name represents the problem purpose clearly
public class NumbersStartingWithOne {

    // Method to find all numbers starting with '1' using Java 8 Stream API
    public static List<Integer> findNumbersStartingWithOne(List<Integer> numbers) {
        // Convert list of integers into a stream for functional processing
        return numbers.stream()
                // Convert each number to string, check if it starts with '1'
                .filter(num -> String.valueOf(num).startsWith("1"))
                // Collect the filtered numbers back into a list
                .collect(Collectors.toList());
    }

    // Main method for testing different test cases
    public static void main(String[] args) {
        // Test Case 1: Small list with mixed values
        List<Integer> test1 = Arrays.asList(10, 15, 20, 31, 111, 98);
        List<Integer> expected1 = Arrays.asList(10, 15, 111);
        testAndPrint(test1, expected1);

        // Test Case 2: No numbers start with 1
        List<Integer> test2 = Arrays.asList(22, 33, 44, 55);
        List<Integer> expected2 = Collections.emptyList();
        testAndPrint(test2, expected2);

        // Test Case 3: All numbers start with 1
        List<Integer> test3 = Arrays.asList(1, 10, 12, 19, 100);
        List<Integer> expected3 = Arrays.asList(1, 10, 12, 19, 100);
        testAndPrint(test3, expected3);

        // Test Case 4: Edge case – empty list input
        List<Integer> test4 = new ArrayList<>();
        List<Integer> expected4 = new ArrayList<>();
        testAndPrint(test4, expected4);

        // Test Case 5: Large data input to test performance
        List<Integer> largeList = IntStream.rangeClosed(1, 1_000_000)
                .boxed()
                .collect(Collectors.toList());
        // Only numbers between 1 and 199999 start with 1 in this range
        List<Integer> largeResult = findNumbersStartingWithOne(largeList);
        boolean largePass = largeResult.size() == 199999; // 1 to 199999
        System.out.println("Large Data Test: " + (largePass ? "PASS" : "FAIL"));
    }

    // Helper method to check if output matches expected and print result
    private static void testAndPrint(List<Integer> input, List<Integer> expected) {
        // Call our method to get actual result
        List<Integer> actual = findNumbersStartingWithOne(input);

        // Compare both lists
        boolean pass = actual.equals(expected);

        // Print clear test output
        System.out.println("Input: " + input);
        System.out.println("Expected: " + expected);
        System.out.println("Actual: " + actual);
        System.out.println("Result: " + (pass ? "PASS" : "FAIL"));
        System.out.println("------------------------------------");
    }
}
