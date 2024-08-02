package com.interview.notes.code.months.july24.test17;

class Solution1 {
    public String solution(String S) {
        int[] count = new int[10];
        for (char c : S.toCharArray()) {
            count[c - '0']++;
        }

        StringBuilder left = new StringBuilder();
        char middle = ' ';
        boolean hasMiddle = false;

        for (int i = 9; i >= 0; i--) {
            while (count[i] > 1) {
                left.append((char) (i + '0'));
                count[i] -= 2;
            }

            if (!hasMiddle && count[i] > 0) {
                middle = (char) (i + '0');
                hasMiddle = true;
                count[i]--;
            }
        }

        if (left.length() == 0 && !hasMiddle) {
            // If no digits were used, return "0"
            return "0";
        }

        if (left.length() == 0 && hasMiddle) {
            // If only one digit is used, return that digit
            return String.valueOf(middle);
        }

        StringBuilder result = new StringBuilder(left);
        if (hasMiddle) {
            result.append(middle);
        }
        result.append(left.reverse());

        // Remove leading zeros if present
        while (result.length() > 1 && result.charAt(0) == '0') {
            result.deleteCharAt(0);
            result.deleteCharAt(result.length() - 1);
        }

        return result.toString();
    }
}
