package com.interview.notes.code.year.y2025.feb25.Amazon.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProductGroupSolution {
    public static int maximizeGroups(List<Integer> products) {
        // Convert to ArrayList to allow modifications
        ArrayList<Integer> productList = new ArrayList<>(products);
        // Remove zeros
        productList.removeIf(x -> x == 0);

        int groups = 0;
        int currentGroupSize = 1;

        while (true) {
            // Sort in ascending order
            Collections.sort(productList);

            // Check if we have enough different products
            if (productList.size() < currentGroupSize) {
                break;
            }

            // Take items from first currentGroupSize products
            for (int i = 0; i < currentGroupSize; i++) {
                productList.set(i, productList.get(i) - 1);
            }

            // Remove depleted products
            productList.removeIf(x -> x == 0);

            groups++;
            currentGroupSize++;
        }

        return groups;
    }

    public static void main(String[] args) {
        // Create test cases
        List<TestCase> testCases = new ArrayList<>();

        // Sample test cases
        testCases.add(new TestCase(
                Arrays.asList(1, 2, 7),
                3,
                "Sample Case 0"
        ));

        testCases.add(new TestCase(
                Arrays.asList(1, 2, 8, 9),
                4,
                "Sample Case 1"
        ));

        // Edge cases
        testCases.add(new TestCase(
                Arrays.asList(0),
                0,
                "Single zero"
        ));

        testCases.add(new TestCase(
                Arrays.asList(1),
                1,
                "Single product"
        ));

        testCases.add(new TestCase(
                Arrays.asList(1, 1, 1),
                2,
                "Equal quantities"
        ));

        testCases.add(new TestCase(
                Arrays.asList(10, 10, 10, 10),
                4,
                "Large equal quantities"
        ));

        // Run tests
        boolean allPassed = true;
        int testNumber = 1;

        System.out.println("Running tests...\n");

        for (TestCase test : testCases) {
            System.out.println("Test " + testNumber + ": " + test.description);
            System.out.println("Input: " + test.input);

            // Create copy of input to avoid modification
            List<Integer> inputCopy = new ArrayList<>(test.input);
            int result = maximizeGroups(inputCopy);
            boolean passed = result == test.expected;

            System.out.println("Expected: " + test.expected);
            System.out.println("Got: " + result);
            System.out.println("Status: " + (passed ? "PASS" : "FAIL"));
            System.out.println("--------------------");

            if (!passed) allPassed = false;
            testNumber++;
        }

        // Large input test
        System.out.println("Large Input Test:");
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            largeInput.add(1000000000);
        }

        long startTime = System.currentTimeMillis();
        int largeResult = maximizeGroups(new ArrayList<>(largeInput));
        long endTime = System.currentTimeMillis();

        System.out.println("Input size: 100,000 elements");
        System.out.println("Result: " + largeResult);
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        System.out.println("--------------------");

        // Final status
        System.out.println("\nFinal Status: " + (allPassed ? "ALL TESTS PASSED" : "SOME TESTS FAILED"));
    }

    // Test case class
    static class TestCase {
        List<Integer> input;
        int expected;
        String description;

        TestCase(List<Integer> input, int expected, String description) {
            this.input = input;
            this.expected = expected;
            this.description = description;
        }
    }
}
