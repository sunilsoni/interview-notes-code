package com.interview.notes.code.year.y2026.march.common.tes1;

import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class StringExpander { // This is the main container class for our program logic and testing execution.

    public static String expand(String text) { // This method takes the compressed input text and outputs the expanded string.
        return Pattern.compile("([a-zA-Z])(\\d+)") // We compile a regex: group 1 '([a-zA-Z])' finds a letter, group 2 '(\\d+)' finds subsequent digits.
            .matcher(text) // We apply this compiled pattern to our specific input text to search for all valid pairs.
            .results() // We convert the found matches into a modern Java Stream of MatchResult objects for functional processing.
            .map(m -> m.group(1).repeat(Integer.parseInt(m.group(2)))) // For each match, we isolate the letter (group 1) and duplicate it based on the number (group 2).
            .collect(Collectors.joining()); // We combine all the dynamically generated string fragments together into one final string output.
    }

    public static void main(String[] args) { // This is our simple main method used to execute our custom testing framework without JUnit.
        runTest("w3r4y6ab2e0D1", "wwwrrrryyyyyybbD", "Base Case"); // Tests the primary problem statement case provided in the requirements.
        runTest("a10", "aaaaaaaaaa", "Double Digit Case"); // Tests if multiple sequential digits are parsed correctly as '10' instead of just '1'.
        runTest("z0X1c2", "Xcc", "Zero and Mixed Case"); // Tests zero repetition (should yield nothing) and ensures uppercase letters work.
        runTest("A100000", "A".repeat(100000), "Large Data Case"); // Tests large data input capability to ensure memory and speed handle bulk generation seamlessly.
    }

    private static void runTest(String input, String expected, String testName) { // Helper method to execute a test case and print a clear PASS/FAIL result.
        var result = expand(input); // We execute the logic and store the result, using Java 'var' to minimize boilerplate code.
        var status = result.equals(expected) ? "PASS" : "FAIL"; // We compare the actual output against the expected output to evaluate success.
        System.out.println(status + " | " + testName + " -> Input: " + input); // We print the final status, test name, and input to the console for review.
    }
}