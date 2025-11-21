package com.interview.notes.code.year.y2024.dec24.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LastTwoDigitsSolution {

    /**
     * This function takes a list of integers and returns the last two digits
     * of their product as an integer.
     * Example: If the product is 250, it returns 50.
     * If the product is 1, it returns 1 (but we know we must print 01 externally).
     */
    public static int solve(List<Integer> ar) {
        // Maintain product modulo 100 to avoid large numbers
        int product = 1;
        for (int val : ar) {
            product = (product * val) % 100;
        }
        return product;
    }

    // A helper method to format the output to always be two digits
    public static String formatTwoDigits(int num) {
        // Ensure always two digits by formatting with leading zero if needed
        return String.format("%02d", num);
    }

    public static void main(String[] args) {
        // Test cases:
        // Input format is conceptual: we directly pass arrays as needed.

        // Each test will print:
        // Test case description
        // Expected output
        // Actual output
        // Pass/Fail

        List<TestCase> testCases = new ArrayList<>();

        // Minimal reproducible examples and edge cases
        testCases.add(new TestCase("Example #1: [25, 10]", Arrays.asList(25, 10), "50")); // 25*10=250 last two digits=50
        testCases.add(new TestCase("Example #2: [2, 4, 5]", Arrays.asList(2, 4, 5), "40")); // 2*4*5=40 last two=40
        testCases.add(new TestCase("Single digit product: [1]", List.of(1), "01")); // product=1 last two=01
        testCases.add(new TestCase("[100]", List.of(100), "00")); // product=100 last two=00
        testCases.add(new TestCase("[0, 10, 50]", Arrays.asList(0, 10, 50), "00")); // product=0 last two=00
        testCases.add(new TestCase("[99, 99, 99, 99, 99]", Arrays.asList(99, 99, 99, 99, 99), null));
        // We'll compute the expected for the large product test below:

        // Compute the expected large product test:
        // 99*99=9801 last two digits=01
        // 01*99=99 last two=99
        // 99*99=9801 last two=01
        // 01*99=99 last two=99
        // Final: 99 -> "99"
        testCases.get(testCases.size() - 1).expected = "99";

        // Run tests
        for (TestCase tc : testCases) {
            int result = solve(tc.input);
            String formatted = formatTwoDigits(result);
            boolean pass = formatted.equals(tc.expected);
            System.out.println("Test: " + tc.description);
            System.out.println("Expected: " + tc.expected + " | Actual: " + formatted);
            System.out.println(pass ? "PASS" : "FAIL");
            System.out.println("---------");
        }

        // Test with large data (performance check)
        // Large data scenario: 100 elements of random numbers
        // Since we are mod 100, performance is not a problem.
        List<Integer> largeInput = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            largeInput.add((int) (Math.random() * 100) + 1);
        }
        int largeResult = solve(largeInput);
        // We don’t have a predefined expected for random test, just ensure no error occurs.
        System.out.println("Large Data Test (100 elements): " + formatTwoDigits(largeResult));
        System.out.println("PASS if no exception occurred.");
    }

    private static class TestCase {
        String description;
        List<Integer> input;
        String expected;

        TestCase(String description, List<Integer> input, String expected) {
            this.description = description;
            this.input = input;
            this.expected = expected;
        }
    }
}
