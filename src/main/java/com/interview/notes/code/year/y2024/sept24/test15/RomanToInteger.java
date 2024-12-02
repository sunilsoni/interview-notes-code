package com.interview.notes.code.year.y2024.sept24.test15;

import java.util.HashMap;

public class RomanToInteger {

    // Method to convert Roman numeral to Integer
    public static int romanToInt(String s) {
        // Map to store Roman numeral values
        HashMap<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        // Variable to store the result
        int result = 0;

        // Iterate through the string
        for (int i = 0; i < s.length(); i++) {
            int currentVal = romanMap.get(s.charAt(i));

            // Check if the next character exists and is larger than the current value
            if (i + 1 < s.length() && romanMap.get(s.charAt(i + 1)) > currentVal) {
                // Subtraction case
                result -= currentVal;
            } else {
                // Regular addition case
                result += currentVal;
            }
        }

        return result;
    }

    // Main method to run test cases
    public static void main(String[] args) {
        // Test cases
        String[] testCases = {"XIX", "IV", "XL", "MCMXCIV", "III", "LVIII", "I", "MMMCMXCIX"};
        int[] expectedResults = {19, 4, 40, 1994, 3, 58, 1, 3999};

        // Run each test case
        for (int i = 0; i < testCases.length; i++) {
            int result = romanToInt(testCases[i]);
            if (result == expectedResults[i]) {
                System.out.println("Test case " + testCases[i] + " passed: " + result);
            } else {
                System.out.println("Test case " + testCases[i] + " failed: " + result + " (Expected: " + expectedResults[i] + ")");
            }
        }
    }
}
