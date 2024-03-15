package com.interview.notes.code.months.march24.test9;

public class Outcome1 {

    public static String solve(String a, String b) {
        StringBuilder result = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0 || carry == 1) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            result.insert(0, (char) (sum % 2 + '0'));
            carry = sum / 2;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Example #1
        System.out.println(solve("11", "1")); // Output: 100

        // Example #2
        System.out.println(solve("1010", "1011")); // Output: 10101
    }
}
