package com.interview.notes.code.months.year2023.dec23.test3;

public class VowelReverser {
    public static void main(String[] args) {
        String input = "hell";
        String output = reverseVowels(input);
        System.out.println("Output: " + output);
    }

    private static String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (!isVowel(chars[i])) {
                i++;
                continue;
            }

            if (!isVowel(chars[j])) {
                j--;
                continue;
            }

            // Swap vowels
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;

            // Move pointers
            i++;
            j--;
        }
        return new String(chars);
    }

    private static boolean isVowel(char c) {
        return "AEIOUaeiou".indexOf(c) != -1;
    }
}
