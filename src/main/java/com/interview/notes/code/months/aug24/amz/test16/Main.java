package com.interview.notes.code.months.aug24.amz.test16;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Test case 1: "abbd"
        String s1 = "abbd";
        System.out.println("Test Case 1:");
        System.out.println("Input string: " + s1);
        System.out.println("Expected output: abca");
        System.out.println("Actual output: " + getSpecialString(s1));

        // Test case 2: "zzab"
        String s2 = "zzab";
        System.out.println("\nTest Case 2:");
        System.out.println("Input string: " + s2);
        System.out.println("Expected output: -1");
        System.out.println("Actual output: " + getSpecialString(s2));

        // Test case 3: "abccde"
        String s3 = "abccde";
        System.out.println("\nTest Case 3:");
        System.out.println("Input string: " + s3);
        System.out.println("Expected output: abcdab");
        System.out.println("Actual output: " + getSpecialString(s3));

    }

    public static String getSpecialString(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            char c = 'a';

            // If the current character is not equal to the previous one, 
            // we can safely increase it.
            if ((sb.length() == 0) || (s.charAt(i) != sb.charAt(sb.length() - 1))) {
                while (c <= s.charAt(i)) {
                    c++;
                }

                // Add the increased character to the string builder
                sb.append(c);

                i++;
            } else {
                if ((sb.length() == 0) || (s.charAt(i - 1) != 'z')) {

                    char prevChar = s.charAt(i - 1);
                    while (prevChar <= 'a') {
                        c++;
                        prevChar = c;
                    }
                    sb.append(c);
                    i++;
                } else {
                    return "-1";
                }
            }
        }

        // If the length of string builder is equal to input string, then we have found a special string
        if (sb.length() == s.length()) {

            String result = sb.toString();
            int j = 0;
            while ((j < result.length() - 2) &&
                    (result.charAt(j + 1) != 'z') &&
                    (result.charAt(result.length() - 1) != result.charAt(j))) {
                char c = result.charAt(j);

                // Find the next character that is greater than current one
                int k;
                for (k = j; k < result.length(); k++) {
                    if (c < result.charAt(k)) break;
                }

//                // Replace all occurrences of 'c' with the found character
//                String temp = result.substring(0, j) +
//                              new StringBuilder(result.substring(j+1)).replaceFirst(String.valueOf(c), "") +
//                              c+
//                              result.substring(j+2);
//                if (temp.length() == s.length()) {
//                    return temp;
//                }
            }

        } else {

            // If the length of string builder is less than input string, 
            // then we need to fill it with 'a'
            while ((sb.length() < s.length())) {

                sb.append('a');
            }

        }

        return "-1";
    }
}