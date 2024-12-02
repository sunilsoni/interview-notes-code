package com.interview.notes.code.year.y2024.june24.test7;

public class MergePalindromes5 {


    public static String mergePalindromes(String s1, String s2) {
        String pal1 = getLongestPalindrome(s1);
        String pal2 = getLongestPalindrome(s2);
        return merge(pal1, pal2);
    }

    private static String getLongestPalindrome(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middle = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                if (middle == 0 || (char) (i + 'a') < middle) {
                    middle = (char) (i + 'a');
                }
            }
            for (int j = 0; j < freq[i] / 2; j++) {
                half.append((char) (i + 'a'));
            }
        }

        String firstHalf = half.toString();
        String secondHalf = half.reverse().toString();
        return firstHalf + (middle == 0 ? "" : middle) + secondHalf;
    }

    private static String merge(String pal1, String pal2) {
        int[] freq = new int[26];
        for (char c : (pal1 + pal2).toCharArray()) {
            freq[c - 'a']++;
        }

        StringBuilder half = new StringBuilder();
        char middle = 0;
        for (int i = 0; i < 26; i++) {
            if (freq[i] % 2 != 0) {
                if (middle == 0 || (char) (i + 'a') < middle) {
                    middle = (char) (i + 'a');
                }
            }
            for (int j = 0; j < freq[i] / 2; j++) {
                half.append((char) (i + 'a'));
            }
        }

        String firstHalf = half.toString();
        String secondHalf = half.reverse().toString();
        return firstHalf + (middle == 0 ? "" : middle) + secondHalf;
    }

    public static void main(String[] args) {
        test("adaab", "cac", "aaccaa");
        test("aaaabbbccc", "ddeeccc", "aabcccdeedcccbaa");
        test("awwzaigvxuikdqlvshspsvyckttwdzqmarpxglwmpob", "dtisfxyobndu", "abddgiklmpqstvwwxzzxwwvtsqpmlkigddba");
        test("mgbgikhvjyiigxhsrgekjmjkrs", "cikmqfxpcybzyhbdrhudjmsoaqdurgjsnjlqogrkcmdtxbyazfxv", "abbbccddfggghhiiijjjkklmmmnnoppqrrrsstuvxyyzzyyxvutssrrrapponnmmmlkkjjjiiihhgggfddccbbba");
        test("aaaabbbccc", "ddeeccc", "aabcccdeedcccbaa");
        test("awwzaigvxuikdqlvshspsvyckttwdzqmarpxglwmpob", "disfxyobndu", "abddgiklmpqstvwwxzzxwwvtsqpmlkigddba");
    }

    private static void test(String s1, String s2, String expected) {
        String result = mergePalindromes(s1, s2);
        if (!result.equals(expected)) {
            System.out.println("Test failed for inputs: s1=" + s1 + ", s2=" + s2);
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
        } else {
            System.out.println("Test passed for inputs: s1=" + s1 + ", s2=" + s2);
        }
    }
}