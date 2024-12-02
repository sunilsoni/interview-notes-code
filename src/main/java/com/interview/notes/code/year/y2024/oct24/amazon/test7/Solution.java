package com.interview.notes.code.year.y2024.oct24.amazon.test7;

public class Solution {

    public static String solution(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            result.insert(0, sum % 10);
            carry = sum / 10;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        String[][] testCases = {
                {"99", "99"},
                {"11", "9"},
                {"123", "456"},
                {"999", "1"},
                {"1", "999"},
                {"0", "0"},
                {"1000", "1"},
                {"999999999", "1"}
        };

        for (String[] test : testCases) {
            String result = solution(test[0], test[1]);
            System.out.println("Input: a = " + test[0] + ", b = " + test[1]);
            System.out.println("Output: " + result);
            System.out.println();
        }

        // Large data input test
        StringBuilder largeA = new StringBuilder();
        StringBuilder largeB = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeA.append("9");
            largeB.append("1");
        }
        String resultLarge = solution(largeA.toString(), largeB.toString());
        System.out.println("Large Input Test:");
        System.out.println("Input: a = " + largeA.substring(0, 10) + "... (1000 digits)");
        System.out.println("       b = " + largeB.substring(0, 10) + "... (1000 digits)");
        System.out.println("Output: " + resultLarge.substring(0, 10) + "... (" + resultLarge.length() + " digits)");
    }
}
