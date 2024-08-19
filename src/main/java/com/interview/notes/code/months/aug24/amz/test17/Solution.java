package com.interview.notes.code.months.aug24.amz.test17;

public class Solution {
    public static int atoi(String str) {
        if (str == null || str.length() == 0) return 0;

        int index = 0, sign = 1, total = 0;

        // Remove leading whitespace
        while (index < str.length() && str.charAt(index) == ' ')
            index++;

        // Handle sign
        if (index < str.length() && (str.charAt(index) == '+' || str.charAt(index) == '-')) {
            sign = str.charAt(index) == '+' ? 1 : -1;
            index++;
        }

        // Process digits
        while (index < str.length()) {
            int digit = str.charAt(index) - '0';
            if (digit < 0 || digit > 9) break;

            // Check for overflow
            if (total > Integer.MAX_VALUE / 10 ||
                    (total == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            total = total * 10 + digit;
            index++;
        }

        return total * sign;
    }

    public static boolean doTestsPass() {
        boolean result = true;
        result &= atoi("42") == 42;
        result &= atoi("-42") == -42;
        result &= atoi("   42") == 42;
        result &= atoi("4193 with words") == 4193;
        result &= atoi("words and 987") == 0;
        result &= atoi("-91283472332") == Integer.MIN_VALUE;
        result &= atoi("91283472332") == Integer.MAX_VALUE;
        result &= atoi("") == 0;
        result &= atoi("+1") == 1;
        return result;
    }

    public static void main(String[] args) {
        if (doTestsPass()) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }
    }
}
