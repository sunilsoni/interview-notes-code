package com.interview.notes.code.months.june24.test7;

import java.util.*;

public class MergePalindromes4 {

    public static String mergePalindromes(String s1, String s2) {
        // Find all palindromes from s1
        List<String> s1Palindromes = findPalindromes(s1);
        // Find all palindromes from s2
        List<String> s2Palindromes = findPalindromes(s2);

        // Find the longest palindrome from s1
        String longestS1Palindrome = findLongestPalindrome(s1Palindromes);
        // Find the longest palindrome from s2
        String longestS2Palindrome = findLongestPalindrome(s2Palindromes);

        // Find the alphabetically smallest merged palindrome
        return findSmallestMergedPalindrome(longestS1Palindrome, longestS2Palindrome);
    }

    // Find all palindromes from a string
    private static List<String> findPalindromes(String str) {
        List<String> palindromes = new ArrayList<>();
        // Iterate through all possible substrings
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j <= str.length(); j++) {
                String substring = str.substring(i, j);
                // Check if the substring is a palindrome
                if (isPalindrome(substring)) {
                    palindromes.add(substring);
                }
            }
        }
        return palindromes;
    }

    // Check if a string is a palindrome
    private static boolean isPalindrome(String str) {
        return new StringBuilder(str).reverse().toString().equals(str);
    }

    // Find the longest palindrome from a list of palindromes
    private static String findLongestPalindrome(List<String> palindromes) {
        String longestPalindrome = "";
        for (String palindrome : palindromes) {
            if (palindrome.length() > longestPalindrome.length()) {
                longestPalindrome = palindrome;
            }
        }
        return longestPalindrome;
    }

    // Find the alphabetically smallest merged palindrome
    private static String findSmallestMergedPalindrome(String s1Palindrome, String s2Palindrome) {
        // Create a map to store the frequency of each character in both palindromes
        Map<Character, Integer> charFrequency = new HashMap<>();
        for (char c : s1Palindrome.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }
        for (char c : s2Palindrome.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        // Create a sorted list of characters based on frequency
        List<Character> sortedChars = new ArrayList<>(charFrequency.keySet());
        Collections.sort(sortedChars, Comparator.comparingInt(charFrequency::get).reversed());

        // Build the merged palindrome
        StringBuilder mergedPalindrome = new StringBuilder();
        for (char c : sortedChars) {
            // Add the character if its frequency is even
            if (charFrequency.get(c) % 2 == 0) {
                mergedPalindrome.append(String.valueOf(c).repeat(charFrequency.get(c)));
            }
            // Add the character once if its frequency is odd
            else {
                mergedPalindrome.append(c);
            }
        }

        // Return the alphabetically smallest merged palindrome
        return mergedPalindrome.toString();
    }

    public static void main(String[] args) {
        runTest("adaab", "cac", "aaccaa");
        runTest("aaaabbbccc", "ddeeccc", "aabcccdeedcccbaa");
        runTest("awwzaigvxuikdqlvshspsvyckttwdzqmarpxglwmpob", "dtisfxyobndu", "abddgiklmpqstvwwxzzxwwvtsqpmlkigddba");
        runTest("mgbgikhvjyiigxhsrgekjmjkrs", "cikmqfxpcybzyhbdrhudjmsoaqdurgjsnjlqogrkcmdtxbyazfxv", "abbbccddfggghhiiijjjkklmmmnnoppqrrrsstuvxyyzzyyxvutssrrrapponnmmmlkkjjjiiihhgggfddccbbba");
        runTest("aaaabbbccc", "ddeeccc", "aabcccdeedcccbaa");
        runTest("awwzaigvxuikdqlvshspsvyckttwdzqmarpxglwmpob", "disfxyobndu", "abddgiklmpqstvwwxzzxwwvtsqpmlkigddba");
    }

    private static void runTest(String s1, String s2, String expected) {
        String result = mergePalindromes(s1, s2);
        if (result.equals(expected)) {
            System.out.println("Test passed: " + s1 + ", " + s2 + " -> " + result);
        } else {
            System.out.println("Test failed: " + s1 + ", " + s2 + " -> " + result + " (Expected: " + expected + ")");
        }
    }
}