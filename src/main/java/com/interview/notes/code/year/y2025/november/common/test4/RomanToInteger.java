package com.interview.notes.code.year.y2025.november.common.test4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RomanToInteger {

    // Converts a Roman numeral string to an integer
    public static int romanToInt(String s) {
        // Map each Roman symbol to its integer value
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        // Convert string to char array for easy traversal
        char[] chars = s.toCharArray();
        int total = 0;

        // Loop through each symbol
        for (int i = 0; i < chars.length; i++) {
            int current = map.get(chars[i]); // get value of current symbol

            // If a smaller value precedes a larger one, subtract it
            if (i + 1 < chars.length && current < map.get(chars[i + 1])) {
                total -= current;
            } else {
                // otherwise add normally
                total += current;
            }
        }
        return total; // final integer value
    }

    // Main method for manual testing (no JUnit)
    public static void main(String[] args) {

        // Define test inputs and expected outputs
        List<String> inputs = Arrays.asList("III", "LVIII", "MCMXCIV", "XCIX");
        List<Integer> expected = Arrays.asList(3, 58, 1994, 99);

        // Use Java 8 Stream to process and print results neatly
        for (int i = 0; i < inputs.size(); i++) {
            String input = inputs.get(i);
            int exp = expected.get(i);
            int result = romanToInt(input);

            System.out.println("Input: " + input);
            System.out.println("Expected: " + exp);
            System.out.println("Output: " + result);

            // Compare result with expected and print Pass/Fail
            System.out.println("Test " + (i + 1) + " => " + (result == exp ? "PASS" : "FAIL"));
            System.out.println("---------------------------");
        }

        // Additional large input test (performance check)
        String largeInput = "M".repeat(1000); // 1000 Ms = 1,000,000
        System.out.println("Large Data Test Output: " + romanToInt(largeInput));
    }
}
