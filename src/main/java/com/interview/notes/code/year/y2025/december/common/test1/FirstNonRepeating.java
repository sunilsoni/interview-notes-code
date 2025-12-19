package com.interview.notes.code.year.y2025.december.common.test1;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FirstNonRepeating {
    
    // Main method to find first non-repeating character
    static Character findFirstNonRepeating(String s) {
        // Return null if string is null or empty - edge case handling
        if (s == null || s.isEmpty()) return null;
        
        // LinkedHashMap keeps insertion order - crucial for "first" requirement
        // Collectors.groupingBy counts occurrences of each character
        var charCount = s.chars()  // Convert string to IntStream of char codes
            .mapToObj(c -> (char) c)  // Box each int to Character object
            .collect(Collectors.groupingBy(  // Group by character itself
                Function.identity(),  // Key = character
                LinkedHashMap::new,  // Use LinkedHashMap to preserve order
                Collectors.counting()  // Value = count of occurrences
            ));
        
        // Stream through map entries in insertion order
        // Filter entries where count equals 1 (non-repeating)
        // Return first match or null if none found
        return charCount.entrySet().stream()
            .filter(e -> e.getValue() == 1)  // Keep only chars appearing once
            .map(Map.Entry::getKey)  // Extract just the character
            .findFirst()  // Get first non-repeating
            .orElse(null);  // Return null if no non-repeating char exists
    }
    
    // Test runner - checks expected vs actual and prints PASS/FAIL
    static void test(String input, Character expected, String desc) {
        var result = findFirstNonRepeating(input);  // Get actual result
        // Compare using Objects.equals to handle null safely
        var status = Objects.equals(result, expected) ? "PASS" : "FAIL";
        // Print test result with details
        System.out.printf("%s | %s | Input: \"%s\" | Expected: %s | Got: %s%n",
            status, desc, 
            input == null ? "null" : (input.length() > 20 ? input.substring(0, 20) + "..." : input),
            expected, result);
    }
    
    public static void main(String[] args) {
        System.out.println("=== First Non-Repeating Character Tests ===\n");
        
        // Basic test cases
        test("leetcode", 'l', "Basic - first char is unique");
        test("loveleetcode", 'v', "Basic - middle unique char");
        test("aabb", null, "All chars repeat");
        
        // Edge cases
        test("", null, "Empty string");
        test(null, null, "Null input");
        test("a", 'a', "Single character");
        test("aa", null, "Two same chars");
        test("ab", 'a', "Two different chars");
        
        // Special characters
        test("a b c a b", 'c', "With spaces");
        test("!!@@##$", '$', "Special chars");
        test("aA", 'a', "Case sensitive check");
        
        // Complex cases
        test("abcdefghijklmnopqrstuvwxyz", 'a', "All unique - return first");
        test("aabbccddeeffg", 'g', "Last char is unique");
        test("abcabc", null, "Pattern repeating");
        
        // Large data test
        System.out.println("\n=== Large Data Tests ===\n");
        
        // Build large string: 100000 'a's + one 'z' + 100000 'a's
        var large1 = "a".repeat(100000) + "z" + "a".repeat(100000);
        test(large1, 'z', "Large - unique in middle (200001 chars)");
        
        // All same characters - large
        var large2 = "x".repeat(500000);
        test(large2, null, "Large - all same (500000 chars)");
        
        // Large with unique at start
        var large3 = "z" + "a".repeat(100000);
        test(large3, 'z', "Large - unique at start");
        
        // Large with unique at end
        var large4 = "a".repeat(100000) + "z";
        test(large4, 'z', "Large - unique at end");
        
        System.out.println("\n=== All Tests Completed ===");
    }
}