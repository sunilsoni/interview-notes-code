package com.interview.notes.code.months.oct24.amazon.test27;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class CharacterRearranger {

    public static String rearrangeString(String s) {
        // Count character frequencies
        Map<Character, Integer> charCount = new HashMap<>();
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Create a max heap based on character frequencies
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> charCount.get(b) - charCount.get(a));
        maxHeap.addAll(charCount.keySet());

        StringBuilder result = new StringBuilder();
        char lastChar = '#'; // Placeholder for last added character

        while (!maxHeap.isEmpty()) {
            char current = maxHeap.poll();
            result.append(current);
            charCount.put(current, charCount.get(current) - 1);

            if (lastChar != '#' && charCount.get(lastChar) > 0) {
                maxHeap.offer(lastChar);
            }

            lastChar = current;

            if (charCount.get(current) == 0) {
                charCount.remove(current);
            }
        }

        return result.length() == s.length() ? result.toString() : "";
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "bcaaa",
                "aab",
                "aaab",
                "a",
                "aa",
                "aabbcc",
                "aaaabbbccc",
                "abcdefg",
                "zzzzzyyyyxxxxwwwvvvuuu"
        };

        String[] expectedOutputs = {
                "abaca",
                "aba",
                "",
                "a",
                "",
                "abcabc",
                "abcabcabc",
                "abcdefg",
                "zyzxwvuzyzxwvuzyzxwvu"
        };

        for (int i = 0; i < testCases.length; i++) {
            String input = testCases[i];
            String expected = expectedOutputs[i];
            String result = rearrangeString(input);

            boolean passed = result.equals(expected) && isValid(result);
            System.out.println("Test Case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expected);
            System.out.println("Result: " + result);
            System.out.println();
        }

        // Large data input test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append((char) ('a' + i % 26));
        }
        String largeResult = rearrangeString(largeInput.toString());
        System.out.println("Large Input Test: " + (isValid(largeResult) ? "PASS" : "FAIL"));
        System.out.println("Large Input Length: " + largeInput.length());
        System.out.println("Large Result Length: " + largeResult.length());
    }

    private static boolean isValid(String s) {
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }
}
