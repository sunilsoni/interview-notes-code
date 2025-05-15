package com.interview.notes.code.year.y2025.may.common.test6;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FirstNonRepeatingChar {
    
    public static Character findFirstNonRepeating(String input) {
        if (input == null || input.isEmpty()) {
            return null;
        }
        
        // Using LinkedHashMap to maintain insertion order
        return input.chars()
                .mapToObj(ch -> (char) ch)
                .collect(Collectors.groupingBy(
                        ch -> ch,
                        LinkedHashMap::new,
                        Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1L)
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
    }

    public static void main(String[] args) {
        // Test cases
        runTest("hello", 'h', "Basic case");
        runTest("racecar", 'e', "Palindrome");
        runTest("aabbcc", null, "No unique char");
        runTest("", null, "Empty string");
        runTest("aabbccddee", null, "All repeating");
        runTest("stress", 't', "Multiple unique chars");
        runTest("a", 'a', "Single char");
        runTest("    ", null, "All spaces");
        
        // Large data test
        StringBuilder largeInput = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            largeInput.append("abcdef");
        }
        long startTime = System.currentTimeMillis();
        findFirstNonRepeating(largeInput.toString());
        long endTime = System.currentTimeMillis();
        System.out.println("Large data test completed in: " + (endTime - startTime) + "ms");
    }

    private static void runTest(String input, Character expected, String testName) {
        Character result = findFirstNonRepeating(input);
        boolean passed = (expected == null && result == null) || 
                        (expected != null && expected.equals(result));
        
        System.out.printf("Test '%s': %s%n", testName, passed ? "PASS" : "FAIL");
        System.out.printf("Input: '%s', Expected: %s, Got: %s%n", 
                         input, expected, result);
        System.out.println();
    }
}
