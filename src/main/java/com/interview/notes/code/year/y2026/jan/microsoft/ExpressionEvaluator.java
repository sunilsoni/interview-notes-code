package com.interview.notes.code.year.y2026.jan.microsoft;

import java.util.LinkedHashMap;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ExpressionEvaluator { // Main class definition.

    // Method to evaluate the arithmetic expression string.
    public static int evaluate(String s) {
        var stack = new Stack<Integer>(); // Use 'var' (Java 10+) for concise stack declaration to store numbers.
        int num = 0; // Variable to build the current multi-digit number being parsed.
        char op = '+'; // Keep track of the 'previous' operator, initialized to '+' to handle the first number.

        for (int i = 0; i < s.length(); i++) { // Loop through every character in the string.
            char c = s.charAt(i); // Get the character at the current index.

            if (Character.isDigit(c)) // Check if the character is a number.
                num = num * 10 + (c - '0'); // Build number: Shift existing digits left and add new digit (ASCII math).

            // If char is an operator, or it's the last character, we process the PREVIOUS operator.
            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                switch (op) { // Java 21 Switch Expression: cleaner and faster than if-else chain.
                    case '+' -> stack.push(num); // If prev op was +, push positive number to stack.
                    case '-' -> stack.push(-num); // If prev op was -, push negative number to stack.
                    case '*' -> stack.push(stack.pop() * num); // If *, pop top, multiply by current, push result back.
                    case '/' -> stack.push(stack.pop() / num); // If /, pop top, divide by current, push result back.
                } // End of switch block.
                op = c; // Update the 'previous' operator to the current character for the next iteration.
                num = 0; // Reset current number to 0 for the next integer parsing.
            } // End of operator processing.
        } // End of loop.

        // Java 8 Stream API: Convert stack to IntStream and sum all values (handles final addition/subtraction).
        return stack.stream().mapToInt(Integer::intValue).sum();
    }

    // Simple main method for testing without JUnit (handles Pass/Fail and Large Data).
    public static void main(String[] args) {
        System.out.println("--- Starting Tests ---"); // Print start message.

        // Map of Test Inputs to Expected Outputs.
        var tests = new LinkedHashMap<String, Integer>(); // Use LinkedHashMap to preserve insertion order.
        tests.put("4 * 3 + 1 / 2 - 5", 7); // The specific case from your image (12 + 0 - 5 = 7).
        tests.put("3+2*2", 7); // Standard precedence check: 3 + 4 = 7.
        tests.put(" 3/2 ", 1); // Integer division check and whitespace check: 3 / 2 = 1.
        tests.put("3 + 5 / 2", 5); // Mixed operators: 3 + 2 = 5.

        // Large Data Test: Generate a huge string "1+1+1..." repeated 100,000 times.
        // Logic: 100000 ones added together should equal 100000.
        String largeInput = IntStream.range(0, 100000).mapToObj(i -> "1").collect(Collectors.joining("+")); // Java 8 Stream to build string.
        tests.put(largeInput, 100000); // Add large data test case.

        // Iterate through all test cases and verify.
        tests.forEach((input, expected) -> { // Lambda expression to loop through map.
            long startTime = System.currentTimeMillis(); // Track start time for performance check.
            int result = evaluate(input); // Run the logic.
            long endTime = System.currentTimeMillis(); // Track end time.

            // Check if Result matches Expected.
            String status = (result == expected) ? "PASS" : "FAIL"; // Ternary operator for status.

            // Print clear output for the user.
            String displayInput = input.length() > 20 ? "Large Data Input..." : input; // Truncate display for large strings.
            System.out.printf("Test: [%s] -> Got: %d | Expected: %d | Status: %s (%d ms)%n",
                    displayInput, result, expected, status, (endTime - startTime)); // Formatted print.
        }); // End of test loop.

        System.out.println("--- All Tests Completed ---"); // End message.
    }
}