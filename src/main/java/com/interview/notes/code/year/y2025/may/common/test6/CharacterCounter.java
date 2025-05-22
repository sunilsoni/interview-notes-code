package com.interview.notes.code.year.y2025.may.common.test6;

public class CharacterCounter {
    public static void main(String[] args) {
        // Test cases
        runTest(" java is a programing language ", "Basic string");
        runTest("", "Empty string");
        runTest("aaa", "Repeated characters");
        runTest("123!@#", "Special characters");
        runTest(null, "Null string");
        
        // Large data test
        StringBuilder large = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            large.append("java");
        }
        runTest(large.toString(), "Large string");
    }

    public static void countChars(String input) {
        if (input == null || input.isEmpty()) {
            System.out.println("Input is null or empty");
            return;
        }

        // Create array to store character counts (ASCII values)
        int[] counts = new int[128];
        
        // Count characters
        for (char c : input.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                counts[c]++;
            }
        }
        
        // Print results for characters that appear at least once
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                System.out.println((char)i + ": " + counts[i]);
            }
        }
    }

    private static void runTest(String input, String testName) {
        System.out.println("\nTest: " + testName);
        System.out.println("Input: " + (input == null ? "null" : "\"" + input + "\""));
        try {
            countChars(input);
            System.out.println("Status: PASS");
        } catch (Exception e) {
            System.out.println("Status: FAIL");
            System.out.println("Error: " + e.getMessage());
        }
    }
}
