package com.interview.notes.code.year.y2025.march.amazon.test11;

import java.util.*;

public class LexicoMinimalString {

    public static String getMinimumString(String s_id) {
        char[] digits = s_id.toCharArray();
        int n = digits.length;

        for (int i = 0; i < n; i++) {
            char minDigit = digits[i];
            int minIndex = i;

            // Find smaller digit to the right (d+1)%10 allows digit rotation
            for (int j = n - 1; j > i; j--) {
                char rotatedDigit = (char)(((digits[j] - '0' + 1) % 10) + '0');
                if (rotatedDigit < minDigit) {
                    minDigit = rotatedDigit;
                    minIndex = j;
                }
            }

            if (minIndex != i) {
                // Perform operation: remove digit at minIndex and insert rotatedDigit at position i
                char rotatedDigit = (char)(((digits[minIndex] - '0' + 1) % 10) + '0');
                StringBuilder sb = new StringBuilder();
                for (int k = 0; k < n; k++) {
                    if (k == i) sb.append(rotatedDigit);
                    if (k != minIndex) sb.append(digits[k]);
                }
                digits = sb.toString().toCharArray();
            }
        }

        return new String(digits);
    }

    public static void main(String[] args) {
        test("04829", "02599");
        test("34892", "24599");
        test("26547", "24677");
        test("11111", "11111");
        test("01234", "01234");

        // Large test case
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 200000; i++) {
            largeInput.append((char) ('9' - (i % 10)));
        }
        String largeResult = getMinimumString(largeInput.toString());
        System.out.println("Large test length: " + largeResult.length());
    }

    static void test(String input, String expected) {
        String result = getMinimumString(input);
        System.out.println("Input: " + input
                + " | Expected: " + expected
                + " | Result: " + result
                + " | " + (result.equals(expected) ? "PASS" : "FAIL"));
    }
}
