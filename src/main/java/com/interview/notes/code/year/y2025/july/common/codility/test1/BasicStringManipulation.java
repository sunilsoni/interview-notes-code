// File: src/main/java/com/interview/notes/code/year/y2025/july/common/codility/test1/BasicStringManipulation.java
package com.interview.notes.code.year.y2025.july.common.codility.test1;

public class BasicStringManipulation {

    /**
     * Task 1: Reverse a String in‐place (no built-in reverse()).
     */
    public static String reverseString(String str) {
        if (str == null) return null;
        char[] arr = str.toCharArray();
        int i = 0, j = arr.length - 1;
        while (i < j) {
            char tmp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = tmp;
        }
        return new String(arr);
    }

    /**
     * Task 2: Reverse the order of words.
     * - Trims leading/trailing spaces.
     * - Collapses multiple spaces into one.
     */
    public static String reverseWordsInString(String str) {
        if (str == null) return null;
        String[] parts = str.trim().split("\\s+");
        StringBuilder sb = new StringBuilder(parts.length * 5);
        for (int k = parts.length - 1; k >= 0; k--) {
            sb.append(parts[k]);
            if (k > 0) sb.append(' ');
        }
        return sb.toString();
    }
}