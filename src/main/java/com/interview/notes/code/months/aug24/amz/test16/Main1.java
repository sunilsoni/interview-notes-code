package com.interview.notes.code.months.aug24.amz.test16;

public class Main1 {
    // Main method to run test cases

    public static void main(String[] args) {
        // Test cases
        System.out.println(getSpecialString("abbd"));  // Expected: abca
        System.out.println(getSpecialString("abccde"));  // Expected: abcdab
        System.out.println(getSpecialString("zzab"));  // Expected: -1

        // Additional test cases
        System.out.println(getSpecialString("a"));  // Expected: b
        System.out.println(getSpecialString("z"));  // Expected: -1
        System.out.println(getSpecialString("az"));  // Expected: ba
        System.out.println(getSpecialString("zz"));  // Expected: -1
        System.out.println(getSpecialString("abcdefghijklmnopqrstuvwxyz"));  // Expected: -1
    }

    public static String getSpecialString(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();

        // Find the first character to increment
        int i = n - 1;
        while (i >= 1 && chars[i] <= chars[i - 1]) {
            i--;
        }

        // If all characters are 'z', return "-1"
        if (i == 0 && chars[0] == 'z') {
            return "-1";
        }

        // Increment the character and set subsequent characters to 'a'
        chars[i]++;
        for (int j = i + 1; j < n; j++) {
            chars[j] = 'a';
        }

        // Handle the case where incrementing causes adjacent duplicates
        while (i > 0 && chars[i] == chars[i - 1]) {
            chars[i - 1]++;
            i--;
        }

        return new String(chars);
    }
}