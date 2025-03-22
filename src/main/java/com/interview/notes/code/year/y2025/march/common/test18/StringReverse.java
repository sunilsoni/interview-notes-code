package com.interview.notes.code.year.y2025.march.common.test18;

public class StringReverse {
    public static String reverseString(String str) {
        char[] charArray = str.toCharArray();
        reverseStringHelper(charArray, 0, str.length() - 1);
        return new String(charArray);
    }

    private static void reverseStringHelper(char[] str, int start, int end) {
        // Base case
        if (start >= end) {
            return;
        }

        // Swap characters
        char temp = str[start];
        str[start] = str[end];
        str[end] = temp;

        // Recursive call with updated indices
        reverseStringHelper(str, start + 1, end - 1);
    }

    public static void main(String[] args) {
        String str = "abcd";
        System.out.println("Original string: " + str);
        System.out.println("Reversed string: " + reverseString(str));
    }
}
