package com.interview.notes.code.months.aug24.test8;

import java.util.Scanner;

public class Solution {

    public static String phoneNumber(String S) {
        StringBuilder digits = new StringBuilder();

        // Iterate through the string from right to left
        for (int i = S.length() - 1; i >= 0; i--) {
            char c = S.charAt(i);
            if (Character.isDigit(c)) {
                digits.insert(0, c);
                if (digits.length() == 10) {
                    break;
                }
            }
        }

        // If less than 10 digits were found, pad with leading zeros
        while (digits.length() < 10) {
            digits.insert(0, '0');
        }

        return digits.toString();
    }


    public static String ReplaceVowels(String S) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < S.length(); i++) {
            char ch = S.charAt(i);
            if (ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U' ||
                    ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                out.append('3');
            } else {
                out.append(ch);
            }
        }
        return out.toString();
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String S = scan.nextLine();
        String result = ReplaceVowels(S);
        System.out.println(result);
        scan.close();
    }
}
