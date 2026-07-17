package com.interview.notes.code.year.y2026.july.common.test5;

public class DigitSum {
    // Recursive method to calculate sum of digits
    public static int sum(int n) {
        // Base case: if number is 0, recursion stops and returns 0
        if (n == 0) return 0;
        // Logic: Extract last digit (n % 10) and add it to the result of sum called with remaining digits (n / 10)
        return (n % 10) + sum(n / 10);
    }

    public static void main(String[] args) {
        // Define test cases: {Input, Expected Output}
        var tests = new int[][]{{489, 21}, {0, 0}, {5, 5}, {12345, 15}, {999, 27}};

        // Iterate through all cases to verify logic
        for (var test : tests) {
            // Execute method with current test input
            var result = sum(test[0]);
            // Validate result against expectation and print status
            System.out.println("Input: " + test[0] + " | Expected: " + test[1] + " | Result: " + result + " | " + (result == test[1] ? "PASS" : "FAIL"));
        }
    }
}