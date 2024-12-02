package com.interview.notes.code.year.y2024.june24.test7;

public class MergePalindromes3 {

    public static String mergePalindromes(String s1, String s2) {
        String palindrome1 = getLongestPalindrome(s1);
        String palindrome2 = getLongestPalindrome(s2);

        String combined = mergeTwoPalindromes(palindrome1, palindrome2);

        return combined;
    }

    private static String getLongestPalindrome(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                if (middle == 0 || (char) (i + 'a') < middle) {
                    middle = (char) (i + 'a');
                }
            }
            for (int j = 0; j < count[i] / 2; j++) {
                half.append((char) (i + 'a'));
            }
        }

        String halfStr = half.toString();
        String reverseHalfStr = half.reverse().toString();

        if (middle != 0) {
            return halfStr + middle + reverseHalfStr;
        } else {
            return halfStr + reverseHalfStr;
        }
    }

    private static String mergeTwoPalindromes(String p1, String p2) {
        String combined = p1 + p2;
        int[] count = new int[26];
        for (char c : combined.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middle = 0;

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                if (middle == 0 || (char) (i + 'a') < middle) {
                    middle = (char) (i + 'a');
                }
            }
            for (int j = 0; j < count[i] / 2; j++) {
                half.append((char) (i + 'a'));
            }
        }

        String halfStr = half.toString();
        String reverseHalfStr = half.reverse().toString();

        if (middle != 0) {
            return halfStr + middle + reverseHalfStr;
        } else {
            return halfStr + reverseHalfStr;
        }
    }

    public static void main(String[] args) {
        runTestCases();
    }

    private static void runTestCases() {
        String[][] testCases = {
                {"adaab", "cac", "aaccaa"},
                {"aaaabbbccc", "ddeeccc", "aabcccdeedcccbaa"},
                {"awwzaigvxuikdqlvshspsvyckttwdzqmarpxglwmpob", "dtisfxyobndu", "abddgiklmpqstvwwxzzxwwvtsqpmlkigddba"},
                {"mgbgikhvjyiigxhsrgekjmjkrs", "cikmqfxpcybzyhbdrhudjmsoaqdurgjsnjlqogrkcmdtxbyazfxv", "abbbccddfggghhiiijjjkklmmmnnoppqrrrsstuvxyyzzyyxvutssrrrapponnmmmlkkjjjiiihhgggfddccbbba"},
                {"aaaabbbccc", "ddeeccc", "aabcccdeedcccbaa"},
                {"awwzaigvxuikdqlvshspsvyckttwdzqmarpxglwmpob", "disfxyobndu", "abddgiklmpqstvwwxzzxwwvtsqpmlkigddba"}
        };

        for (String[] testCase : testCases) {
            String s1 = testCase[0];
            String s2 = testCase[1];
            String expected = testCase[2];
            String result = mergePalindromes(s1, s2);

            if (result.equals(expected)) {
                System.out.println("Test case passed");
            } else {
                System.out.println("Test case failed");
                System.out.println("Input: s1 = " + s1 + ", s2 = " + s2);
                System.out.println("Expected: " + expected);
                System.out.println("Got: " + result);
            }
        }
    }
}

