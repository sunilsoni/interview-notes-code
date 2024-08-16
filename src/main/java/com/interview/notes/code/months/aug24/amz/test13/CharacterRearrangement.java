package com.interview.notes.code.months.aug24.amz.test13;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
Rearrange characters in a string so that no two adjacent characters are the same.
You are given a string with repeated characters, your task is to rearrange characters in a string so that no two adjacent characters are the same.
Sample input bcaaal
Sample output abaca

 */
public class CharacterRearrangement {
    public static String rearrangeString(String s) {
        // Count the frequency of each character
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : s.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }

        // Create a max heap based on character frequency
        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) -> charFreq.get(b) - charFreq.get(a));
        maxHeap.addAll(charFreq.keySet());

        StringBuilder result = new StringBuilder();
        char lastChar = '#';

        while (!maxHeap.isEmpty()) {
            char currentChar = maxHeap.poll();
            result.append(currentChar);
            charFreq.put(currentChar, charFreq.get(currentChar) - 1);

            if (lastChar != '#' && charFreq.get(lastChar) > 0) {
                maxHeap.offer(lastChar);
            }

            lastChar = currentChar;

            if (charFreq.get(currentChar) > 0) {
                if (maxHeap.isEmpty()) {
                    return ""; // Impossible to rearrange
                }
                char nextChar = maxHeap.poll();
                result.append(nextChar);
                charFreq.put(nextChar, charFreq.get(nextChar) - 1);

                if (charFreq.get(nextChar) > 0) {
                    maxHeap.offer(nextChar);
                }
                maxHeap.offer(currentChar);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Test cases
        String[] testCases = {
                "bcaaal",
                "aab",
                "aaab",
                "aabbcc",
                "aaabc",
                "aaabbc",
                "abcdefg",
                "aaaaabbbbbccccc"
        };

        for (String testCase : testCases) {
            String result = rearrangeString(testCase);
            System.out.println("Input: " + testCase);
            System.out.println("Output: " + (result.isEmpty() ? "Impossible to rearrange" : result));
            System.out.println("Valid: " + isValid(result));
            System.out.println();
        }
    }

    private static boolean isValid(String s) {
        if (s.isEmpty()) return true;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) return false;
        }
        return true;
    }
}
