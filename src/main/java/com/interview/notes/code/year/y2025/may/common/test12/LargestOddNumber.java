package com.interview.notes.code.year.y2025.may.common.test12;

import java.util.ArrayList;
import java.util.List;

public class LargestOddNumber {

    public static int findLargestOdd(String input) {
        // Input validation
        if (input == null || input.isEmpty()) {
            return -1;
        }

        // Store all numbers found in string
        List<Integer> numbers = new ArrayList<>();

        // StringBuilder to build numeric groupings
        StringBuilder currentNumber = new StringBuilder();

        // Iterate through each character in input string
        for (char c : input.toCharArray()) {
            // If character is digit, add to current number
            if (Character.isDigit(c)) {
                currentNumber.append(c);
            }
            // If non-digit and we have collected numbers, process them
            else if (currentNumber.length() > 0) {
                try {
                    numbers.add(Integer.parseInt(currentNumber.toString()));
                    currentNumber.setLength(0); // Reset builder
                } catch (NumberFormatException e) {
                    // Handle very large numbers gracefully
                    currentNumber.setLength(0);
                }
            }
        }

        // Handle last number if string ends with digits
        if (currentNumber.length() > 0) {
            try {
                numbers.add(Integer.parseInt(currentNumber.toString()));
            } catch (NumberFormatException e) {
                // Handle very large numbers
            }
        }

        // Find largest odd number using Stream API
        return numbers.stream()
                .filter(n -> n % 2 != 0)  // Filter odd numbers
                .mapToInt(Integer::intValue)
                .max()  // Get maximum value
                .orElse(-1);  // Return -1 if no odd numbers found
    }

    public static void main(String[] args) {
        // Test cases
        String[] tests = {
                "gt12cty65mt1",        // Expected: 65
                "mkf43kd1cmk32k1mv123", // Expected: 123
                "246",                  // Expected: -1 (no odd numbers)
                "",                     // Expected: -1 (empty string)
                "abc",                  // Expected: -1 (no numbers)
                "999999999999",        // Large number test
                "1a2b3c4d5e"          // Expected: 5
        };

        // Run tests and verify results
        for (String test : tests) {
            int result = findLargestOdd(test);
            System.out.println("Input: " + test);
            System.out.println("Result: " + result);
            System.out.println("-------------------");
        }
    }
}
