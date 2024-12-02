package com.interview.notes.code.year.y2023.Sep23.test1;

public class ReverseStringWithSpecialChars1 {
    public static void main(String[] args) {
        System.out.println(reverseString("ab-cd"));       // "dc-ba"
        System.out.println(reverseString("@amit_dixit")); // "@tixi_dtima"
    }

    public static String reverseString(String s) {
        char[] chars = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;

        while (i < j) {
            if (!Character.isLetter(chars[i])) {
                i++;
            } else if (!Character.isLetter(chars[j])) {
                j--;
            } else {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }

        return new String(chars);
    }
}
