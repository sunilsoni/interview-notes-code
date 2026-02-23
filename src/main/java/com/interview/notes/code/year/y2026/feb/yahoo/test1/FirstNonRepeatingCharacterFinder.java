package com.interview.notes.code.year.y2026.feb.yahoo.test1;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class FirstNonRepeatingCharacterFinder { // Proper meaningful class name

    public static Character find(String s) { // Method to find first non-repeating character
        
        if (s == null || s.isEmpty()) return null; // Return null if string is empty
        
        Map<Character, Long> map = s.chars() // Convert string to IntStream
                .mapToObj(c -> (char) c) // Convert int to Character
                .collect(Collectors.groupingBy( // Group characters
                        c -> c, // Group by character itself
                        LinkedHashMap::new, // Preserve insertion order
                        Collectors.counting() // Count frequency
                ));
        
        return map.entrySet().stream() // Stream over map entries
                .filter(e -> e.getValue() == 1) // Filter frequency = 1
                .map(Map.Entry::getKey) // Extract character
                .findFirst() // Get first match
                .orElse(null); // Return null if none found
    }

    public static void main(String[] args) { // Main method for testing
        
        record TestCase(String input, Character expected) {} // Java 21 record for test data
        
        List<TestCase> tests = List.of( // List of test cases
                new TestCase("swiss", 'w'),
                new TestCase("aabbcc", null),
                new TestCase("leetcode", 'l'),
                new TestCase("a", 'a'),
                new TestCase("", null)
        );
        
        tests.forEach(t -> { // Iterate test cases
            Character result = find(t.input()); // Call method
            boolean pass = Objects.equals(result, t.expected()); // Compare result
            System.out.println("Input: " + t.input() + 
                               " | Expected: " + t.expected() + 
                               " | Got: " + result + 
                               " | " + (pass ? "PASS" : "FAIL")); // Print result
        });
        
        // Large data test
        String large = "a".repeat(1_000_000) + "b"; // Create large string
        Character largeResult = find(large); // Test performance
        System.out.println("Large Input Test: " + 
                           (largeResult == 'b' ? "PASS" : "FAIL")); // Validate large test
    }
}