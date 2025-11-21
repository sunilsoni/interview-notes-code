package com.interview.notes.code.year.y2025.november.linkedin.test1;

public class ParenthesisValidator {

    /**
     * Main method to run manual tests without JUnit.
     */
    public static void main(String[] args) {
        ParenthesisValidator solver = new ParenthesisValidator();

        // --- Standard Test Cases from prompt ---
        runTest(solver, "()()()()", true, "Standard balanced");
        runTest(solver, "((45+)*a3)", true, "Balanced with noise characters");
        runTest(solver, "(((()())", false, "Missing closing bracket");
        runTest(solver, "))((", false, "Closing before opening");

        // --- Edge Cases ---
        runTest(solver, "", true, "Empty string (mathematically balanced)");
        runTest(solver, null, false, "Null input");
        runTest(solver, "(", false, "Single open");
        runTest(solver, ")", false, "Single close");
        runTest(solver, "a+b", true, "No brackets (valid logic)");

        // --- Large Data Input Case ---
        // Create a massive string with 1,000,000 pairs of brackets
        System.out.println("\nRunning Large Data Test (Wait...):");
        StringBuilder largeBuilder = new StringBuilder();

        // Append 500,000 open brackets
        for (int i = 0; i < 500000; i++) largeBuilder.append("(");
        // Append 500,000 close brackets
        for (int i = 0; i < 500000; i++) largeBuilder.append(")");

        String largeInput = largeBuilder.toString();
        runTest(solver, largeInput, true, "Large Dataset (1M chars)");

        // Large Fail Case
        runTest(solver, largeInput + ")", false, "Large Dataset Fail Case");
    }

    /**
     * Helper method to print PASS/FAIL results clearly.
     */
    private static void runTest(ParenthesisValidator solver, String input, boolean expected, String testName) {
        // Capture the start time to measure performance (useful for large data)
        long startTime = System.currentTimeMillis();

        // Run the actual solution logic
        boolean result = solver.matched(input);

        // Capture end time
        long endTime = System.currentTimeMillis();

        // Determine if the test passed (Result matches Expected)
        String status = (result == expected) ? "PASS" : "FAIL";

        // Format the input for display (truncate if too long to keep output clean)
        String displayInput = (input == null) ? "null" : input;
        if (displayInput.length() > 20) displayInput = displayInput.substring(0, 20) + "...";

        // Output the result in a structured format
        System.out.printf("[%s] Test: %-30s | Input: %-15s | Expected: %-5s | Actual: %-5s | Time: %d ms%n",
                status, testName, displayInput, expected, result, (endTime - startTime));
    }

    /**
     * Determines if parenthesis are properly matched using Java 8 Streams.
     */
    public boolean matched(String s) {
        // Check if the input string is null to prevent errors
        if (s == null) {
            // Return false immediately because a null string cannot be valid
            return false;
        }

        // We use an integer array to hold the 'balance' state because variables used inside
        // Java 8 lambdas (forEach) must be 'effectively final'.
        // state[0] represents the current balance (open - close).
        // state[1] represents a 'failure flag' (1 if failed, 0 if passing).
        int[] state = {0, 0};

        // Convert the String into a Stream of characters (IntStream)
        s.chars()
                // Filter: Keep only '(' or ')' characters. This ignores numbers, letters, and other symbols.
                .filter(c -> c == '(' || c == ')')
                // Iterate through the filtered stream of brackets
                .forEach(c -> {
                    // Optimization: If we already marked it as failed (state[1] == 1), do nothing.
                    if (state[1] == 1) return;

                    // Logic: If character is '(', we increment the balance counter
                    if (c == '(') {
                        state[0]++;
                    }
                    // Logic: If character is ')', we decrement the balance counter
                    else {
                        state[0]--;
                    }

                    // Critical Rule: If balance drops below zero, it means we have a ')' before a '('.
                    // Example: "))((" -> starts with negative balance immediately.
                    if (state[0] < 0) {
                        state[1] = 1; // Mark as failed
                    }
                });

        // Final Validation:
        // 1. state[0] == 0: Means every open bracket was closed (count is zero).
        // 2. state[1] == 0: Means we never dropped below zero during the process.
        return state[0] == 0 && state[1] == 0;
    }
}