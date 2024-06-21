package com.interview.notes.code.months.june24.test7;

import java.util.*;

public class MergePalindromes {

    public static void main(String[] args) {
        System.out.println(mergePalindromes("aab", "cca")); // Sample Test Case
    }

    public static String mergePalindromes(String s1, String s2) {
        String palindrome1 = longestPalindrome(s1);
        String palindrome2 = longestPalindrome(s2);

        List<String> mergedPalindromes = generateMergedPalindromes(palindrome1, palindrome2);

        Collections.sort(mergedPalindromes);

        return mergedPalindromes.get(0);
    }

    private static String longestPalindrome(String s) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        StringBuilder half = new StringBuilder();
        Character oddChar = null;

        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            if (count % 2 == 1) {
                if (oddChar == null || c < oddChar) {
                    oddChar = c;
                }
            }
            for (int i = 0; i < count / 2; i++) {
                half.append(c);
            }
        }

        String halfStr = half.toString();
        String reverseHalf = new StringBuilder(halfStr).reverse().toString();
        return oddChar == null ? halfStr + reverseHalf : halfStr + oddChar + reverseHalf;
    }

    private static List<String> generateMergedPalindromes(String p1, String p2) {
        List<String> mergedPalindromes = new ArrayList<>();

        for (int i = 0; i <= p1.length(); i++) {
            String merged = p1.substring(0, i) + p2 + p1.substring(i);
            if (isPalindrome(merged)) {
                mergedPalindromes.add(merged);
            }
        }

        return mergedPalindromes;
    }

    private static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
