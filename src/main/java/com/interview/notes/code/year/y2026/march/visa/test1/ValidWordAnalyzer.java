package com.interview.notes.code.year.y2026.march.visa.test1;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class ValidWordAnalyzer {

    public static int countValidWords(String s) {
        if (s == null || s.trim().isEmpty()) {
            return 0;
        }
        
        var pattern = Pattern.compile("^(?=.*[aeiouAEIOU])(?=.*[b-df-hj-np-tv-zB-DF-HJ-NP-TV-Z])[a-zA-Z0-9]{3,}$");
        
        return (int) Arrays.stream(s.trim().split("\\s+"))
            .filter(word -> pattern.matcher(word).matches())
            .count();
    }

    public static void main(String[] args) {
        record TestCase(String input, int expectedOutput) {}

        var testCases = List.of(
            new TestCase("This is an example string 234", 3),
            new TestCase("This is Form16 submis$ion date", 3),
            new TestCase("A b c", 0),
            new TestCase("123", 0),
            new TestCase("AEI", 0),
            new TestCase("BCD", 0),
            new TestCase("", 0),
            new TestCase("    ", 0)
        );

        System.out.println("Running Standard Test Cases:");
        for (int i = 0; i < testCases.size(); i++) {
            var tc = testCases.get(i);
            int result = countValidWords(tc.input());
            boolean passed = result == tc.expectedOutput();
            System.out.printf("Test %d: %s | Expected: %d, Got: %d%n", 
                i + 1, passed ? "PASS" : "FAIL", tc.expectedOutput(), result);
        }

        System.out.println("\nRunning Large Data Test Case:");
        var largeInput = new StringBuilder();
        int expectedLargeCount = 50000;
        
        for (int i = 0; i < 50000; i++) {
            largeInput.append("Valid1 ");
        }
        for (int i = 0; i < 50000; i++) {
            largeInput.append("no ");
        }
        
        int largeResult = countValidWords(largeInput.toString());
        boolean largePassed = largeResult == expectedLargeCount;
        System.out.printf("Large Data Test: %s | Expected: %d, Got: %d%n", 
            largePassed ? "PASS" : "FAIL", expectedLargeCount, largeResult);
    }
}