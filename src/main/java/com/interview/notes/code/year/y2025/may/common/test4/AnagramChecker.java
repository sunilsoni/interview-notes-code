package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.Arrays;

public class AnagramChecker {
    public static boolean areAnagrams(String str1, String str2) {
        // Remove spaces and convert to lowercase
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // If lengths are different, they can't be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Convert strings to char arrays and sort them
        char[] charArray1 = str1.toCharArray();
        char[] charArray2 = str2.toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        // Compare sorted arrays
        return Arrays.equals(charArray1, charArray2);
    }

    // Alternative method using character frequency count
    public static boolean areAnagramsUsingCount(String str1, String str2) {
        // Remove spaces and convert to lowercase
        str1 = str1.replaceAll("\\s", "").toLowerCase();
        str2 = str2.replaceAll("\\s", "").toLowerCase();

        // If lengths are different, they can't be anagrams
        if (str1.length() != str2.length()) {
            return false;
        }

        // Create array to store character frequencies
        int[] charCount = new int[26];

        // Count frequencies for first string
        for (char c : str1.toCharArray()) {
            charCount[c - 'a']++;
        }

        // Decrease frequencies for second string
        for (char c : str2.toCharArray()) {
            charCount[c - 'a']--;
        }

        // Check if all frequencies are zero
        for (int count : charCount) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // Test cases
        String s1 = "listen";
        String s2 = "silent";
        System.out.println("Are '" + s1 + "' and '" + s2 + "' anagrams? " +
                areAnagrams(s1, s2));

        s1 = "hello";
        s2 = "world";
        System.out.println("Are '" + s1 + "' and '" + s2 + "' anagrams? " +
                areAnagrams(s1, s2));

        s1 = "Debit Card";
        s2 = "Bad Credit";
        System.out.println("Are '" + s1 + "' and '" + s2 + "' anagrams? " +
                areAnagrams(s1, s2));
    }
}
