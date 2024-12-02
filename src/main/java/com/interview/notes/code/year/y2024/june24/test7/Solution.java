package com.interview.notes.code.year.y2024.june24.test7;

public class Solution {

    public static String mergePalindromes(String s1, String s2) {
        // Combine the two strings
        String combined = s1 + s2;
        return longestPalindrome(combined);
    }

    private static String longestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middle = 0;
        boolean middleSet = false;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 == 1) {
                if (!middleSet || (char) (i + 'a') < middle) {
                    middle = (char) (i + 'a');
                    middleSet = true;
                }
            }
            for (int j = 0; j < freq[i] / 2; j++) {
                half.append((char) (i + 'a'));
            }
        }

        String halfStr = half.toString();
        StringBuilder result = new StringBuilder(halfStr);
        if (middleSet) {
            result.append(middle);
        }
        result.append(half.reverse().toString());

        return result.toString();
    }

    public static void main(String[] args) {
        // Test case 1
        String s1 = "adaab";
        String s2 = "cac";
        System.out.println("Test case 1:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println("Expected: aaccaa");
        System.out.println();

        // Test case 2
        s1 = "aaaabbbccc";
        s2 = "ddeeccc";
        System.out.println("Test case 2:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println("Expected: aabcccdeedcccbaa");
        System.out.println();

        // Test case 3
        s1 = "awwzaigvxuikdqlvshspsvyckttwdzqmarpxglwmpob";
        s2 = "dtisfxyobndu";
        System.out.println("Test case 3:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println("Expected: abddgiklmpqstvwwxzzxwwvtsqpmlkigddba");
        System.out.println();

        // Test case 4
        s1 = "mgbgikhvjyiigxhsrgekjmjkrs";
        s2 = "cikmqfxpcybzyhbdrhudjmsoaqdurgjsnjlqogrkcmdtxbyazfxv";
        System.out.println("Test case 4:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println("Expected: abbbccddfggghhiiijjjkklmmmnnoppqrrrsstuvxyyzzyyxvutssrrrapponnmmmlkkjjjiiihhgggfddccbbba");
        System.out.println();

        // Test case 5
        s1 = "aaaabbbccc";
        s2 = "ddeeccc";
        System.out.println("Test case 5:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println("Expected: aabcccdeedcccbaa");
        System.out.println();

        // Test case 6
        s1 = "awwzaigvxuikdqlvshspsvyckttwdzqmarpxglwmpob";
        s2 = "disfxyobndu";
        System.out.println("Test case 6:");
        System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
        System.out.println("Output: " + mergePalindromes(s1, s2));
        System.out.println("Expected: abddgiklmpqstvwwxzzxwwvtsqpmlkigddba");
        System.out.println();
    }
}
