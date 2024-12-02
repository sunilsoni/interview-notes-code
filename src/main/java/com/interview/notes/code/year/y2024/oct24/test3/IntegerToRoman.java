package com.interview.notes.code.year.y2024.oct24.test3;

import java.util.HashMap;
import java.util.Map;

public class IntegerToRoman {
    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public static String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < VALUES.length; i++) {
            while (num >= VALUES[i]) {
                num -= VALUES[i];
                result.append(SYMBOLS[i]);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {
        testIntToRoman();
    }

    public static void testIntToRoman() {
        Map<Integer, String> testCases = new HashMap<>();
        testCases.put(3, "III");
        testCases.put(4, "IV");
        testCases.put(9, "IX");
        testCases.put(58, "LVIII");
        testCases.put(1994, "MCMXCIV");
        testCases.put(3999, "MMMCMXCIX");
        testCases.put(2023, "MMXXIII");
        testCases.put(3888, "MMMDCCCLXXXVIII");

        boolean allPassed = true;
        for (Map.Entry<Integer, String> entry : testCases.entrySet()) {
            int input = entry.getKey();
            String expected = entry.getValue();
            String result = intToRoman(input);
            if (!result.equals(expected)) {
                System.out.println("FAIL: Input: " + input + ", Expected: " + expected + ", Got: " + result);
                allPassed = false;
            }
        }

        if (allPassed) {
            System.out.println("PASS: All test cases passed successfully.");
        }

        // Test for large input
        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= 1000000; i++) {
            intToRoman(i);
        }
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Time taken for 1 million conversions: " + duration + " ms");
        if (duration <= 3000) {
            System.out.println("PASS: Large input test passed (completed within 3 seconds).");
        } else {
            System.out.println("FAIL: Large input test failed (took more than 3 seconds).");
        }
    }
}
