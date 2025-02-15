package com.interview.notes.code.year.y2025.feb25.common.test5;

public class StringReverse {
    public static String reverseString(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        char[] charArray = str.toCharArray();
        int left = 0;
        int right = charArray.length - 1;

        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }

        return new String(charArray);
    }

    public static void main(String[] args) {
        String original = "Hello World";
        String reversed = reverseString(original);
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reversed);
    }
}
