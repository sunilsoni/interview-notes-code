package com.interview.notes.code.year.y2024.oct24.amazon.test25;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/*
You are given a string with repeated characters, your task is to produce a new string where the characters of the string are rearranged so that no two adjacent characters are the same.
A character can be any value permissible in the language runtime. It is possible the string does not have a solution.
For example:
rearrange ("bcaaa") -> "abaca" rearrange ("zzzbg") -> "zbzgz".

FOlloups:

You are given a string with repeated characters, your task is to produce a new string where the characters of the string are rearranged so that no two adjacent characters are the same.
A character can be any value permissible in the language runtime. It is possible the string does not have a solution.
For example:
rearrange ("bcaaa") -> "abaca" rearrange ("zzzbg") -> "zbzgz".
 */
public class StringRearrangement {

    public static String rearrange(String input) {
        if (input == null || input.isEmpty()) {
            return "";
        }

        // Count frequency of each character
        Map<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) + 1);
        }

        // Use PriorityQueue to get most frequent characters first
        PriorityQueue<CharFreq> pq = new PriorityQueue<>((a, b) -> b.freq - a.freq);
        for (Map.Entry<Character, Integer> entry : frequencyMap.entrySet()) {
            pq.offer(new CharFreq(entry.getKey(), entry.getValue()));
        }

        StringBuilder result = new StringBuilder();
        CharFreq prev = null;

        while (!pq.isEmpty() || prev != null) {
            // No more characters but previous still has frequency > 0
            if (pq.isEmpty() && prev != null) {
                return "";  // No valid solution
            }

            CharFreq current = pq.poll();
            result.append(current.ch);
            current.freq--;

            // Add previous back to queue if it still has frequency
            if (prev != null && prev.freq > 0) {
                pq.offer(prev);
            }

            prev = current.freq > 0 ? current : null;
        }

        return result.toString();
    }

    public static void main(String[] args) {
        // Basic test cases
        runTest("bcaaa", "abaca", "Basic Test 1");
        runTest("zzzbg", "zbzgz", "Basic Test 2");

        // Edge cases
        runTest("", "", "Empty String");
        runTest("a", "a", "Single Character");
        runTest("aa", "", "Impossible Case - Same Characters");
        runTest("aaa", "", "Impossible Case - Three Same Characters");
        runTest("aabb", "abab", "Equal Frequency");
        runTest("aaabc", "abaca", "High Frequency Character");

        // Complex test cases
        runTest("aaabbc", "ababac", "Multiple Characters");
        runTest("zzzabc", "zazbzc", "Multiple Same Characters");

        // Large input test cases
        testLargeInput();
    }

    private static void testLargeInput() {
        System.out.println("\nTesting Large Inputs:");

        // Test 1: Long string with varied characters
        StringBuilder input1 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            input1.append((char) ('a' + i % 26));
        }
        testLargeCase(input1.toString(), "Large Varied String");

        // Test 2: Long string with repetitive pattern
        StringBuilder input2 = new StringBuilder();
        for (int i = 0; i < 10000; i++) {
            input2.append("abc");
        }
        testLargeCase(input2.toString(), "Large Repetitive Pattern");

        // Test 3: String with many repeated characters
        StringBuilder input3 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            input3.append('a').append('b');
        }
        testLargeCase(input3.toString(), "Large Two-Character Pattern");
    }

    private static void testLargeCase(String input, String testName) {
        long startTime = System.currentTimeMillis();
        String result = rearrange(input);
        long endTime = System.currentTimeMillis();

        boolean isValid = validateResult(result);

        System.out.println(testName + ":");
        System.out.println("Input length: " + input.length());
        System.out.println("Result length: " + result.length());
        System.out.println("Execution time: " + (endTime - startTime) + "ms");
        System.out.println("Valid arrangement: " + isValid);
        System.out.println();
    }

    private static void runTest(String input, String expected, String testName) {
        String result = rearrange(input);
        boolean passed = false;

        if (expected.isEmpty() && result.isEmpty()) {
            passed = true;
        } else if (!result.isEmpty()) {
            passed = validateResult(result) && result.length() == input.length();
        }

        System.out.println(testName + ": " + (passed ? "PASS" : "FAIL"));
        if (!passed) {
            System.out.println("Input: " + input);
            System.out.println("Expected: " + expected);
            System.out.println("Got: " + result);
            System.out.println();
        }
    }

    private static boolean validateResult(String result) {
        if (result.isEmpty()) {
            return true;
        }

        // Check no adjacent characters are same
        for (int i = 1; i < result.length(); i++) {
            if (result.charAt(i) == result.charAt(i - 1)) {
                return false;
            }
        }
        return true;
    }

    private static class CharFreq {
        char ch;
        int freq;

        CharFreq(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }
    }
}