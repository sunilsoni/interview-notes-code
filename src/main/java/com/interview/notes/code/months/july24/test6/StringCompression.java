package com.interview.notes.code.months.july24.test6;

public class StringCompression {

    public static String compressString(String str) {
        // Check for edge cases
        if (str == null || str.isEmpty()) {
            return str; // Return original string for null or empty input
        }

        int compressedLength = calculateCompressedLength(str);

        if (compressedLength >= str.length()) {
            return str; // Return original string if compressed string is not shorter
        }

        StringBuilder compressed = new StringBuilder(compressedLength);
        int countConsecutive = 0;

        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressed.append(str.charAt(i));
                compressed.append(countConsecutive);
                countConsecutive = 0;
            }
        }

        return compressed.toString();
    }

    private static int calculateCompressedLength(String str) {
        int compressedLength = 0;
        int countConsecutive = 0;

        for (int i = 0; i < str.length(); i++) {
            countConsecutive++;

            if (i + 1 >= str.length() || str.charAt(i) != str.charAt(i + 1)) {
                compressedLength += 1 + String.valueOf(countConsecutive).length();
                countConsecutive = 0;
            }
        }

        return compressedLength;
    }

    public static void main(String[] args) {
        String str1 = "aabcccccaaa";
        String str2 = "aabbcc";
        String str3 = "aaaa";
        String str4 = "";
        String str5 = null;
        String str6 = "abcdefg";
        String str7 = "a";

        System.out.println(compressString(str1)); // Output: a2b1c5a3
        System.out.println(compressString(str2)); // Output: aabbcc
        System.out.println(compressString(str3)); // Output: a4
        System.out.println(compressString(str4)); // Output: (empty string)
        System.out.println(compressString(str5)); // Output: null
        System.out.println(compressString(str6)); // Output: abcdefg
        System.out.println(compressString(str7)); // Output: a
    }
}
