package com.interview.notes.code.months.oct24.amazon.test28;

import java.util.*;

public class StringRearranger {
    
    public static String rearrangeString(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }

        // Count frequency of characters
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        // Check if rearrangement is possible
        int maxFreq = Collections.max(freqMap.values());
        if (maxFreq > (str.length() + 1) / 2) {
            return ""; // Impossible to rearrange
        }

        // Create max heap based on character frequencies
        PriorityQueue<Map.Entry<Character, Integer>> maxHeap = 
            new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
        maxHeap.addAll(freqMap.entrySet());

        StringBuilder result = new StringBuilder();
        Map.Entry<Character, Integer> previous = null;

        while (!maxHeap.isEmpty() || previous != null) {
            if (maxHeap.isEmpty() && previous != null) {
                return ""; // Invalid case
            }

            Map.Entry<Character, Integer> current = maxHeap.poll();
            result.append(current.getKey());
            current.setValue(current.getValue() - 1);

            if (previous != null && previous.getValue() > 0) {
                maxHeap.offer(previous);
            }

            previous = current.getValue() > 0 ? current : null;
        }

        return result.toString();
    }

    private static void runTest(String input, String expectedOutput, String testName) {
        String result = rearrangeString(input);
        boolean isValid;
        
        if (result.isEmpty()) {
            // For impossible cases, empty result is expected
            isValid = isImpossibleCase(input);
        } else {
            isValid = isValidRearrangement(result, input) && 
                     !hasAdjacentDuplicates(result);
        }
        
        System.out.println("Test Case: " + testName);
        System.out.println("Input: " + input);
        System.out.println("Output: " + (result.isEmpty() ? "Impossible" : result));
        System.out.println("Expected: " + (expectedOutput == null ? "Impossible" : expectedOutput));
        System.out.println("Status: " + (isValid ? "PASS" : "FAIL"));
        System.out.println();
    }

    private static boolean isImpossibleCase(String input) {
        if (input == null || input.isEmpty()) return false;
        
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }
        
        int maxFreq = Collections.max(freqMap.values());
        return maxFreq > (input.length() + 1) / 2;
    }

    private static boolean isValidRearrangement(String result, String input) {
        if (result.length() != input.length()) return false;
        
        char[] inputChars = input.toCharArray();
        char[] resultChars = result.toCharArray();
        Arrays.sort(inputChars);
        Arrays.sort(resultChars);
        
        return Arrays.equals(inputChars, resultChars);
    }

    private static boolean hasAdjacentDuplicates(String str) {
        if (str == null || str.length() <= 1) return false;
        
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i-1)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // Test Case 1: Given example
        runTest("bcaaa", "abaca", "Basic Test");

        // Test Case 2: Empty string
        runTest("", "", "Empty String");

        // Test Case 3: Single character
        runTest("a", "a", "Single Character");

        // Test Case 4: All same characters
        runTest("aaa", null, "All Same Characters");

        // Test Case 5: No adjacent duplicates needed
        runTest("abc", "abc", "No Rearrangement Needed");

        // Test Case 6: Larger input
        runTest("aaaabbbcc", "ababacabc", "Larger Input");

        // Test Case 7: Another impossible case
        runTest("aaaa", null, "Another Impossible Case");

        // Test Case 8: Border case
        runTest("aabb", "abab", "Border Case");

        // Test Case 9: Very large input (stress test)
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            largeInput.append("abc");
        }
        runTest(largeInput.toString(), null, "Very Large Input");
    }
}