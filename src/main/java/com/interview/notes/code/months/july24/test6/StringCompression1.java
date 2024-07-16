package com.interview.notes.code.months.july24.test6;

public class StringCompression1 {

    public static String compressString(String str) {
        // Check for edge cases
        if (str == null || str.isEmpty()) {
            return str; // Return original string for null or empty input
        }

        StringBuilder sb = new StringBuilder();
        int count = 0;
        int n = str.length();

        for (int i = 0; i < n; i++) {
            count++;

            if (i + 1 >= n || str.charAt(i) != str.charAt(i + 1)) {
                sb.append(str.charAt(i));
                sb.append(count);
                count = 0;
            }
        }

        return sb.length() < str.length() ? sb.toString() : str;
    }

    public static void main(String[] args) {
        String str1 = "aabcccccaaa";
        String str2 = "aabbcc";
        String str3 = "aaaa";

        System.out.println(str1 + " : " + compressString(str1)); // Output: a2b1c5a3
        System.out.println(str2 + " : " + compressString(str2)); // Output: aabbcc
        System.out.println(str3 + " : " + compressString(str3)); // Output: a4
    }
}
