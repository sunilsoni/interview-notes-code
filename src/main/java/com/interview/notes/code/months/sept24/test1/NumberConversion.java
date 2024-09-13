package com.interview.notes.code.months.sept24.test1;

public class NumberConversion {

    // Main method to run test cases
    public static void main(String[] args) {
        // Test cases
        testCase(7, "VII");
        testCase(9, "IX");
        testCase(1, "I");
        testCase(3999, "MMMCMXCIX");
        testCase(2023, "MMXXIII");
        testCase(444, "CDXLIV");
        testCase(3888, "MMMDCCCLXXXVIII");
    }

    // Method to run and verify test cases
    private static void testCase(int input, String expected) {
        String result = solve(input);
        System.out.println("Input: " + input);
        System.out.println("Output: " + result);
        System.out.println("Expected: " + expected);
        System.out.println("Test Case: " + (result.equals(expected) ? "Passed" : "Failed"));
        System.out.println();
    }

    // Solution method to convert decimal to Roman numeral
    public static String solve(int N) {
        if (N < 1 || N > 3999) {
            throw new IllegalArgumentException("Input must be between 1 and 3999");
        }

        // Define Roman numeral symbols and their values
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        // Iterate through the values and build the Roman numeral
        for (int i = 0; i < values.length; i++) {
            while (N >= values[i]) {
                result.append(symbols[i]);
                N -= values[i];
            }
        }

        return result.toString();
    }
}
