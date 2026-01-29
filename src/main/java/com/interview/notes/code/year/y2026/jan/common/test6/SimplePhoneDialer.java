package com.interview.notes.code.year.y2026.jan.common.test6;

import java.util.ArrayList;
import java.util.List;

public class SimplePhoneDialer {

    public static void main(String[] args) {
        // Test 1: Simple case "2"
        test("2", List.of("A", "B", "C"));

        // Test 2: The problem example "23"
        test("23", List.of("AD", "AE", "AF", "BD", "BE", "BF", "CD", "CE", "CF"));

        // Test 3: Edge case (Empty)
        test("", List.of());

        // Test 4: Large input "2345" (3x3x3x3 = 81 combinations)
        // Just checking size ensures it works without printing huge lists
        testLarge("2345", 81);
    }

    // Main Logic: Simple Iterative Approach
    static List<String> solve(String digits) {
        // 1. If input is empty, return empty list immediately
        if (digits.isEmpty()) return new ArrayList<>();

        // 2. Keypad mapping (Index 2="ABC", 3="DEF" etc.)
        String[] map = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

        // 3. Start with a list containing one empty string ""
        // This is our base to build upon.
        List<String> result = new ArrayList<>();
        result.add("");

        // 4. Loop through every number in the input string (e.g., '2', then '3')
        for (char digit : digits.toCharArray()) {

            // 5. Create a temp list for the NEW combinations we are about to make
            List<String> temp = new ArrayList<>();

            // 6. Get the letters for the current number (e.g., '2' -> "ABC")
            String letters = map[digit - '0'];

            // 7. Loop through what we already have (e.g., "A", "B", "C")
            for (String currentStr : result) {
                // 8. Loop through the new letters (e.g., 'D', 'E', 'F')
                for (char letter : letters.toCharArray()) {
                    // 9. Combine and add to temp (e.g., "A"+"D" -> "AD")
                    temp.add(currentStr + letter);
                }
            }

            // 10. Update our main result to be the new list we just built
            result = temp;
        }

        // 11. Return the final list of combinations
        return result;
    }

    // Simple Test Method
    static void test(String input, List<String> expected) {
        List<String> actual = solve(input); // Run the logic
        boolean pass = actual.equals(expected); // Check if lists match
        // Print result: Use ternary operator (?) for simple PASS/FAIL text
        System.out.println("Input: " + input + " | " + (pass ? "PASS" : "FAIL") + " | Output: " + actual);
    }

    // Test Method for larger data (checks size only)
    static void testLarge(String input, int expectedSize) {
        List<String> actual = solve(input);
        boolean pass = actual.size() == expectedSize;
        System.out.println("Input: " + input + " | " + (pass ? "PASS" : "FAIL") + " | Size: " + actual.size());
    }
}