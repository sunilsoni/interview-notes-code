package com.interview.notes.code.year.y2026.jan.microsoft.test1;

import java.util.LinkedHashMap;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DoubleEvaluator {

    // Evaluate arithmetic expression with Double precision
    public static double evaluate(String s) {
        if (s == null || s.isEmpty()) return 0;

        var stack = new Stack<Double>(); // Store Doubles instead of Integers
        double num = 0;                  // Current number value
        double decimalDivisor = 0;       // Tracker for decimal places (0 means we are in integer part)
        char lastOp = '+';               // Previous operator

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // 1. Parse Number (Integers and Decimals)
            if (Character.isDigit(c)) {
                if (decimalDivisor == 0) {
                    num = num * 10 + (c - '0'); // Integer part logic: Shift left
                } else {
                    num += (c - '0') / decimalDivisor; // Fractional logic: Add value at specific decimal place
                    decimalDivisor *= 10; // Move to next decimal place (10 -> 100 -> 1000)
                }
            } else if (c == '.') {
                decimalDivisor = 10; // Start decimal mode
            }

            // 2. Process Operator (if char is symbol OR last char of string)
            // We verify c is NOT a digit AND NOT a decimal point
            if ((!Character.isDigit(c) && c != '.' && c != ' ') || i == s.length() - 1) {
                switch (lastOp) { // Java 21 Switch
                    case '+' -> stack.push(num);
                    case '-' -> stack.push(-num);
                    case '*' -> stack.push(stack.pop() * num);
                    case '/' -> stack.push(stack.pop() / num); // Floating point division happens here
                }
                lastOp = c;          // Update operator
                num = 0;             // Reset number
                decimalDivisor = 0;  // Reset decimal mode to "Integer"
            }
        }

        // 3. Sum up the stack
        return stack.stream().mapToDouble(Double::doubleValue).sum();
    }

    // --- Test Method ---
    public static void main(String[] args) {
        System.out.println("--- Double Evaluator Tests ---");

        var tests = new LinkedHashMap<String, Double>();

        // Basic Decimal Cases
        tests.put("1.5 + 2.5", 4.0);            // Simple decimal addition
        tests.put("3.5 * 2", 7.0);              // Decimal multiplication
        tests.put("1 / 2", 0.5);                // Float division (Crucial change from int version)
        tests.put("4 * 3 + 1 / 2 - 5", 7.5);    // 12 + 0.5 - 5 = 7.5
        tests.put("10.5 + 2.5 * 2", 15.5);      // Precedence: 10.5 + 5.0

        // Large Data Test: Sum of 0.5 repeated 100,000 times -> Should be 50,000.0
        String largeInput = IntStream.range(0, 100000)
                .mapToObj(i -> "0.5")
                .collect(Collectors.joining("+"));
        tests.put(largeInput, 50000.0);

        tests.forEach((input, expected) -> {
            double result = evaluate(input);
            // Use a small epsilon for double comparison logic
            String status = Math.abs(result - expected) < 0.0001 ? "PASS" : "FAIL";

            String shortInput = input.length() > 20 ? "Large Data..." : input;
            System.out.printf("Test: %-15s | Exp: %-8.1f | Got: %-8.1f | %s%n",
                    shortInput, expected, result, status);
        });
    }
}