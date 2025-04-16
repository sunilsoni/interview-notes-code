package com.interview.notes.code.year.y2025.april.common.test7;

import java.util.Arrays;

public class AnagramChecker {
    public static boolean areAnagrams(String str1, String str2) {
        // Handle null cases
        if (str1 == null || str2 == null) return false;

        // Convert to lowercase and remove spaces
        str1 = str1.toLowerCase().replaceAll("\\s", "");
        str2 = str2.toLowerCase().replaceAll("\\s", "");

        // Quick length check
        if (str1.length() != str2.length()) return false;

        // Convert to char arrays and sort
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);

        // Compare sorted arrays using two pointers
        int left = 0, right = 0;
        while (left < chars1.length && right < chars2.length) {
            if (chars1[left] != chars2[right]) {
                return false;
            }
            left++;
            right++;
        }
        return true;
    }

    public static void main(String[] args) {
        // Test cases with results
        testAnagram("listen", "silent", true);
        testAnagram("hello", "world", false);
        testAnagram("Debit Card", "Bad Credit", true);
        testAnagram("", "", true);
        testAnagram("a", "a", true);
        testAnagram(null, "test", false);
        testAnagram("test", null, false);

        // Large data test
        String largeStr1 = "a".repeat(100000);
        String largeStr2 = "a".repeat(100000);
        testAnagram(largeStr1, largeStr2, true);
    }

    private static void testAnagram(String str1, String str2, boolean expectedResult) {
        boolean result = areAnagrams(str1, str2);
        System.out.printf("Test: '%s' and '%s' -> %s%n",
                str1, str2, result == expectedResult ? "PASS" : "FAIL");
    }
}
